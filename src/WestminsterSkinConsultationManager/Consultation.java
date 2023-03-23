package WestminsterSkinConsultationManager;

public class Consultation {

    private Patient patient;
    private String patientInTime;
    private String patientOutTime;
    private String consultationDate;
    private String doctorName;

    private String doctorSpecialization;
    private String cost;

    private String notes;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Consultation(Patient patient, String patientInTime, String patientOutTime, String consultationDate, String doctorName, String doctorSpecializetion) {
        this.patient = patient;
        this.patientInTime = patientInTime;
        this.patientOutTime = patientOutTime;
        this.consultationDate = consultationDate;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecializetion;

    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public String getPatientStartingTime() {
        return patientInTime;
    }

    public void setPatientStartingTime(String patientStartingTime) {
        this.patientInTime = patientStartingTime;

    }

    public String getPatientEndingTime() {
        return patientOutTime;
    }

    public void setPatientEndingTime(String patientEndingTime) {
        this.patientOutTime = patientEndingTime;
    }


    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public boolean isDoctorAvailable(String from, String to) {


        boolean validateInTime = getIntegerTimeValue(patientInTime) <= getIntegerTimeValue(from) && getIntegerTimeValue(from) <= getIntegerTimeValue(patientOutTime);
        boolean validateOutTime = getIntegerTimeValue(patientOutTime) >= getIntegerTimeValue(to) && getIntegerTimeValue(to) >= getIntegerTimeValue(patientInTime);
        System.out.println(doctorName);
        System.out.println(validateInTime);
        System.out.println(validateOutTime);
        return !(validateInTime || validateOutTime);
    }

    public int getIntegerTimeValue(String value) {
        return Integer.parseInt(value.substring(0, 2));
    }

}



