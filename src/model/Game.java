package model;

import controller.DataController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game {
    // Game screen
    public enum GameScreen {
        START_GAME_SCREEN,
        PLAYING_SCREEN,
        GAME_OVER_SCREEN,
    }

    public GameScreen currentScreen = GameScreen.START_GAME_SCREEN;

    public Bird bird;
    public PipeGroup pipeGroup;
    public BufferedImage startGameScreen, gameOverScreen;
    public BufferedImage backgroundImage, foregroundImage;

    public DataController dataController;
    public final String fileName = "SCORE";

    // player
    public int score;
    public int highScore;

    public Game() {
        dataController = new DataController();
        highScore = dataController.readScoreFromFile(fileName);
        bird = new Bird();
        pipeGroup = new PipeGroup();
        score = 0;
        try {
            startGameScreen = ImageIO.read(new File("lib/start_game_screen.png"));
            gameOverScreen = ImageIO.read(new File("lib/game_over_screen.png"));
            backgroundImage = ImageIO.read(new File("lib/background.png"));
            foregroundImage = ImageIO.read(new File("lib/foreground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCollision() {
        for (int i = 0; i < 3; i++) {
            if (bird.birdRect.intersects(pipeGroup.getPipeIndex(i).pipeRect)) {
                bird.isAlive = false;
            }
        }
        if (bird.y >= 420) {
            bird.isAlive = false;
        }
    }

    public void handleIncreaseScore() {
        for (int i = 0; i < 3; i++) {
            if (bird.x > pipeGroup.getPipeIndex(i).x && !pipeGroup.getPipeIndex(i).isBehind &&
                    pipeGroup.getPipeIndex(i).position.equals("bot")) {
                this.score++;
                pipeGroup.getPipeIndex(i).isBehind = true;
            }

        }
    }

    public void resetScore() {
        this.score = 0;
        this.highScore = dataController.readScoreFromFile(fileName);
    }

    public void restart() {
        resetScore();
        bird.reset();
        pipeGroup.reset();
    }

    public void update() {
        if (this.currentScreen == GameScreen.PLAYING_SCREEN) {
            if (bird.isAlive) {
                bird.update();
                pipeGroup.update();
                handleCollision();
                handleIncreaseScore();
            }

            if (!bird.isAlive) {
                if (score > highScore) {
                    dataController.writeScoreToFile(score, fileName);
                }

                this.currentScreen = GameScreen.GAME_OVER_SCREEN;
            }
        }
    }
}
