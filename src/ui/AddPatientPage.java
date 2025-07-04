package ui;

//---------------------------------------------------------
import model.Patient;
import model.PatientData;
import model.EmergencyPatient;
import model.AppointmentManager;
import model.sendmassage;
//کتابخانه های سیستمی
import javax.swing.*;
import java.awt.*;
import java.awt.Desktop;
import java.net.URI;

//---------------------------------------------------------

public class AddPatientPage extends JFrame {
    private JTextField nameField, nationalCodeField, phoneField;         //برای فیلد هایی که تایپ میشود
    private JComboBox<String> doctorComboBox;                            // برای فیلد های چند گزینه ای
    private JTextField appointmentDateField;
    private JTextField appointmentTimeField;
    private JCheckBox emergencyCheckBox;                                 //برای فیلد هایی که چک باکس هستند
    private JCheckBox sendSMSCheckBox;
    private JButton saveButton;                                          // ذخیره
    private JButton calendarButton;                                      // تقویم گوگل

//------------------------------------------------------------------------------------------------

    private static final AppointmentManager appointmentManager = new AppointmentManager();  //ایجاد شی از کلاس

    //--------------------------------------------------------------------------------------------------
    private void openGoogleCalendar() {
        try {
            // تلاش برای باز کردن لینک تقویم گوگل در مرورگر
            Desktop.getDesktop().browse(new URI("https://calendar.google.com/calendar/u/0/r?pli=1"));
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,    //خطا در همون فریم نوشن بده با دیس
                    "خطا در باز کردن تقویم: " + ex.getMessage(),
                    "خطا", JOptionPane.ERROR_MESSAGE);
        }
    }


    //--------------------------------------------------------------------------------------------------

    public AddPatientPage() {
        setTitle("افزودن بیمار و رزرو نوبت");
        setSize(400, 530);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());                           //نوع چیدمان جدول انعطاف پذیر
        GridBagConstraints gbc = new GridBagConstraints();             //ساخت شی از GridBagConstraints
        gbc.insets = new Insets(10, 10, 10, 10);  // فاصله دکمه از 4 طرف

        // اطلاعات بیمار
        JLabel nameLabel = new JLabel(": نام بیمار");
        nameField = new JTextField(20);                       // طول رشته
        JLabel nationalCodeLabel = new JLabel(": کد ملی");
        nationalCodeField = new JTextField(20);
        JLabel phoneLabel = new JLabel(": شماره تماس");
        phoneField = new JTextField(20);

        String[] doctors = {"میربیگی", "مولایی", "تیرانداز"};
        JLabel doctorLabel = new JLabel(": پزشک");
        doctorComboBox = new JComboBox<>(doctors);                   //کامبو باکسی برای انتخاب چند گزینه ای


        // رزرو نوبت
        JLabel appointmentDateLabel = new JLabel(": تاریخ نوبت");
        appointmentDateField = new JTextField("1404-02-21", 20);

        JLabel appointmentTimeLabel = new JLabel(": زمان نوبت");
        appointmentTimeField = new JTextField("12:00", 20);

        // دکمه تقویم گوگل
        calendarButton = new JButton("تقویم گوگل");
        calendarButton.setFont(new Font("B Nazanin", Font.BOLD, 16));
        calendarButton.setBackground(new Color(0x4285F4)); // رنگ گوگل
        calendarButton.setForeground(Color.WHITE);
        calendarButton.addActionListener(e -> openGoogleCalendar());

        emergencyCheckBox = new JCheckBox("بیمار اورژانسی");
        sendSMSCheckBox = new JCheckBox("ارسال پیامک به بیمار");
        sendSMSCheckBox.setSelected(true);

        saveButton = new JButton("ذخیره");

//-------------------------------------------------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nationalCodeLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        panel.add(nationalCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(doctorLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        panel.add(doctorComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(appointmentDateLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 1;
        panel.add(appointmentDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(appointmentTimeLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        panel.add(appointmentTimeField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(calendarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(emergencyCheckBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(sendSMSCheckBox, gbc);

 //-------------------------------------------------------------------------------------------

        emergencyCheckBox.addActionListener(e -> {
            appointmentTimeField.setEnabled(!emergencyCheckBox.isSelected());//set enable false
            appointmentDateField.setEnabled(!emergencyCheckBox.isSelected());
            calendarButton.setEnabled(!emergencyCheckBox.isSelected());
        });

//-------------------------------------------------------------------------------------------

        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(saveButton, gbc);
        add(panel);

        saveButton.addActionListener(e -> savePatient());
    }

//-------------------------------------------------------------------------------------------

    private void savePatient() {

        String name = nameField.getText();
        String nationalCode = nationalCodeField.getText();
        String phone = phoneField.getText();
        String doctor = (String) doctorComboBox.getSelectedItem();
        String appointmentDate = appointmentDateField.getText();
        String appointmentHour = appointmentTimeField.getText();

//-------------------------------------------------------------------------------------------
        //پر بودن فیلد ها
        if (name.trim().isEmpty() || nationalCode.trim().isEmpty() || phone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "لطفاً تمام فیلدها را پر کنید.", "خطا", JOptionPane.ERROR_MESSAGE);
            return;
        }

//-------------------------------------------------------------------------------------------

        Patient patient;                          // ایجاد آبجکت بیمار
        if (emergencyCheckBox.isSelected()) {
            patient = new EmergencyPatient(name, nationalCode, phone, doctor);
        } else {
            patient = new Patient(name, nationalCode, phone, doctor, 30, appointmentDate, appointmentHour);
        }

//-------------------------------------------------------------------------------------------
        // افزودن بیمار به لیست
        PatientData.addPatient(patient);
//-------------------------------------------------------------------------------------------
        // ارسال پیامک
        if (sendSMSCheckBox.isSelected()) {
            try {
                boolean smsSuccess = sendmassage.sendSMS(phone, name, doctor, appointmentDate, appointmentHour);

                if (smsSuccess) {
                    JOptionPane.showMessageDialog(this, "بیمار با موفقیت ثبت شد و پیامک ارسال گردید.",
                            "موفقیت", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "بیمار ثبت شد اما پیامک ارسال نشد.",
                            "هشدار", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "بیمار ثبت شد اما در ارسال پیامک خطا رخ داد:\n" + ex.getMessage(),
                        "خطای ارسال پیامک", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "بیمار با موفقیت ثبت شد.",
                    "موفقیت", JOptionPane.INFORMATION_MESSAGE);
        }

        dispose();
    }
}
