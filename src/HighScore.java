import Render.*;

public class HighScore implements Renderable {
    @Override
    public Overlay getOverlay() {
        return new Overlay(
                new String[]{
                        "High Score: " + HighScoreManager.getHighScore(),
                },

                new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.TOP)
        );
    }
}

