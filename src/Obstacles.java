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
        int gapY = (int) Math.round(Math.random() * (Renderer.getHeight() - 10));
        int gapSize = (int) Math.round(Math.random() * 10 + 10);
        pipes.add(new Pipe(gapY, gapSize));
    }

    public int update(int score) {
        for (Pipe pipe : pipes) {
            pipe.update(speed);

            if (!pipe.isCleared() && pipe.getX() < Bird.getX()) {
                pipe.setCleared(true);
                score += 1;
                speed += 0.01;
            }

            if (pipe.getX() < 0) {
                pipes.remove(pipe);
                addPipe();
                break;
            }
        }
        return score;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }
}
