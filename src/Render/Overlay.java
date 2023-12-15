package Render;

public class Overlay {
    private final String[] overlayArr;
    private final Position position;

    public Overlay(String[] overlayArr, Position position) {
        this.overlayArr = overlayArr;
        this.position = position;
    }

    public String[] getOverlayArr() {
        return overlayArr;
    }

    public Position getPosition() {
        return position;
    }
}
