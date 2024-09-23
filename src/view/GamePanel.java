package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable, MouseListener {

    public Game game;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
        this.addMouseListener(this);
    }

    public void update() {
        game.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw screen
        if (game.currentScreen == Game.GameScreen.START_GAME_SCREEN) {
            g.drawImage(game.startGameScreen, 0, 0, null);
        } else if (game.currentScreen == Game.GameScreen.PLAYING_SCREEN) {
            g.drawImage(game.backgroundImage, 0, 0, null);
            g.drawImage(game.bird.birdImage, game.bird.x, game.bird.y, null);
            game.pipeGroup.paint(g);
            g.drawImage(game.foregroundImage, 0, 0, null);
            // draw score
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + this.game.score, 10, 30);
            g.drawString("High score: " + this.game.highScore, 10, 50);
        } else if (game.currentScreen == Game.GameScreen.GAME_OVER_SCREEN) {
            g.drawImage(game.gameOverScreen, 0, 0, null);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.update();
                Thread.sleep(15);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (game.currentScreen == Game.GameScreen.START_GAME_SCREEN) {
            game.restart();
            game.currentScreen = Game.GameScreen.PLAYING_SCREEN;
        } else if (game.currentScreen == Game.GameScreen.PLAYING_SCREEN) {
            this.game.bird.jump();
        } else if (game.currentScreen == Game.GameScreen.GAME_OVER_SCREEN) {
            game.currentScreen = Game.GameScreen.START_GAME_SCREEN;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
