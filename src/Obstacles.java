import Render.Renderer;

import java.util.ArrayList;

public class Obstacles {
    private final ArrayList<Pipe> pipes = new ArrayList<>();

    private double speed = 0.5;

    public Obstacles() {
        addPipe();
    }

    public boolean checkCollision(int birdY) {
        for (Pipe pipe : pipes) {
            if (pipe.checkCollision(birdY)) {
                return true;
            }
        }
        return false;
    }

    private void addPipe() {
        int gapSize = (int) Math.round(Math.random() * 10 + 10);
        int gapY = (int) Math.round(Math.random() * (Renderer.getHeight() - gapSize)) + gapSize / 2;
        pipes.add(new Pipe(gapY, gapSize));
    }

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

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }
}
