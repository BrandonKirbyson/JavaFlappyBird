package Render;

public class Position {
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

    public Position(int x, int y, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.x = x;
        this.y = y;

        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public Position(int x, int y) {
        this(x, y, HorizontalAlignment.MIDDLE, VerticalAlignment.CENTER);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }
}
