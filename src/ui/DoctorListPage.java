package ui;

import javax.swing.*;
import java.awt.*;

public class DoctorListPage extends JFrame {

    public DoctorListPage() {

//-------------------------------------------------------------------------------------------
        setTitle("لیست پزشکان");// پنجره اصلی
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

//-------------------------------------------------------------------------------------------
        //پنل سفید داخلی
        JPanel panel = new JPanel(new BorderLayout());
        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.setBackground(new Color(0x88F2CF));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

 //------------------------------------------------------------------------------------------
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(0xF0F200));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel titleLabel = new JLabel("لیست پزشکان درمانگاه رازی", SwingConstants.RIGHT);
        titleLabel.setFont(new Font("B Nazanin", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0x004D40));
        titleLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        titlePanel.add(titleLabel, BorderLayout.CENTER);
        panel.add(titlePanel, BorderLayout.NORTH);

//---------------------------------------------------------------------------------------------
       // لیست پزشکان
        String[] doctors = {
                "  دکتر میربیگی - متخصص اعصاب و روان - شنبه تا چهارشنبه 8 تا 14",
                "  دکتر مولایی - متخصص پوست و مو - یکشنبه تا سه‌شنبه 14 تا 20",
                "  دکتر تیرانداز -  (نامشخص) - چهارشنبه 10 تا 18"
        };
    //------------------------------------------------------------------------------------------
        JList<String> doctorList = new JList<>(doctors);
        doctorList.setFont(new Font("B Nazanin", Font.PLAIN, 20));
        doctorList.setFixedCellHeight(50);
        doctorList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        panel.add(doctorList, BorderLayout.CENTER);

        add(panel);
    }
}