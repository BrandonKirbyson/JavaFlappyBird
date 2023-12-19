package Overlays;

import Overlays.GameScreen;
import Render.*;

public class Screen implements Renderable {
    private final GameScreen gameScreen;

    private int score = 0;

    private static final String[] gameOver = new String[]{
//            "-------------------------------------------------------",
//            "  _____                         ____                 ",
//            " / ____|                       / __ \\                ",
//            "| |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ",
//            "| | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|",
//            "| |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ",
//            " \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ",
//            ""
//
            "+------------------------------------------------------------------------------------------------+",
            "|               ('-.     _   .-')       ('-.                           (`-.      ('-.  _  .-')   |",
            "|              ( OO ).-.( '.( OO )_   _(  OO)                        _(OO  )_  _(  OO)( \\( -O )  |",
            "|  ,----.      / . --. / ,--.   ,--.)(,------.       .-'),-----. ,--(_/   ,. \\(,------.,------.  |",
            "| '  .-./-')   | \\-.  \\  |   `.'   |  |  .---'      ( OO'  .-.  '\\   \\   /(__/ |  .---'|   /`. ' |",
            "| |  |_( O- ).-'-'  |  | |         |  |  |          /   |  | |  | \\   \\ /   /  |  |    |  /  | | |",
            "| |  | .--, \\ \\| |_.'  | |  |'.'|  | (|  '--.       \\_) |  |\\|  |  \\   '   /, (|  '--. |  |_.' | |",
            "|(|  | '. (_/  |  .-.  | |  |   |  |  |  .--'         \\ |  | |  |   \\     /__) |  .--' |  .  '.' |",
            "| |  '--'  |   |  | |  | |  |   |  |  |  `---.         `'  '-'  '    \\   /     |  `---.|  |\\  \\  |",
            "|  `------'    `--' `--' `--'   `--'  `------'           `-----'      `-'      `------'`--' '--' |",
            "+------------------------------------------------------------------------------------------------+",
    };

    private static final String[] mainMenu = new String[]{
            Colors.WHITE.apply("Welcome to Flappy Bird!"),
            Colors.GREEN.apply("Your best score is: 0"),
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
