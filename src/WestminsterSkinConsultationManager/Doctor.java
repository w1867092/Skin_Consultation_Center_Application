package WestminsterSkinConsultationManager;

public class Doctor extends Person {

    private String medicalLicenceNumber;
    private String specialisationCategory;


    public Doctor() {

    }

    /**
     * @param firstName
     * @param surName
     * @param address
     * @param dateOfBirth
     * @param mobilePhoneNumber
     * @param medicalLicenceNumber
     * @param specialisationCategory
     */


    public Doctor(String firstName, String surName, String address, String dateOfBirth, String mobilePhoneNumber, String medicalLicenceNumber, String specialisationCategory) {
        super(firstName, surName, address, dateOfBirth, mobilePhoneNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisationCategory = specialisationCategory;
    }
    public String fullname(){
        return firstName+" "+surName;
    }



    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisationCategory() {
        return specialisationCategory;
    }

    public void setSpecialisationCategory(String specialisationCategory) {
        this.specialisationCategory = specialisationCategory;
    }

    @Override
    public String toString() {
        return "WestminsterSkinConsultationManager.Doctor{" +
                "medicalLicenceNumber='" + medicalLicenceNumber + '\'' +
                ", specialisationCategory='" + specialisationCategory + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                '}';
    }

}
