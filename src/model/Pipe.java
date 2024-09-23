package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pipe {

    public final int PIPE_WIDTH = 66;
    public final int PIPE_HEIGHT = 400;
    public static int PIPE_DISTANCE = 200;
    public static final int PIPE_SPACE = 50;

    public int x;
    public int y;
    public boolean isBehind;
    public String position;
    public Rectangle pipeRect;
    public BufferedImage pipeImage;


    public Pipe(int x, int y, String position) {
        pipeRect = new Rectangle(this.x, this.y, this.PIPE_WIDTH, this.PIPE_HEIGHT);
        this.x = x;
        this.y = y;
        this.position = position;
        this.isBehind = false;

        try {
            if (this.position.equals("bot")) {
                this.pipeImage = ImageIO.read(new File("lib/pipe_" + position + ".png"));
            } else {
                this.pipeImage = ImageIO.read(new File("lib/pipe_top.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        pipeRect.setLocation(this.x, this.y);
        this.x -= 3;
    }
}
