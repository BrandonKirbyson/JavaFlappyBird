import Render.*;

public class LoadingBar implements Renderable {
    private final int progress;

    public LoadingBar(int progress) {
        this.progress = progress;
    }

    @Override
    public Overlay getOverlay() {
        return new Overlay(
                new String[]{
                        "Loading: " + progress + "% ",
                        "█".repeat(progress / 2) + "░".repeat((100 / 2) - (progress / 2)),
                },
                new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.CENTER)
        );
    }
}
