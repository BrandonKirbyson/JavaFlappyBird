package Render;

/**
 * Ansi escape codes for colors
 */
@SuppressWarnings("unused")
public enum Colors {
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    WHITE_BACKGROUND("\u001B[47m"),

    RESET("\u001B[0m");

    private final String color;

    /**
     * Creates a new color
     *
     * @param color the color code
     */
    Colors(String color) {
        this.color = color;
    }

    /**
     * Applies the color to a string
     */
    public String apply(String text) {
        return color + text + RESET.color;
    }

    /**
     * Applies the color to a string array
     *
     * @see Colors#apply(String)
     */
    public String[] apply(String[] text) {
        String[] coloredText = new String[text.length];
        for (int i = 0; i < text.length; i++) {
            coloredText[i] = apply(text[i]);
        }
        return coloredText;
    }
}
