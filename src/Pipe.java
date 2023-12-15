import Render.Overlay;
import Render.Position;
import Render.Renderable;
import Render.Renderer;

public class Pipe implements Renderable {
    private static final int width = 10;

    private final int gapY;
    private final int gapSize;

    private boolean cleared = false;

    private double x = Renderer.getWidth();

    public Pipe(int gapY, int gapSize) {
        this.gapY = gapY;
        this.gapSize = gapSize;
    }

    public void update(double speed) {
        x -= speed;
    }

    public boolean checkCollision(int birdY) {
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
        for (int i = 0; i < pipe.length; i++) {
            pipe[i] = "|";
            if (i < gapY || i >= gapY + gapSize) {
                for (int j = 0; j < width; j++) {
                    pipe[i] += " ";
                }
            } else {
                for (int j = 0; j < width; j++) {
                    pipe[i] += "-";
                }
            }
        }
        return pipe;
    }

    @Override
    public Overlay getOverlay() {
        return new Overlay(getPipeDrawing(), new Position((int) Math.round(x), 0, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.TOP));
    }
}
