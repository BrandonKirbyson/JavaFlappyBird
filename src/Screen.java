import Render.Overlay;
import Render.Position;
import Render.Renderable;
import Render.Renderer;

public class Screen implements Renderable {
    private final GameScreen gameScreen;

    public Screen(GameScreen screen) {
        gameScreen = screen;
    }

    @Override
    public Overlay getOverlay() {
        return new Overlay(new String[]{"GAME OVER"},
                new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.LEFT, Position.VerticalAlignment.TOP)
        );
    }
}
