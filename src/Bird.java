public class Bird {
    private static final String[] birdArr = {
            " \\  _  ",
            "  \\(.)<",
            "  (___)",
    };
    private static final String[] birdArrFlap = {
            "    _  ",
            "  /(.)<",
            " /(___)",
    };

    private int flap = 0;

    private static final int x = 20;

    private double y = Renderer.getHeight() / 2;

    private double yVel = 0;
    private final static double gravity = 0.05;

    public void update() {
        if (flap > 0) {
            flap -= 1;
        }
        yVel += gravity;
        y += yVel;

        if (y >= Renderer.getHeight() - birdArr.length + 1) {
            y = Renderer.getHeight() - birdArr.length + 1;
            yVel = 0;
        }
    }

    public void jump() {
        yVel = -1;
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
}
