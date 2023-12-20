import Render.Overlay;
import Render.Position;
import Render.Renderable;
import Render.Renderer;

public class Pipe implements Renderable {
    public static final int SPACING = 30;

    private static final int width = 4;

    private final int gapY;
    private final int gapSize;

    private boolean cleared = false;
    public boolean genNext = false;

    private double x = Renderer.getWidth();

    public Pipe(int gapY, int gapSize) {
        this.gapY = gapY;
        this.gapSize = gapSize;
    }

    public void update(double speed) {
        x -= speed;
    }

    public boolean checkCollision(int birdY) {
        if (x < Bird.getX() + (double) Bird.getWidth() / 2 + (double) width / 2 && x > Bird.getX() - (double) Bird.getWidth() / 2 - (double) width / 2) {
            return birdY < gapY - gapSize / 2 + Bird.getHeight() / 2 || birdY > gapY + gapSize / 2 - Bird.getHeight() / 2;
        }
        return false;
    }

    public int getX() {
        return (int) Math.round(x);
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    private String[] getPipeDrawing() {
        String[] pipe = new String[Renderer.getHeight()];
        // |==| for pipe
        for (int i = 0; i < pipe.length; i++) {
            if (i < gapY - gapSize / 2 || i > gapY + gapSize / 2) {
                pipe[i] = "[" + "=".repeat(width) + "]";
            } else {
                pipe[i] = " ".repeat(2 + width);
            }
        }
        return pipe;
    }

    @Override
    public Overlay getOverlay() {
        return new Overlay(getPipeDrawing(), new Position((int) Math.round(x), 0, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.TOP));
    }
}
