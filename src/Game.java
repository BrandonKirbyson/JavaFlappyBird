import Overlays.GameScreen;
import Overlays.Screen;
import Render.Renderable;
import Render.Renderer;

import java.util.ArrayList;
import java.util.Scanner;
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

        final Bird bird = new Bird();
        final Obstacles obstacles = new Obstacles();

        Renderer.render(new Renderable[]{new Screen(GameScreen.MAIN_MENU)});

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

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
                renderObjects.add(bird);
                renderObjects.addAll(obstacles.getPipes());

                Renderer.render(renderObjects.toArray(new Renderable[0]));
            } else {
                Renderer.render(new Renderable[]{bird, new Screen(GameScreen.GAME_OVER)});
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
