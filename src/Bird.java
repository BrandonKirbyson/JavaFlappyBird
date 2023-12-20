import Render.*;

import java.util.HashMap;

/**
 * The bird
 */
public class Bird implements Renderable {
    private static final int width = 4;
    private static final int height = 4;


    private static final HashMap<Integer, String[]> birdArrMap = new HashMap<>() {{
        put(0,new String[]{
                "\\   ",
                " \\(.)<",
                " (__) ",
        });
        put(1,new String[]{
                "    ",
                "  (.)<",
                "/(__) ",
        });
    }};

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
    private final static double gravity = 100;
    private final static double jumpVel = 30;

    /**
     * Every frame the bird decrements its flap counter and goes down. Jumping resets this
     */
    public void update() {
        if (flap > 0) {
            flap -= 1;
        }
        yVel += gravity / Game.FPS;
        y += yVel / Game.FPS;

        if (y >= Renderer.getHeight() - (birdArr.length)) {
            y = Renderer.getHeight() - (birdArr.length);
            yVel = 0;
            isDead = true;
        }
        if(y <= birdArr.length / 2){
            y = birdArr.length / 2;
            yVel = 0;
        }
    }

    /**
     * Makes the bird jump
     */
    public void jump() {
        yVel = -jumpVel;
        flap = 12;
    }

    /**
     * @return the bird drawing, whether it is flapping or not
     */
    public String[] getBirdDrawing() {
        return flap > 0 ? birdArrMap.get(1) : birdArrMap.get(0);
    }

    /**
     * @return the x position of the bird
     */
    public static int getX() {
        return x;
    }

    /**
     * @return the y position of the bird
     */
    public int getY() {
        return (int) Math.round(y);
    }

    /**
     * @return the width and height of the bird
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return the width and height of the bird
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return if the bird is dead
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets if the bird is dead
     *
     * @param dead if the bird is dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Resets the bird to the middle of the screen
     */
    public void reset() {
        y = (double) Renderer.getHeight() / 2;
        yVel = 0;
        isDead = false;
    }

    /**
     * @return the overlay of the bird
     */
    @Override
    public Overlay getOverlay() {
        return new Overlay(getBirdDrawing(), new Position(getX(), getY()));
    }
}
