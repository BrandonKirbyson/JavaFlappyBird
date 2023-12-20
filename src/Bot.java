import Render.Renderer;

public class Bot {
    private final static int JumpArea = 10;

    /**
     * @return if the bot should jump
     */
    public static boolean getJump(Bird bird, Obstacles obstacles) {
        Pipe currentPipe = obstacles.getCurrentPipe();
        Pipe nextPipe = obstacles.getCurrentPipe(1);
        int targetY = currentPipe.getGapY();
        if (nextPipe != null && currentPipe.getGapSize() > Bird.getHeight() + JumpArea) {
            if (nextPipe.getGapY() < currentPipe.getGapY()) {
                targetY = currentPipe.getGapY() - currentPipe.getGapSize() / 2 + JumpArea - Bird.getHeight();
            } else {
                targetY = currentPipe.getGapY() + currentPipe.getGapSize() / 2 - Bird.getHeight() / 2 - 1;
            }
        }
        System.out.println("Target Y: " + currentPipe.getGapY() + " Next Target Y: " + nextPipe.getGapY());
        return bird.getY() > targetY || bird.getY() > Renderer.getHeight() - Bird.getHeight() / 2 - 1;
    }
}
