import Render.Renderable;
import Render.Renderer;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    public final static int FPS = 60;

    public static void main(String[] args) {
        Renderer.hideCursor();
        Controller controller = new Controller();
        new Thread(controller).start();
        final Bird bird = new Bird();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Renderer.render(new Renderable[]{new Screen(GameScreen.MAIN_MENU)});

        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();

        executorService.scheduleAtFixedRate(() -> {
            if (!bird.isDead()) {
                if (controller.getJump()) {
                    bird.jump();
                }

                bird.update();

                Renderer.render(new Renderable[]{bird});
            } else {
                Renderer.render(new Renderable[]{new Screen(GameScreen.GAME_OVER)});
            }

        }, 0, 1000 / FPS, TimeUnit.MILLISECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executorService.shutdown();
            Renderer.showCursor();
        }));
    }
}
