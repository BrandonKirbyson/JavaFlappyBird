import Render.*;

/**
 * High score screen
 */
public final class HighScore implements Renderable {
    /**
     * @return the overlay to render
     * @see HighScoreManager
     */
    @Override
    public Overlay getOverlay() {
        return new Overlay(
                new String[]{
                        "High Score: " + HighScoreManager.getHighScore(),
                },

                new Position(Renderer.getWidth() / 2, (Renderer.getHeight() / 2) - 10, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.BOTTOM)
        );
    }
}

