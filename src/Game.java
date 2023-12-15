import Render.Renderable;
import Render.Renderer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    public static void main(String[] args) {
        Controller controller = new Controller();
        new Thread(controller).start();
        Bird bird = new Bird();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            if (controller.getJump()) {
                bird.jump();
            }

            bird.update();

            Renderer.render(new Renderable[]{bird});
        }, 0, 16, TimeUnit.MILLISECONDS);
    }
}
