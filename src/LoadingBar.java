import Render.*;

/**
 * Loading bar screen
 */
public final class LoadingBar implements Renderable {
    private final int progress;

    /**
     * Creates a new LoadingBar object
     *
     * @param progress the progress of the loading bar
     */
    public LoadingBar(int progress) {
        this.progress = progress;
    }

    /**
     * @return the loading bar based on the progress value
     */
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
