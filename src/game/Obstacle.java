package game;

import java.awt.*;

/**
 * کلاس موانع بازی دایناسور (مثل کاکتوس)
 * این کلاس برای نگهداری اطلاعات موانع استفاده می‌شود
 */
public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Image image;

    /**
     * سازنده کلاس موانع
     *
     * @param x موقعیت افقی
     * @param y موقعیت عمودی
     * @param width عرض
     * @param height ارتفاع
     * @param speed سرعت حرکت
     * @param image تصویر مانع
     */
    public Obstacle(int x, int y, int width, int height, int speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
    }

    /**
     * حرکت مانع به سمت چپ
     */
    public void move() {
        x -= speed;
    }

    /**
     * رسم مانع
     *
     * @param g شیء گرافیکی برای رسم
     */
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    /**
     * بررسی خارج شدن مانع از صفحه
     *
     * @return true اگر مانع از سمت چپ صفحه خارج شده باشد
     */
    public boolean isOffScreen() {
        return x < -width;
    }

    /**
     * گرفتن محدوده مانع برای تشخیص برخورد
     *
     * @return شیء Rectangle که محدوده مانع را نشان می‌دهد
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Getters و Setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}