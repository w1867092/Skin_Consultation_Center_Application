package WestminsterSkinConsultationManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.format.DateTimeFormatter;
import java.util.*;


import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * This is the documentation comment for the WestminsterSkinConsultationManager class.
 * It consists of Console Menu Implementation , validation methods , doctorArrayList , and some methods
 * This WestminsterSkinConsultationManager class implements form SkinConsultationManager interface
 */

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    /**
     * defining doctor ArrayList
     */
    ArrayList<Doctor> doctorArrayList = new ArrayList<Doctor>();  // doc array

    private Scanner scanner = new Scanner(System.in);


    /**
     * This method used to get all inputs from user when choice Add New Doctor option
     * @param printinstruction
     * @return
     */
    public String toGetInfo(String printinstruction) {
        String answer = "";

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(printinstruction);
            answer = scanner.nextLine();

            if (answer.isEmpty()) {
                System.out.println("Cannot be empty");
            } else {
                break;
            }
        }
        return answer;
    }

    /**
     * Below method validates how many doctors can enter the system
     * @return
     */
    public boolean isDoctorListFull() {
        if (doctorArrayList.size() >= 10) {
            System.out.println("Cannot add any more doctors");
            return true;
        } else {
            return false;
        }
    }

    /**
     * This addNewDoctor method use to add new doctor to the system
     * it takes Doctors first name , surname , Address , Date Of Birth , Contact number , Medical Licence Number ,
     * and specialisation Categories.
     */
    public void addNewDoctor() {
        boolean isRunning = !isDoctorListFull();

        while (isRunning) {

            String doctorFirstName = inputString("Enter first name :- ");
            String doctorSurname = inputString("Enter surname :- ");
            String doctorAddress = toGetInfo("Enter Address :- ");
            String doctorDateOfBirth = inputDob();
            String doctorMobilePhoneNumber = inputContactNo();
            String doctorMedicalLicenceNumber = toGetInfo("Enter Medical Licence Number :- ");
            String doctorSpecialisationCategory = inputString("\"Skin care center has only 'Dermatologists' \"\n\t" +
                    "Enter only these specialisation Categories  :- \n\t(Cosmetic dermatology , Medical dermatology , Surgical dermatology , Paediatric Dermatology)");

            doctorArrayList.add(new Doctor(doctorFirstName, doctorSurname, doctorAddress, doctorDateOfBirth, doctorMobilePhoneNumber, doctorMedicalLicenceNumber, doctorSpecialisationCategory));

            System.out.println("  ");
            System.out.println("*** Successfully added doctor for the system ***");

            while (true) {
                System.out.println("Do you want to add a another doctor enter 'Y/N' ");
                String input = scanner.nextLine().toUpperCase(Locale.ROOT);

                if (input.equals("N") || (input.equals("Y") && isDoctorListFull())) {
                    isRunning = false;
                    break;
                } else if (input.equals("Y")) {
                    break;
                }

                System.out.println("Invalid input");
            }
        }
    }

    /**
     * Below method validates the String inputs validated by regula expressions
     * Can only input Alphabetic letters (A-Z).
     * @param message
     * @return
     */
    public String inputString(String message) {
        String name = null;
        boolean isLoopRunning = true;

        while (isLoopRunning) {
            System.out.println(message);
            name = scanner.next();
            if (name.matches("[a-zA-Z]+")) {
                isLoopRunning = false;
            } else {
                System.out.println("Invalid Input Type");
            }
        }
        return name;
    }

    /**
     * Below method validates the Dates inputs validated by DateTimeFormatter
     * Can only input this format - [dd/mm/yyyy]
     * @return
     */
    public String inputDob() {
        String docDob = null;
        boolean isLoopRunning = true;

        while (isLoopRunning) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Enter the doctors Date Of Birth (Use format - [dd/mm/yyyy]) :- ");
            docDob = scanner.next();
            try {
                formatter.parse(docDob);
                isLoopRunning = false;

            } catch (Exception e) {
                System.out.println("**** Please check the format and Enter valid input ****");
            }

        }
        return docDob;

    }

    /**
     * Below method validates the Contact number inputs validated by Use this format '0_________')
     * Can only input 0 with ten leters
     * @return
     */
    public String inputContactNo() {
        String docContactNo = null;
        boolean isLoopRunning = true;

        while (isLoopRunning) {
            System.out.println("Enter the doctors Contact Number ( Use format '0_________')  :- ");
            docContactNo = scanner.next();
            if (docContactNo.matches("[0-9]{10}")) {
                isLoopRunning = false;
            } else {
                System.out.println("**** Please check the format and Enter valid input ****");
            }
        }
        return docContactNo;
    }

    /**
     * alphabeticalOrder method ordered Doctors BY there surnames
     */

    public void alphabeticalOrder() {
        doctorArrayList.sort(new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.surName.compareTo(o2.surName);
            }
        });
    }

    /**
     *  ViewAllDoctorsInAlphabetical print list of Doctor and their information In Alphabetically
     */
    @Override
    public void viewAllDoctorsInAlphabetical() {
        alphabeticalOrder();
        for (Doctor doctor : doctorArrayList) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("First name               :- " + doctor.firstName +
                    "\nSur name                 :- " + doctor.surName +
                    "\nAddress                  :- " + doctor.address +
                    "\nData of birth            :- " + doctor.dateOfBirth +
                    "\nmobile phone number      :- " + doctor.mobilePhoneNumber +
                    "\nMedical Licence Number   :- " + doctor.getMedicalLicenceNumber() +
                    "\nspecialisation Category  :- " + doctor.getSpecialisationCategory());
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

    /**
     * deleteDoctor method deletes doctors checking their Medical LicenceNumber
     */
    public void deleteDoctor() {
        boolean isRunning = true;

        if (doctorArrayList.size() == 0) {
            System.out.println("WestminsterSkinConsultationManager.Doctor array is empty");
            isRunning = false;
        }

        while (isRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter medical licence number which doctor you want to delete :- ");
            String userInput = scanner.nextLine();

            boolean isDoctorFound = false;

            for (int i = 0; i < doctorArrayList.size(); i++) {
                if (doctorArrayList.get(i).getMedicalLicenceNumber().equals(userInput)) {
                    doctorArrayList.remove(doctorArrayList.get(i));
                    isDoctorFound = true;
                    System.out.println("*** Successfully deleted doctor in the system ***");
                }
            }

            if (!isDoctorFound) {
                System.out.println("Medical licence number you entered was previously deleted or incorrect");
            }

            System.out.println("Do you want to delete another doctor? Y/N");
            String input = scanner.next().toLowerCase();
            if (!input.equals("y")) {
                isRunning = false;
            }
        }
    }

    /**
     * THis method stores all doctor's information  Into The JSON File
     */
    public void storeDataToIntoTheFile() {
        JSONArray doctorJList = new JSONArray();
        for (Doctor doctor : doctorArrayList) {
            JSONObject docDetails = new JSONObject();
            docDetails.put("doctorFirstName", doctor.firstName);
            docDetails.put("doctorSurname", doctor.surName);
            docDetails.put("doctorAddress", doctor.address);
            docDetails.put("doctorDateOfBirth", doctor.dateOfBirth);
            docDetails.put("doctorMobilePhoneNumber", doctor.mobilePhoneNumber);
            docDetails.put("doctorMedicalLicenceNumber", doctor.getMedicalLicenceNumber());
            docDetails.put("doctorSpecialisationCategory", doctor.getSpecialisationCategory());

            JSONObject doctorObject = new JSONObject();
            doctorObject.put("doctor", docDetails);
            doctorJList.add(doctorObject);
        }

        try (FileWriter file = new FileWriter("doctor.json")) {
            file.write(doctorJList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("** Successfully saved doctor information to the File **");
    }

    /**
     * Below method loads all data form the Json file and this method called in main
     * and it loads all data when program starts.
     */
    public void loadDataToIntoConsole() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("doctor.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray doctorJList = (JSONArray) obj;

            doctorJList.forEach(doc -> doctorObject((JSONObject) doc));

            System.out.println("  ");
            System.out.println("** All WestminsterSkinConsultationManager.Doctor information are loaded to the program **");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creat JSONObjects
     * @param doctor
     */
    public void doctorObject(JSONObject doctor) {
        JSONObject doctorObject = (JSONObject) doctor.get("doctor");
        String firstName = (String) doctorObject.get("doctorFirstName");
        String surName = (String) doctorObject.get("doctorSurname");
        String address = (String) doctorObject.get("doctorAddress");
        String dateOfBirth = (String) doctorObject.get("doctorDateOfBirth");
        String mobilePhoneNumber = (String) doctorObject.get("doctorMobilePhoneNumber");
        String getMedicalLicenceNumber = (String) doctorObject.get("doctorMedicalLicenceNumber");
        String getSpecialisationCategory = (String) doctorObject.get("doctorSpecialisationCategory");
        doctorArrayList.add(new Doctor(firstName, surName, address, dateOfBirth, mobilePhoneNumber, getMedicalLicenceNumber, getSpecialisationCategory));
    }

    /**
     * This is documentation comment for getDoctorsInSelectedSpecialization method.
     * This method include a doctorArrayList created from Doctor class.
     * This method uses in thc ConsultationForm class.
     * @param fullName retrieve the doctors FullName specified to doctor's specialization.
     * @return doctorArrayList.
    */
    public ArrayList<Doctor> getDoctorsInSelectedSpecialization(String fullName) {
        ArrayList<Doctor> doctorList = new ArrayList<>();

        for (Doctor doctor : doctorArrayList) {
            if (doctor.getSpecialisationCategory().equals(getSpecializationFromDoctorFullName(fullName))) {
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    /**
     * This is documentation comment for getSpecializationFromDoctorFullName method.
     * This method use in the getDoctorsInSelectedSpecialization method.
     * @param fullName from this retrieve the doctor's specialization.
     * @return Doctor Specialization.
    */
    public String getSpecializationFromDoctorFullName(String fullName) {
        for (Doctor doctor : doctorArrayList) {
            if (fullName.equals(doctor.firstName + " " + doctor.surName)) {
                return doctor.getSpecialisationCategory();
            }
        }
        return null;
    }

    /**
     * This is for exit The Application
     */
    public void exitTheApplication() {
        System.exit(0);
    }

}

