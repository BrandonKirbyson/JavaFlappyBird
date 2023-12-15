package Render;

import java.util.Arrays;

public class Renderer {
    private static final int width = 100;
    private static final int height = 30;

    private static String[] frame;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private static String[] getBlankFrame() {
        String line = "|" + " ".repeat(width) + "|";
        String horizontalLine = "|" + "-".repeat(width) + "|";
        String[] frame = new String[height];
        Arrays.fill(frame, line);
        frame[0] = horizontalLine;
        frame[height - 1] = horizontalLine;
        return frame;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void newFrame() {
        frame = getBlankFrame();
    }

    public static <T extends Renderable> void render(T[] overlayObjects) {
        newFrame();

        for (final Renderable obj : overlayObjects) {
            addOverlay(obj.getOverlay());
        }

        clearScreen();
        System.out.println(String.join("\n", frame));
    }

    private static void addOverlay(Overlay overlay) {
        final String[] overlayArr = overlay.getOverlayArr();
        final Position position = overlay.getPosition();

        final int xOffset = switch (position.getHorizontalAlignment()) {
            case LEFT -> 0;
            case MIDDLE -> -overlayArr[0].length() / 2;
            case RIGHT -> -overlayArr[0].length();
        };
        final int yOffset = switch (position.getVerticalAlignment()) {
            case TOP -> 0;
            case CENTER -> -overlayArr.length / 2;
            case BOTTOM -> -overlayArr.length;
        };
        
        for (int i = 0; i < overlayArr.length; i++) {
            final int row = position.getY() + i + yOffset;
            if (row < 0 || row >= frame.length) {
                continue;
            }
            final String overlayRow = overlayArr[i];
            for (int j = 0; j < overlayRow.length(); j++) {
                final int col = position.getX() + j + xOffset;
                if (col < 0 || col >= frame[row].length()) {
                    continue;
                }
                final char overlayChar = overlayRow.charAt(j);
                if (overlayChar != ' ') {
                    frame[row] = frame[row].substring(0, col) + overlayChar + frame[row].substring(col + 1);
                }
            }
        }
    }
}
