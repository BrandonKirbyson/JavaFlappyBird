import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Controller controller = new Controller();
        new Thread(controller).start();
        Bird bird = new Bird();

        while (true) {
            if (controller.getJump()) {
                bird.jump();
            }
            bird.update();

            Renderer.render(bird);
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}