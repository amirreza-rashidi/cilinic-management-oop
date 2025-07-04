package game;


import javax.swing.*;

/**
 * کلاس اصلی بازی دایناسور که مسئول ایجاد پنجره بازی است
 */
public class DinoGame {

    /**
     * ایجاد و نمایش پنجره بازی دایناسور
     */
    public static void startGame() {
        JFrame frame = new JFrame("بازی  با دکتر  مولایی");
        DinoPanel gamePanel = new DinoPanel();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // فقط پنجره بازی بسته می‌شود
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}