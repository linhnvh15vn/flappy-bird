package view;

import javax.swing.*;

public class App extends JFrame {

    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 500;

    public GamePanel gamePanel;

    public App() {
        gamePanel = new GamePanel();

        this.setTitle("Phờ Láp Bi Đóc");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.add(gamePanel);
    }

    public static void main(String[] args) {
        new App();
    }
}
