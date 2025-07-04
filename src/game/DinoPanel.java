package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class DinoPanel extends JPanel implements ActionListener, KeyListener {

    private int dinoX = 50;
    private int dinoY = 200;
    private int dinoWidth = 120;
    private int dinoHeight = 140;
    private boolean jumping = false;
    private int jumpSpeed = 0;
    private int groundY = 200;

    private int cactusX = 800;
    private int cactusY = 260;
    private int cactusWidth = 80;
    private int cactusHeight = 90;

    private Timer timer;
    private Random rand;

    private Image dinoImage;
    private Image cactusImage;

    private boolean gameOver = false;

    // برای نگهداری امتیاز
    private int score = 0;


    public DinoPanel() {
        this.setPreferredSize(new Dimension(800, 400));
        this.setBackground(new Color(64, 224, 208)); // Turquoise (فیروزه‌ای جذاب)
        this.setFocusable(true);
        this.addKeyListener(this);

        // بارگذاری تصاویر
        loadImages();

        timer = new Timer(20, this);
        timer.start();

        rand = new Random();
    }


    private void loadImages() {
        try {
            // بارگذاری تصاویر از پوشه game در داخل src
            dinoImage = new ImageIcon(getClass().getResource("/game/d.png")).getImage();
            cactusImage = new ImageIcon(getClass().getResource("/game/m.png")).getImage();
        } catch (Exception e) {
            // اگر تصاویر یافت نشدند، از شکل‌های ساده استفاده می‌کنیم
            System.err.println("خطا در بارگذاری تصاویر: " + e.getMessage());
            dinoImage = null;
            cactusImage = null;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // رسم زمین
        g.setColor(Color.GRAY);
        g.fillRect(0, groundY + dinoHeight, getWidth(), 10);

        // رسم امتیاز
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("امتیاز: " + score, 650, 50);

        if (!gameOver) {
            // رسم دایناسور
            if (dinoImage != null) {
                g.drawImage(dinoImage, dinoX, dinoY, dinoWidth, dinoHeight, null);
            } else {
                g.setColor(Color.GREEN);
                g.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);
            }

            // رسم کاکتوس
            if (cactusImage != null) {
                g.drawImage(cactusImage, cactusX, cactusY, cactusWidth, cactusHeight, null);
            } else {
                g.setColor(Color.RED);
                g.fillRect(cactusX, cactusY, cactusWidth, cactusHeight);
            }
        } else {
            // رسم پیام پایان بازی با استایل بهبود یافته

            // پس‌زمینه نیمه‌شفاف
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
            g.setColor(new Color(0, 0, 50));
            g.fillRoundRect(100, 50, 600, 300, 30, 30);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            // عنوان - پایان بازی
            g.setColor(new Color(255, 70, 70));
            g.setFont(new Font("B Titr", Font.BOLD, 42));
            FontMetrics titleMetrics = g.getFontMetrics();
            String gameOverText = "بازی شما تمام شد!";
            int gameOverX = (getWidth() - titleMetrics.stringWidth(gameOverText)) / 2;
            g.drawString(gameOverText, gameOverX, 110);

            // نمایش امتیاز
            g.setColor(new Color(255, 215, 0)); // طلایی
            g.setFont(new Font("B Nazanin", Font.BOLD, 36));
            FontMetrics scoreMetrics = g.getFontMetrics();
            String scoreText = "امتیاز نهایی: " + score;
            int scoreX = (getWidth() - scoreMetrics.stringWidth(scoreText)) / 2;
            g.drawString(scoreText, scoreX, 170);

            // دستورالعمل شروع مجدد
            g.setColor(new Color(200, 255, 200)); // سبز روشن
            g.setFont(new Font("B Nazanin", Font.PLAIN, 28));
            FontMetrics instructionMetrics = g.getFontMetrics();
            String restartText = "برای شروع مجدد، کلید اینتر را فشار دهید";
            int restartX = (getWidth() - instructionMetrics.stringWidth(restartText)) / 2;
            g.drawString(restartText, restartX, 240);

            // خط جداکننده
            g.setColor(new Color(100, 150, 255));
            g.drawLine(150, 270, 650, 270);



            // متن اصلی
            g.setColor(new Color(173, 255, 47)); // آبی روشن
            g.setFont(new Font("Arial", Font.BOLD, 28));
            FontMetrics nameMetrics = g.getFontMetrics();
            String nameText = "AMIRREZA RASHIDI";
            int nameX = (getWidth() - nameMetrics.stringWidth(nameText)) / 2;
            g.drawString(nameText, nameX, 310);

            // اضافه کردن قاب بیرونی با گرادیان
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    100, 50, new Color(0, 0, 100),
                    700, 350, new Color(50, 0, 80)
            );
            g2.setPaint(gp);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(100, 50, 600, 300, 30, 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // حرکت کاکتوس به سمت چپ
            cactusX -= 9;

            // افزایش امتیاز
            score++;

            // منطق پرش دایناسور
            if (jumping) {
                dinoY += jumpSpeed;
                jumpSpeed += 1;
                if (dinoY >= groundY) {
                    dinoY = groundY;
                    jumping = false;
                }
            }

            // بازنشانی موقعیت کاکتوس
            if (cactusX < -cactusWidth) {
                cactusX = 800 + rand.nextInt(300);
            }

            // بررسی برخورد
            checkCollision();
        }
        repaint();
    }

    /**
     * بررسی برخورد دایناسور با کاکتوس
     */
    private void checkCollision() {
        Rectangle dinoRect = new Rectangle(dinoX, dinoY, dinoWidth, dinoHeight);
        Rectangle cactusRect = new Rectangle(cactusX, cactusY, cactusWidth, cactusHeight);

        if (dinoRect.intersects(cactusRect)) {
            gameOver = true;
            timer.stop();
        }
    }

    /**
     * شروع مجدد بازی
     */
    private void restartGame() {
        dinoY = groundY;
        cactusX = 800;
        jumping = false;
        gameOver = false;
        score = 0;
        timer.start();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && !jumping && e.getKeyCode() == KeyEvent.VK_SPACE) {
            // پرش با فشار کلید فاصله
            jumping = true;
            jumpSpeed = -24;
        } else if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
            // شروع مجدد بازی با فشار کلید Enter
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}