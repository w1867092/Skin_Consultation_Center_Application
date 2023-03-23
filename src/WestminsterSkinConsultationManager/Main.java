package WestminsterSkinConsultationManager;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * This is the documentation for the Main class.
 * In this class created a  arraylist data type of Doctor and consultationsList
 * created an object westminsterSkinConsultationManager from WesministerSkinConsultationManager class
 * This will  display the functionalities of the menu
 */

public class Main {
    public static void main(String[] args) {
        WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();
        westminsterSkinConsultationManager.loadDataToIntoConsole();

         ArrayList<Consultation> consultationsList = new ArrayList<>();
         Scanner scanner = new Scanner(System.in);

         boolean quit = true;
         while (quit){

             System.out.println("---------------------Westminster Skin  ConsultationManager Manager---------------------");
             System.out.println("\nPress ");
             System.out.println("\t A - To Add a new WestminsterSkinConsultationManager.Doctor.");
             System.out.println("\t V - To View all doctors in alphabetical order by their SurName.");
             System.out.println("\t D - To Delete a doctor.");
             System.out.println("\t S - To Store data to into the file.");
             System.out.println("\t G - To Open graphical user interface.");
             System.out.println("\t X - To Exit the application.");
             System.out.println("  ");

             System.out.println("Enter Option :- ");

             String GetInput = scanner.next().toLowerCase(Locale.ROOT);

             switch (GetInput) {
                 case "a":
                     westminsterSkinConsultationManager.addNewDoctor();
                     break;
                 case "v":
                     westminsterSkinConsultationManager.viewAllDoctorsInAlphabetical();
                     break;
                 case "d":
                     westminsterSkinConsultationManager.deleteDoctor();
                     break;
                 case "s":
                     westminsterSkinConsultationManager.storeDataToIntoTheFile();
                     break;
                 case "g":
                     new MyHome(westminsterSkinConsultationManager,consultationsList).setVisible(true);
                     break;
                 case "x":
                     westminsterSkinConsultationManager.exitTheApplication();
                     break;
                 default:
                     System.out.println("  Invalid Input !!! ");
             }
         }
    }
}

