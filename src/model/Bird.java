package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird {
    public final int BIRD_WIDTH = 45;
    public final int BIRD_HEIGHT = 45;
    public final double GRAVITY = 0.5;

    public int x;
    public int y;
    public double velocity;
    public boolean isAlive;
    public Rectangle birdRect;
    public BufferedImage birdImage;

    public Bird() {
        birdRect = new Rectangle(this.x, this.y, this.BIRD_WIDTH, this.BIRD_HEIGHT);
        this.x = 100;
        this.y = 100;
        this.isAlive = true;
        this.velocity = 0;
        try {
            birdImage = ImageIO.read(new File("lib/inu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        birdRect = new Rectangle(this.x, this.y, this.BIRD_WIDTH, this.BIRD_HEIGHT);
        this.x = 100;
        this.y = 100;
        this.isAlive = true;
        this.velocity = 0;
    }

    public void update() {
        this.birdRect.setLocation(this.x, this.y);

        this.velocity += GRAVITY;
        this.y += (int) velocity;
    }
    public void jump() {
        velocity = -8;
    }

}
