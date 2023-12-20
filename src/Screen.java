import Render.*;

public class Screen implements Renderable {
    private final GameScreen gameScreen;

    private static String centerString(String s) {
        int totalPadding = (Screen.rowWidth - s.length());
        int paddingEachSide = totalPadding / 2;
        String repeat = " ".repeat(paddingEachSide);
        String extraSpace = (totalPadding % 2 == 1) ? " " : "";
        return repeat + s + repeat + extraSpace;
    }

    private final int score;

    private static final int rowWidth = 53;
    private static final int highScore = HighScoreManager.getHighScore();


    // Unused
    private static final String[] mainMenu = new String[]{
            Colors.WHITE.apply("Welcome to Flappy Bird!"),
            Colors.GREEN.apply("Your best score is: 0"),
    };

    public Screen(GameScreen screen, int s) {
        gameScreen = screen;
        score = s;
    }

    @Override
    public Overlay getOverlay() {
        final String[] gameOver = new String[]{
                "+-----------------------------------------------------+",
                "|                                                     |",
                "|   ____                         ___                  |",
                "|  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __  |",
                "| | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |",
                "| | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |    |",
                "|  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|    |",
                "|                                                     |",
                "|                                                     |",
                "|" + centerString(("High Score: " + highScore)) + "|",
                "|                                                     |",
                "|" + centerString(("Your Score: " + score)) + "|",
                "|                                                     |",
                "|                                                     |",
                "|                                                     |",
                "|                                                     |",
                "|                                                     |",
                "|                                                     |",
                "|                                                     |",
                "+-----------------------------------------------------+",
        };

        return switch (gameScreen) {
            case MAIN_MENU -> new Overlay(mainMenu,
                    new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.CENTER)
            );
            case GAME -> new Overlay(
                    new String[]{"< Score: " + score + " >"},
                    new Position(Renderer.getWidth(),
                            0,
                            Position.HorizontalAlignment.RIGHT,
                            Position.VerticalAlignment.TOP));
            case GAME_OVER -> new Overlay(gameOver,
                    new Position(Renderer.getWidth() / 2, Renderer.getHeight() / 2, Position.HorizontalAlignment.MIDDLE, Position.VerticalAlignment.CENTER)
            );
        };
    }
}
