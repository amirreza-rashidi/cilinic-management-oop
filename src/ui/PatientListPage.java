package ui;

import model.Patient;
import model.PatientData;
import javax.swing.*;
import java.awt.*;

public class PatientListPage extends JFrame {
    public PatientListPage() {
        setTitle("لیست بیماران");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // تنظیم فونت و رنگ کلی
        Font defaultFont = new Font("B Nazanin", Font.PLAIN, 16);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("List.font", defaultFont);
        UIManager.put("Button.font", defaultFont);

        // پنل اصلی
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xE0F2F1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // عنوان
        JLabel titleLabel = new JLabel("لیست بیماران", SwingConstants.CENTER);
        titleLabel.setFont(new Font("B Nazanin", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0x004D40));
        panel.add(titleLabel, BorderLayout.NORTH);

        // لیست بیماران
        DefaultListModel<Patient> listModel = new DefaultListModel<>();
        for (Patient p : PatientData.getPatients()) listModel.addElement(p);

        JList<Patient> patientList = new JList<>(listModel);
        patientList.setFixedCellHeight(40);

        // راست‌چین کردن آیتم‌ها
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        renderer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        patientList.setCellRenderer(renderer);

        panel.add(new JScrollPane(patientList), BorderLayout.CENTER);
        add(panel);
    }
}
