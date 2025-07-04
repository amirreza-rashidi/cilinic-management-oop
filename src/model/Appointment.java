

package model;

import java.time.LocalDateTime;

public class Appointment {
    private String patientName;
    private String doctorName;
    private LocalDateTime appointmentTime;

    public Appointment(String patientName, String doctorName, LocalDateTime appointmentTime) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentTime = appointmentTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
    }
