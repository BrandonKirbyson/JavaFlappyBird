import Render.*;

public class LoadingBar implements Renderable {
    private int progress = 0;
    private final int maxProgress = 100;

    public LoadingBar(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public Overlay getOverlay() {
        return new Overlay(
                new String[]{
                        "Loading: " + progress + "%",
                        "█".repeat(progress / 10) + "░".repeat(10 - progress / 10)
                },
                new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.CENTER)
        );
    }
}
