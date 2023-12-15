package Overlays;

import Overlays.GameScreen;
import Render.Overlay;
import Render.Position;
import Render.Renderable;
import Render.Renderer;

public class Screen implements Renderable {
    private final GameScreen gameScreen;

    private int score = 0;

    private static final String[] gameOver = new String[]{
            "  _____                         ____                 ",
            " / ____|                       / __ \\                ",
            "| |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ",
            "| | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|",
            "| |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ",
            " \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ",
    };

    private static final String[] mainMenu = new String[]{
            "Welcome to Flappy Bird!",
            "1. Play",
            "2. Scoreboard",
    };

    public Screen(GameScreen screen) {
        gameScreen = screen;
    }

    public Screen(GameScreen screen, int s) {
        gameScreen = screen;
        score = s;
    }

    @Override
    public Overlay getOverlay() {
        return switch (gameScreen) {
            case MAIN_MENU -> new Overlay(mainMenu,
                    new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.TOP)
            );
            case GAME ->
                    new Overlay(new String[]{"Score: " + score}, new Position(Renderer.getWidth(), 0, Position.HorizontalAlignment.RIGHT, Position.VerticalAlignment.TOP));
            case GAME_OVER -> new Overlay(gameOver,
                    new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.TOP)
            );
        };
    }
}
