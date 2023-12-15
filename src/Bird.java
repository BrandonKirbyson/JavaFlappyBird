import Render.*;

public class Bird implements Renderable {
    private static final String[] birdArr = Colors.YELLOW.apply(new String[]{
            "\\  _  ",
            " \\(" + Colors.WHITE.apply(".") + Colors.YELLOW.apply(")") + Colors.RED.apply("<"),
            " (" + Colors.WHITE.apply("___") + Colors.YELLOW.apply(")"),
    });
    private static final String[] birdArrFlap = Colors.YELLOW.apply(new String[]{
            "   _  ",
            " /(" + Colors.WHITE.apply(".") + Colors.YELLOW.apply(")") + Colors.RED.apply("<"),
            "/(" + Colors.WHITE.apply("___") + Colors.YELLOW.apply(")"),
    });

    private boolean isDead = false;

    private int flap = 0;

    private static final int x = 20;

    private double y = (double) Renderer.getHeight() / 2;

    private double yVel = 0;
    private final static double gravity = 120;
    private final static double jumpVel = 40;

    public void update() {
        if (flap > 0) {
            flap -= 1;
        }
        yVel += gravity / Game.FPS;
        y += yVel / Game.FPS;

        if (y >= Renderer.getHeight() - birdArr.length + 1) {
            y = Renderer.getHeight() - birdArr.length + 1;
            yVel = 0;
            isDead = true;
        }
    }

    public void jump() {
        yVel = -jumpVel;
        flap = 12;
    }

    public String[] getBirdDrawing() {
        return flap > 0 ? birdArrFlap : birdArr;
    }

    public static int getX() {
        return x;
    }

    public int getY() {
        return (int) Math.round(y);
    }

    public boolean isDead() {
        return isDead;
    }

    @Override
    public Overlay getOverlay() {
        return new Overlay(getBirdDrawing(), new Position(getX(), getY()));
    }
}
