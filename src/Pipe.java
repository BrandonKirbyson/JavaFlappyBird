import Render.Overlay;
import Render.Position;
import Render.Renderable;
import Render.Renderer;

/**
 * A pipe in the game
 */
public final class Pipe implements Renderable {
    public static final int SPACING = 30;

    private static final int width = 5;

    private final int gapY;
    private final int gapSize;

    private boolean cleared = false;
    public boolean genNext = false;

    private double x = Renderer.getWidth();

    /**
     * @param gapY    the y position of the gap
     * @param gapSize the size of the gap
     */
    public Pipe(int gapY, int gapSize) {
        this.gapY = gapY;
        this.gapSize = gapSize;
    }

    /**
     * Updates the pipe by moving it to the left
     *
     * @param speed the speed to move the pipe
     */
    public void update(double speed) {
        x -= speed;
    }

    /**
     * Checks if the bird is colliding with the pipe
     *
     * @param birdY the y position of the bird
     * @return if the bird is colliding with the pipe
     */
    public boolean checkCollision(int birdY) {
        if (x < Bird.getX() + (double) Bird.getWidth() / 2 + (double) width / 2 && x > Bird.getX() - (double) Bird.getWidth() / 2 - (double) width / 2) {
            return birdY < gapY - gapSize / 2 + Bird.getHeight() / 2 - 1 || birdY > gapY + gapSize / 2 - Bird.getHeight() / 2;
        }
        return false;
    }

    /**
     * @return the x position of the pipe
     */
    public int getX() {
        return (int) Math.round(x);
    }

    /**
     * Sets the x position of the pipe
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return if the pipe has been cleared
     */
    public boolean isCleared() {
        return cleared;
    }

    /**
     * Sets if the pipe has been cleared
     *
     * @param cleared if the pipe has been cleared
     */
    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    /**
     * @return the drawing of the pipe
     */
    private String[] getPipeDrawing() {
        String[] pipe = new String[Renderer.getHeight()];
        // |==| is the pipe
        for (int i = 0; i < pipe.length; i++) {
            if (i < gapY - gapSize / 2 || i > gapY + gapSize / 2) {
                pipe[i] = "[" + " ".repeat(width) + "]";
            } else if (i == gapY - gapSize / 2) {
                pipe[i] = "[" + "=".repeat(width) + "]";
            } else if (i == gapY + gapSize / 2) {
                pipe[i] = "[" + "=".repeat(width) + "]";
            } else {
                pipe[i] = " ".repeat(2 + width);
            }
        }
        return pipe;
    }

    /**
     * @return the overlay of the pipe
     */
    @Override
    public Overlay getOverlay() {
        return new Overlay(getPipeDrawing(), new Position((int) Math.round(x), 0, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.TOP));
    }
}
