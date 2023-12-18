import Overlays.GameScreen;
import Overlays.Screen;
import Render.Renderable;
import Render.Renderer;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    public final static int FPS = 60;

    private static int score = 0;

    public static void main(String[] args) {
        Renderer.hideCursor();
        Controller controller = new Controller();
        new Thread(controller).start();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        for (int i = 1; i <= 100; i++) {
            ArrayList<Renderable> renderObjects = new ArrayList<>();
            renderObjects.add(new HighScore());
            renderObjects.add(new LoadingBar(i));

            Renderer.render(renderObjects.toArray(new Renderable[0]));
//            Renderer.render(new Renderable[]{new HighScore()});
//            Renderer.render(new Renderable[]{new LoadingBar(i)});

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        final Bird bird = new Bird();
        final Obstacles obstacles = new Obstacles();

        executorService.scheduleAtFixedRate(() -> {
            if (!bird.isDead()) {
                if (controller.getJump()) {
                    bird.jump();
                }

                bird.update();

                score = obstacles.update(score);

                if (obstacles.checkCollision(bird.getY())) {
                    bird.setDead(true);
                }

                ArrayList<Renderable> renderObjects = new ArrayList<>();
                renderObjects.add(new Screen(GameScreen.GAME, score));
                renderObjects.addAll(obstacles.getPipes());
                renderObjects.add(bird);

                Renderer.render(renderObjects.toArray(new Renderable[0]));
            } else {
                ArrayList<Renderable> renderObjects = new ArrayList<>();
                renderObjects.addAll(obstacles.getPipes());
                renderObjects.add(bird);
                renderObjects.add(new Screen(GameScreen.GAME_OVER, score));

                Renderer.render(renderObjects.toArray(new Renderable[0]));
                executorService.shutdown();
            }
        }, 0, 1000 / FPS, TimeUnit.MILLISECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executorService.shutdown();
            Renderer.showCursor();
            Renderer.clearGame();
        }));
    }
}
