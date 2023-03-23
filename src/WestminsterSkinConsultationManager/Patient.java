package WestminsterSkinConsultationManager;


public class Patient extends Person {
    private String patientGender;
    private String  patientId;

    private String pathology;


    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public Patient(String firstName, String surName, String dateOfBirth, String mobilePhoneNumber, String patientId) {
        super(firstName, surName, dateOfBirth, mobilePhoneNumber);
        this.patientId = patientId;

    }
    public String fullName(){
        return firstName+" "+surName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }



    public String getDescription() {
        return pathology;
    }

    public void setDescription(String description) {
        this.pathology = description;
    }
}