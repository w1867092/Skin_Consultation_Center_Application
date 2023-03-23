package WestminsterSkinConsultationManager;

public class Person {
    protected String firstName;
    protected String surName;
    protected String address;
    protected String  dateOfBirth;
    protected String mobilePhoneNumber;
    public Person() {

    }

    public Person(String firstName, String surName, String address, String dateOfBirth, String mobilePhoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Person(String firstName, String surName, String dateOfBirth, String mobilePhoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

}
