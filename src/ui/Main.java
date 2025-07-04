package ui;

//--------------------------------------------------------------------------------
import game.DinoGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
//--------------------------------------------------------------------------------

public class Main {
    public static void main(String[] args) {
        // تنظیم رنگ و فونت کلی
        UIManager.put("Button.font", new Font("B Nazanin", Font.PLAIN, 18));
        UIManager.put("Button.background", new Color(0x009688));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", new Color(0xE0F2F1));
        UIManager.put("Label.font", new Font("B Nazanin", Font.PLAIN, 18));
        UIManager.put("OptionPane.messageFont", new Font("B Nazanin", Font.PLAIN, 16));

       // SwingUtilities.invokeLater(() -> {   // نبود این متد برای یو ای های سبک مشکلی ندارد
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
       // });
    }
}

class MainFrame extends JFrame {
    public MainFrame() {
//--------------------------------------------------------------------------------
      //فریم اصلی
        setTitle("سیستم مدیریت درمانگاه");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//--------------------------------------------------------------------------------
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x08EFA6));
        JLabel titleLabel = new JLabel("سیستم مدیریت درمانگاه", SwingConstants.CENTER);
        titleLabel.setFont(new Font("B Nazanin", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0x004D40));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

//--------------------------------------------------------------------------------
//موقعیت مکانی دکمه ها
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 60, 4));  //  6 ردیف
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));

//--------------------------------------------------------------------------------

        ImageIcon icon = null;
        JLabel imageLabel = new JLabel();//متن نتیجه آپلود عکس

        try {
            File imageFile = new File("src/v.png");
            if (imageFile.exists()) {
                icon = new ImageIcon("src/v.png");
                Image scaledImage = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImage);
                imageLabel.setIcon(icon);
            } else {
                imageLabel.setText(" فایل تصویر یافت نشد");
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imageLabel.setForeground(new Color(0x004D40));
                imageLabel.setFont(new Font("B Nazanin", Font.ITALIC, 16));
            }
        } catch (Exception e) {
            imageLabel.setText("خطا در بارگذاری تصویر");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setForeground(Color.RED);
            imageLabel.setFont(new Font("B Nazanin", Font.ITALIC, 16));
        }

//--------------------------------------------------------------------------------



        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(0xE0F2F1));

        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        contentPanel.add(imageLabel, BorderLayout.EAST);
        panel.add(contentPanel, BorderLayout.CENTER);

        JButton btnAddPatient = createStyledButton("افزودن بیمار");
        JButton btnPatientList = createStyledButton("لیست بیماران");
        JButton btnDoctorList = createStyledButton("لیست پزشکان");
        JButton btnClinicInfo = createStyledButton("اطلاعات درمانگاه");
        JButton btnWebsite = createStyledButton("وب سایت و دیاگرام");
        JButton btnGame = createStyledButton("حوصله ات سر رفته؟! :)");

        buttonPanel.add(btnAddPatient);
        buttonPanel.add(btnPatientList);
        buttonPanel.add(btnDoctorList);
        buttonPanel.add(btnClinicInfo);
        buttonPanel.add(btnWebsite);
        buttonPanel.add(btnGame);

        add(panel);

        btnAddPatient.addActionListener(e -> openAddPatientPage());
        btnPatientList.addActionListener(e -> openPatientListPage());
        btnDoctorList.addActionListener(e -> openDoctorListPage());
        btnClinicInfo.addActionListener(e -> openClinicInfoPage());
        btnWebsite.addActionListener(e -> openWebsitePage());
        btnGame.addActionListener(e -> openDinoGame());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(0x009688));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("B Nazanin", Font.PLAIN, 20));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0x26A69A));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0x009688));
            }
        });

        return button;
    }

    private void openAddPatientPage() {
        AddPatientPage addPatientPage = new AddPatientPage();
        addPatientPage.setVisible(true);
    }

    private void openPatientListPage() {
        PatientListPage patientListPage = new PatientListPage();
        patientListPage.setVisible(true);
    }

    private void openDoctorListPage() {
        DoctorListPage doctorListPage = new DoctorListPage();
        doctorListPage.setVisible(true);
    }

    private void openClinicInfoPage() {
        ClinicInfoPage clinicInfoPage = new ClinicInfoPage();
        clinicInfoPage.setVisible(true);
    }

    private void openWebsitePage() {
        try {
            File htmlFile = new File("D:\\team\\project\\project.html");// حتما بایدبرای لینک ها ترای کچ استفاده شود
            // باز کردن فایل با مرورگر پیش‌فرض
            Desktop.getDesktop().browse(htmlFile.toURI());//مرور گر پیش فرض سیستم
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "خطا در باز کردن فایل: " + ex.getMessage(),
                    "خطا",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDinoGame() {
        DinoGame.startGame();
    }
}