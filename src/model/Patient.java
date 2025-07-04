package model;

public class Patient {
    private String name;
    private String nationalCode;
    private String phone;
    private String doctor;
    private String appointmentDate;
    private String appointmentHour;

    // Constructor for normal patients
    public Patient(String name, String nationalCode, String phone, String doctor, int appointmentTime, String appointmentDate, String appointmentHour) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.phone = phone;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
    }

    // Constructor for EmergencyPatient
    public Patient(String name, String nationalCode, String phone, String doctor, String appointmentTime) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.phone = phone;
        this.doctor = doctor;
//-------------------------------------------------
        this.appointmentDate = "اورژانسی";
        this.appointmentHour = "فوری";

//-------------------------------------------------

    }

    public String getName() {
        return name;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getDoctor() {
        return doctor;
    }


    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentHour() {
        return appointmentHour;
    }

// فارسی سازی اطلاعات
    @Override
    public String toString() {
        String patientType = appointmentDate.equals("اورژانسی") ? " (اورژانسی)" : "";
        return "نام: " + name + patientType +
                "  کد ملی: " + nationalCode +
                "  پزشک: " + doctor +
                "  تاریخ: " + appointmentDate +
                " ساعت: " + appointmentHour;
    }
}