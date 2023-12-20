package Render;

/**
 * A position on the screen
 */
public final class Position {
    public enum HorizontalAlignment {
        LEFT,
        MIDDLE,
        RIGHT,
    }

    public enum VerticalAlignment {
        TOP,
        CENTER,
        BOTTOM,
    }

    private final int x;
    private final int y;

    private final HorizontalAlignment horizontalAlignment;
    private final VerticalAlignment verticalAlignment;

    /**
     * Creates a new Position object
     *
     * @param x                   the x position
     * @param y                   the y position
     * @param horizontalAlignment the horizontal alignment
     * @param verticalAlignment   the vertical alignment
     */
    public Position(int x, int y, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.x = x;
        this.y = y;

        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    /**
     * Creates a new Position object with centre alignment
     *
     * @param x the x position
     * @param y the y position
     */
    public Position(int x, int y) {
        this(x, y, HorizontalAlignment.MIDDLE, VerticalAlignment.CENTER);
    }

    /**
     * @return the x position
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y position
     */
    public int getY() {
        return y;
    }

    /**
     * @return the horizontal alignment
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * @return the vertical alignment
     */
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }
}
