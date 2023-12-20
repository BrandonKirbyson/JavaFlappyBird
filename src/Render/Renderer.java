package Render;

import java.util.Arrays;

/**
 * The main class for rendering the game
 */
public final class Renderer {
    private static final int width = 100;
    private static final int height = 30;

    private static String[] frame;

    /**
     * @return the width of the game screen
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return the height of the game screen
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return a blank frame that has a boarder
     */
    private static String[] getBlankFrame() {
        String line = "|" + " ".repeat(width) + "|";
        String horizontalLine = "|" + "=".repeat(width) + "|";
        String[] frame = new String[height];
        Arrays.fill(frame, line);
        frame[0] = horizontalLine;
        frame[height - 1] = horizontalLine;
        return frame;
    }

    /**
     * Clears the screen
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    /**
     * Reassigns the frame to a blank frame
     */
    private static void newFrame() {
        frame = getBlankFrame();
    }

    /**
     * Renders the game by looping through the objects and adding them to the frame
     *
     * @param overlayObjects the objects to render
     */
    public static void render(Renderable[] overlayObjects) {
        newFrame();

        for (final Renderable obj : overlayObjects) {
            addOverlay(obj.getOverlay());
        }

        clearScreen();
        System.out.println(String.join("\n", frame));
    }

    /**
     * Gets the real length of a string by ignoring the color codes
     *
     * @param str the string to get the length of
     * @return the real length of the string
     */
    private static int getRealLength(String str) {
        int length = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\u001B') {
                while (str.charAt(i) != 'm') {
                    i++;
                }
                continue;
            }
            length++;
        }
        return length;
    }

    /**
     * Removes the color codes from a string array
     *
     * @param strs the string array to remove the color codes from
     * @return the string array without the color codes
     */
    public static String[] filterColor(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            strs[i] = strs[i].replaceAll("\u001B\\[[;\\d]*m", "");
        }
        return strs;
    }

    /**
     * Adds an overlay to the frame
     */
    private static void addOverlay(Overlay overlay) {
        String[] overlayArr = overlay.overlayArr();
        final Position position = overlay.position();

        filterColor(overlayArr);

        int length = overlayArr.length;

        int xLength = 0;
        for (String s : overlayArr) {
//            xLength = Math.max(xLength, getRealLength(s));
            xLength = Math.max(xLength, s.length());
        }

        // Calculate the offset
        final int xOffset = switch (position.getHorizontalAlignment()) {
            case LEFT -> 0;
            case MIDDLE -> -xLength / 2;
            case RIGHT -> -xLength;
        };
        final int yOffset = switch (position.getVerticalAlignment()) {
            case TOP -> 0;
            case CENTER -> -length / 2;
            case BOTTOM -> -length;
        };

        // Put the overlay into the frame
        for (int i = 0; i < overlayArr.length; i++) {
            final int row = position.getY() + i + yOffset;
            if (row <= 0 || row >= frame.length - 1) {
                continue;
            }
            final String overlayRow = overlayArr[i];
//            int spacing = overlayRow.length() - getRealLength(overlayRow);
//            System.out.println(spacing);
//            overlayArr[i] = " ".repeat(spacing / 2) + overlayRow + " ".repeat(spacing / 2);
//            overlayRow = overlayArr[i];
            for (int j = 0; j < overlayRow.length(); j++) {
                final int col = position.getX() + j + xOffset;
                if (col <= 0 || col >= frame[row].length() - 1) {
                    continue;
                }
                final char overlayChar = overlayRow.charAt(j);
                if (overlayChar == ' ' && overlayRow.substring(0, j).trim().isEmpty() && overlayRow.substring(j + 1).trim().isEmpty()) {
                    continue;
                }
                frame[row] = frame[row].substring(0, col) + overlayChar + frame[row].substring(col + 1);
            }
        }
    }

    /**
     * Hides the cursor
     */
    public static void hideCursor() {
        runCommand("tput civis", "Error hiding cursor ");
    }

    /**
     * Shows the cursor
     */
    public static void showCursor() {
        runCommand("tput cnorm", "Error showing cursor ");
    }

    /**
     * Clears the game
     */
    public static void clearGame() {
        runCommand("clear && printf \"\\e[3j\"", "Error showing cursor ");
    }

    /**
     * Runs a command
     *
     * @param command      the command to run
     * @param errorMessage the error message to print if the command fails
     */
    private static void runCommand(String command, String errorMessage) {
        try {
            new ProcessBuilder("bash", "-c", command).inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(errorMessage + e);
        }
    }
}
