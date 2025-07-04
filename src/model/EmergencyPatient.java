package model;

public class EmergencyPatient extends Patient {

    private static final String EMERGENCY_PRIORITY_TIME = "فوری";

    public EmergencyPatient(String name, String nationalCode, String phone, String doctor) {

        super(name, nationalCode, phone, doctor, EMERGENCY_PRIORITY_TIME);
    }

    @Override
    public String toString() {
        return getName() + " - " + getDoctor() + " - اورژانسی";
    }
}