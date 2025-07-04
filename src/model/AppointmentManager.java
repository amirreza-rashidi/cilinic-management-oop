package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private final List<Appointment> appointments = new ArrayList<>();

    public boolean reserveAppointment(String patientName, String doctorName, LocalDateTime time) {
        if (isAvailable(doctorName, time)) {
            appointments.add(new Appointment(patientName, doctorName, time));
            return true;
        }
        return false;
    }

    public boolean isAvailable(String doctorName, LocalDateTime time) {
        for (Appointment appt : appointments) {
            if (appt.getDoctorName().equals(doctorName) &&
                    appt.getAppointmentTime().equals(time)) {
                return false; // این زمان قبلاً گرفته شده
            }
        }
        return true;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
}
