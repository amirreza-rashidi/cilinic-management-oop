package model;

import java.util.ArrayList;

public class PatientData {

    private static ArrayList<Patient> patients = new ArrayList<>();

    public static ArrayList<Patient> getPatients() {
        return patients;
    }

    public static void addPatient(Patient patient) {
        patients.add(patient);
    }
}