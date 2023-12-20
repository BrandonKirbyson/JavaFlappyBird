import Render.Renderer;

import java.util.ArrayList;

/**
 * The class for Obstacles (pipes)
 */
public final class Obstacles {
    private final ArrayList<Pipe> pipes = new ArrayList<>();

    private double speed = 0.5;

    /**
     * Creates a new Obstacles object
     */
    public Obstacles() {
        addPipe();
    }

    /**
     * Checks if the bird is colliding with any of the pipes
     *
     * @param birdY the y position of the bird
     * @return if the bird is colliding with any of the pipes
     * @see Pipe#checkCollision(int)
     */
    public boolean checkCollision(int birdY) {
        for (Pipe pipe : pipes) {
            if (pipe.checkCollision(birdY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new pipe to the list of pipes
     */
    private void addPipe() {
        int gapSize = (int) Math.round(Math.random() * 10 + 10);
        int gapY = (int) Math.round(Math.random() * (Renderer.getHeight() - (double) gapSize / 2)) + gapSize / 16;
        pipes.add(new Pipe(gapY, gapSize));
    }

    /**
     * Updates the pipes by moving them to the left
     *
     * @param score the current score
     * @return the new score
     */
    public int update(int score) {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.update(speed);

            if (!pipe.isCleared() && pipe.getX() < Bird.getX()) {
                pipe.setCleared(true);
                score += 1;
                speed += 0.01;
            }

            if (pipe.getX() < 0) {
                pipes.remove(pipe);
            }

            if (!pipe.genNext && pipe.getX() < Renderer.getWidth() - Pipe.SPACING) {
                addPipe();
                pipe.genNext = true;
            }
        }
        return score;
    }

    /**
     * @return the list of pipes
     */
    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    /**
     * Resets the pipes
     */
    public void reset() {
        pipes.clear();
        addPipe();
        speed = 0.5;
    }
}
