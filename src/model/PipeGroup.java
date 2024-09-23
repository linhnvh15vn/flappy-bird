package model;

import java.awt.*;
import java.util.ArrayList;

public class PipeGroup {
    public ArrayList<Pipe> pipes;

    private static final int min = 200; // random pipe height
    private static final int max = 350; // // random pipe height

    public PipeGroup() {
        pipes = new ArrayList<>();
        Pipe pipe;

        for (int i = 0; i < 3; i++) {
            int yBot = (int) ((Math.random() * ((max - min) + 1)) + min);
            int yTop = yBot - 500 - Pipe.PIPE_SPACE;
            pipe = new Pipe(600 + i * Pipe.PIPE_DISTANCE, yBot, "bot");
            pipes.add(pipe);
            pipe = new Pipe(600 + i * Pipe.PIPE_DISTANCE, yTop, "top");
            pipes.add(pipe);
        }
    }

    public Pipe getPipeIndex(int index) {
        return pipes.get(index);
    }

    public void reset() {
        pipes = new ArrayList<>();
        Pipe pipe;

        for (int i = 0; i < 3; i++) {
            int yBot = (int) ((Math.random() * ((max - min) + 1)) + min);
            int yTop = yBot - 500 - Pipe.PIPE_SPACE;
            pipe = new Pipe(600 + i * Pipe.PIPE_DISTANCE, yBot, "bot");
            pipes.add(pipe);
            pipe = new Pipe(600 + i * Pipe.PIPE_DISTANCE, yTop, "top");
            pipes.add(pipe);
        }
    }

    public void update() {
        int yBot = (int) ((Math.random() * ((max - min) + 1)) + min);
        int yTop = yBot - 500 - Pipe.PIPE_SPACE;

        for (int i = 0; i < 6; i++) {
            pipes.get(i).update();
        }
        // if pipes[0] is run out of screen, remove it and push a new pipe at last in array
        if (pipes.get(0).x < -pipes.get(0).PIPE_WIDTH) {
            pipes.remove(0);
            pipes.remove(0);
            pipes.add(new Pipe(pipes.get(2).x + Pipe.PIPE_DISTANCE, yBot, "bot"));
            pipes.add(new Pipe(pipes.get(2).x + Pipe.PIPE_DISTANCE, yTop, "top"));
        }
    }

    public void paint(Graphics g) {
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.pipeImage, pipe.x, pipe.y, null);
        }
    }
}
