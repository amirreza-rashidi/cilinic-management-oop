package ui;

//-----------------------------------------------------

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//-----------------------------------------------------

public class ClinicInfoPage extends JFrame {

    public ClinicInfoPage() {

       //پنجره اصلی
        setTitle("اطلاعات درمانگاه");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

//----------------------------------------------------------
       //پنل زیری
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0xE0F2F1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//-------------------------------------------------------------------------------------------

        // پنل محتواها
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 1, 10, 15));   //ساخت جدول
        contentPanel.setBackground(new Color(0xE0F2F1));

//-------------------------------------------------------------------------------------------
        // اطلاعات درمانگاه
        addInfoLabel(contentPanel, "نام درمانگاه: درمانگاه تخصصی شبانه‌روزی رازی");
        addInfoLabel(contentPanel, "آدرس: کرمانشاه بلوار طاقبستان");
        addInfoLabel(contentPanel, "تلفن: 09051574252");
        addInfoLabel(contentPanel, "ساعات کاری: شبانه‌روزی");
        addInfoLabel(contentPanel, "خدمات: درمانگاه عمومی، تخصصی و اورژانس");


        panel.add(contentPanel, BorderLayout.CENTER);
//-------------------------------------------------------------------------------------------

        // پنل دکمه‌ها
        JPanel buttonPanel = new JPanel();


        // دکمه نمایش در گوگل مپ
        JButton googleMapsButton = new JButton("نمایش در گوگل مپ");
        googleMapsButton.setBackground(new Color(0x009688));
        googleMapsButton.setForeground(Color.WHITE);
        googleMapsButton.setFont(new Font("B Nazanin", Font.PLAIN, 16));
        googleMapsButton.setFocusPainted(false);//خط دور دکمه هنگام با ماوس رفتن روش
        buttonPanel.add(googleMapsButton);

        // دکمه تماس با ما
        JButton contactUsButton = new JButton("تماس با ما");
        contactUsButton.setBackground(new Color(0x009688));
        contactUsButton.setForeground(Color.WHITE);
        contactUsButton.setFont(new Font("B Nazanin", Font.PLAIN, 16));
        buttonPanel.add(contactUsButton);

        // دکمه تلگرام
        JButton telegramButton = new JButton("پیام در تلگرام");
        telegramButton.setBackground(new Color(0x4285F4));
        telegramButton.setForeground(Color.WHITE);
        telegramButton.setFont(new Font("B Nazanin", Font.PLAIN, 16));
        buttonPanel.add(telegramButton);

        // دکمه سفر با اسنپ
        JButton snappButton = new JButton("سفر با اسنپ");
        snappButton.setBackground(new Color(0x00C853));
        snappButton.setForeground(Color.WHITE);
        snappButton.setFont(new Font("B Nazanin", Font.PLAIN, 16));
        buttonPanel.add(snappButton);


        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        // اکشن گوگل مپ
        googleMapsButton.addActionListener(e -> {
            try {
                URI uri = new URI("https://www.google.com/maps?q=کرمانشاه+بلوار+طاقبستان+درمانگاه+رازی");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "خطا در باز کردن مرورگر: " + ex.getMessage(), "خطا", JOptionPane.ERROR_MESSAGE);
            }
        });

        // اکشن تماس با ما
        contactUsButton.addActionListener(e -> {
            try {
                File htmlFile = new File("D:\\team\\src\\java.html");
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "خطا در باز کردن فایل: " + ex.getMessage(), "خطا", JOptionPane.ERROR_MESSAGE);
            }
        });

        // اکشن سفر با اسنپ
        snappButton.addActionListener(e -> {
            try {
                URI uri = new URI("https://snapp.ir");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "خطا در باز کردن مرورگر: " + ex.getMessage(), "خطا", JOptionPane.ERROR_MESSAGE);
            }
        });

        // اکشن تلگرام
        telegramButton.addActionListener(e -> {
            try {
                URI uri = new URI("https://t.me/amir2006_reza");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "خطا در باز کردن مرورگر: " + ex.getMessage(), "خطا", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


   private void addInfoLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("B Nazanin", Font.PLAIN, 16));
        label.setForeground(new Color(0x00695C));
        label.setHorizontalAlignment(SwingConstants.RIGHT);// متن راست چین
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x80CBC4)),//خط برای دور لیبل ها
                BorderFactory.createEmptyBorder(5, 10, 5, 10)  //فضای خالی برای هر لیبل
        ));
        panel.add(label);
    }

}