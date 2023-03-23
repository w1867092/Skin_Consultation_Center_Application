package WestminsterSkinConsultationManager;

import com.toedter.calendar.JDateChooser;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


public class ConsultationGui extends JFrame implements ActionListener, KeyListener {
    private JLabel  surnameLabel, dobLabel, mobileLabel, idLabel, specializationLabel, doctorNameLabel, genderLabel, consultationDateLable;
    private JTextField firstNameField, surnameField, mobileField,dobField, idField;

    public ConsultationGui(WestminsterSkinConsultationManager wscm, ArrayList<Consultation> consultationsList) {

        JFrame consultFrame = new JFrame();
        consultFrame.setLayout(new BorderLayout());

        JPanel fullPage = new JPanel();
        fullPage.setBorder(BorderFactory.createEmptyBorder(25,35,25,35));
        fullPage.setBackground(new Color(204,255,255));
        fullPage.setLayout(new GridLayout(1, 2, 100, 0));

        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(new Color(204,255,255));
        leftColumn.setLayout(new GridLayout(16, 1, 0, 10));

        JPanel rightColumn = new JPanel();
        rightColumn.setBackground(new Color(204,255,255));
        rightColumn.setLayout(new GridLayout(16, 1, 0, 10));
        fullPage.add(leftColumn);
        fullPage.add(rightColumn);
        consultFrame.add(fullPage, BorderLayout.CENTER);

        consultFrame.setTitle("Consultation Form");
        consultFrame.setSize(1000, 800);
        consultFrame.setLocationRelativeTo(null);
        consultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        consultFrame.setVisible(true);


        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        firstNameLabel.setForeground(new Color(102, 0, 102));
        firstNameField = new JTextField(20);
        leftColumn.add(firstNameLabel);
        leftColumn.add(firstNameField);

        surnameLabel = new JLabel("Surname");
        surnameLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        surnameLabel.setForeground(new Color(102, 0, 102));
        surnameField = new JTextField(20);
        //surnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftColumn.add(surnameLabel);
        leftColumn.add(surnameField);

        mobileLabel = new JLabel("Mobile number");
        mobileLabel.setFont(new Font("Century Gothic", Font.BOLD, 20)); // NOI18N
        mobileLabel.setForeground(new Color(102, 0, 102));
        mobileField = new JTextField(20);
        // mobileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftColumn.add(mobileLabel);
        leftColumn.add(mobileField);

        idLabel = new JLabel("Id");
        idLabel.setFont(new Font("Century Gothic", Font.BOLD, 20)); // NOI18N
        idLabel.setForeground(new Color(102, 0, 102));
        idField = new JTextField(20);
        // idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftColumn.add(idLabel);
        leftColumn.add(idField);


//        dobLabel = new JLabel("Date of birth");
//        leftColumn.add(dobLabel);

        Calendar calendar = Calendar.getInstance();
        JDateChooser dateChooser1 = new JDateChooser(calendar.getTime());
        dobLabel = new JLabel("Date of birth");
        dobLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        dobLabel.setForeground(new Color(102, 0, 102));
        dateChooser1.addPropertyChangeListener("date", e -> {
            Calendar selectedDate = dateChooser1.getCalendar();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            dobLabel.setText(dateFormat.format(selectedDate.getTime()));
        });

        leftColumn.add(dobLabel);
        leftColumn.add(dateChooser1);


        genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        genderLabel.setForeground(new Color(102, 0, 102));
        genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftColumn.add(genderLabel);

        JRadioButton maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        maleRadioButton.setForeground(new Color(102, 0, 102));
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        femaleRadioButton.setForeground(new Color(102, 0, 102));

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        leftColumn.add(maleRadioButton);
        leftColumn.add(femaleRadioButton);


        //getting a drop down with the name of specialization

        ArrayList<String> doctorSpecialization = new ArrayList<>();
        for (int i = 0; i < wscm.doctorArrayList.size(); i++) {
            doctorSpecialization.add(wscm.doctorArrayList.get(i).getSpecialisationCategory());
        }
        Set<String> set = new LinkedHashSet<>(doctorSpecialization);
        ArrayList<String> updatedList = new ArrayList<>(set);
//
        String[] specializationArray = updatedList.toArray(new String[0]);
        JComboBox<String> specializationComboBox = new JComboBox<>(specializationArray);
        specializationComboBox.setPreferredSize(new Dimension(200, 25));
        //set the initial selection to be nothing


        JComboBox<String> doctorNameComboBox = new JComboBox<>();
        doctorNameComboBox.setPreferredSize(new Dimension(200, 25));
        doctorNameComboBox.setFont(new Font("Century Gothic", Font.BOLD, 20));
        doctorNameComboBox.setForeground(new Color(102, 0, 102));
//        add(doctorNameComboBox);
        specializationComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) specializationComboBox.getSelectedItem();

                doctorNameComboBox.removeAllItems();

                // Add the appropriate items to menu2 based on the selection in menu1
                assert selectedItem != null;
                if (selectedItem.equals("Cosmetic")) {
                    for (int i = 0; i < wscm.doctorArrayList.size(); i++) {
                        if (wscm.doctorArrayList.get(i).getSpecialisationCategory().equals("Cosmetic")) {
                            doctorNameComboBox.addItem(wscm.doctorArrayList.get(i).firstName + " " + wscm.doctorArrayList.get(i).surName);
                        }
                    }
                } else if (selectedItem.equals("Medical")) {
                    for (int i = 0; i < wscm.doctorArrayList.size(); i++) {
                        if (wscm.doctorArrayList.get(i).getSpecialisationCategory().equals("Medical")) {
                            doctorNameComboBox.addItem(wscm.doctorArrayList.get(i).firstName + " " + wscm.doctorArrayList.get(i).surName);
                        }
                    }
                } else if (selectedItem.equals("Surgical")) {
                    for (int i = 0; i < wscm.doctorArrayList.size(); i++) {
                        if (wscm.doctorArrayList.get(i).getSpecialisationCategory().equals("Surgical")) {
                            doctorNameComboBox.addItem(wscm.doctorArrayList.get(i).firstName + " " + wscm.doctorArrayList.get(i).surName);
                        }
                    }
                }
                else {
                    for (int i = 0; i < wscm.doctorArrayList.size(); i++) {
                        if (wscm.doctorArrayList.get(i).getSpecialisationCategory().equals("Paediatric")) {
                            doctorNameComboBox.addItem(wscm.doctorArrayList.get(i).firstName + " " + wscm.doctorArrayList.get(i).surName);
                        }

                    }
                }
            }

        });
        rightColumn.add(specializationLabel = new JLabel("Specialization"));
        specializationLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        specializationLabel.setForeground(new Color(102, 0, 102));
        rightColumn.add(specializationComboBox);
        rightColumn.add(doctorNameLabel = new JLabel("Doctor"));
        doctorNameLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        doctorNameLabel.setForeground(new Color(102, 0, 102));
        rightColumn.add(doctorNameComboBox);


        Calendar dateCalender = Calendar.getInstance();
        JDateChooser dateChooser2 = new JDateChooser(dateCalender.getTime());
        consultationDateLable = new JLabel("Select a Consultation date");
        consultationDateLable.setFont(new Font("Century Gothic", Font.BOLD, 20));
        consultationDateLable.setForeground(new Color(102, 0, 102));
        dateChooser2.addPropertyChangeListener("date", e -> {
            Calendar selectedDate = dateChooser2.getCalendar();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            dateLabel2.setText(dateFormat.format(selectedDate.getTime()));
        });

        //consultationDateLabel = new JLabel("Select a Consultation date");
        rightColumn.add(consultationDateLable);
        rightColumn.add(dateChooser2);


        //creating the time slots with combo box

        JLabel fromLabel = new JLabel("From");
        fromLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        fromLabel.setForeground(new Color(102, 0, 102));
        JLabel toLabel = new JLabel("To");
        toLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        toLabel.setForeground(new Color(102, 0, 102));

        String[] fromOptions = {"01.00 p.m", "02.00 p.m", "03.00 p.m", "04.00 p.m", "05.00 p.m" ,"06.00 p.m", "07.00 p.m", "08.00 p.m" , "09.00 p.m" , "10.00 p.m", "11.00 p.m" , "12.00 p.m"};
        JComboBox<String> fromComboBox = new JComboBox<>(fromOptions);
        fromComboBox.setPreferredSize(new Dimension(200, 25));

        JComboBox<String> toComboBox = new JComboBox<>();
        toComboBox.setPreferredSize(new Dimension(200, 25));

        fromComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectItem = (String) fromComboBox.getSelectedItem();

                toComboBox.removeAllItems();

                // Add the appropriate items to menu2 based on the selection in menu1
                assert selectItem != null;

                int selectedIndex = -1;
                for (int i = 0; i < fromOptions.length; i++) {
                    if (fromOptions[i].equals(selectItem)) {
                        selectedIndex = i;
                        break;
                    }
                }

                if (selectedIndex != -1) {
                    for (int i = selectedIndex + 1; i < fromOptions.length; i++) {
                        toComboBox.addItem(fromOptions[i]);
                    }
                }
            }

        });

        rightColumn.add(fromLabel);
        rightColumn.add(fromComboBox);
        rightColumn.add(toLabel);
        rightColumn.add(toComboBox);


        JTextField pathTextField = new JTextField();
        pathTextField.setEnabled(false);
        JButton imgButton = new JButton("Can insert Images of your pathology");
        imgButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        imgButton.setForeground(new Color(102, 0, 102));
        imgButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();
                    pathTextField.setText(imagePath);
                }
            }
        });

        leftColumn.add(imgButton);
        leftColumn.add(pathTextField);


        JButton encryptBtn = new JButton("Encrypt");
        encryptBtn.setFont(new Font("Century Gothic", Font.BOLD, 20));
        encryptBtn.setForeground(new Color(102, 0, 102));
        encryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream file = new FileInputStream(pathTextField.getText());
                    FileOutputStream outputStream = new FileOutputStream("addImage.jpg");
                    byte enc[] = "Cool20010621hAHo".getBytes();
                    SecretKeySpec key = new SecretKeySpec(enc,"AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE,key);
                    CipherOutputStream cos = new CipherOutputStream(outputStream,cipher);
                    byte buf[] = new byte[1024];
                    int read;
                    while ((read = file.read(buf)) != -1){
                        cos.write(buf,0,read);
                    }
                    file.close();
                    outputStream.flush();
                    cos.close();
                    JOptionPane.showMessageDialog(null,"The Image encrypted Successfully");
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Add Image before Encrypt");
                }
            }
        });
        leftColumn.add(encryptBtn);


        //add details button to get data into the consultationArrayList

        JButton addDetails = new JButton("Add details");
        addDetails.setFont(new Font("Century Gothic", Font.BOLD, 20));
        addDetails.setForeground(new Color(102, 0, 102));
        JLabel costLabel = new JLabel();

        addDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Patient patient = new Patient(firstNameField.getText(), surnameField.getText(), String.valueOf(dateChooser1.getDate()), mobileField.getText(), idField.getText());
                patient.setPatientGender(maleRadioButton.getText());
                patient.setPatientGender(femaleRadioButton.getText());
                Consultation consultation = new Consultation(patient, (String) fromComboBox.getSelectedItem(), (String) toComboBox.getSelectedItem(), dateChooser2.getDate().toString(), (String) doctorNameComboBox.getSelectedItem(), (String) specializationComboBox.getSelectedItem());


                boolean isDoctorAvailable = true;
                for (Consultation consult : consultationsList) {
                    if(consult.getDoctorName().equals(consultation.getDoctorName()) && consult.getConsultationDate().equals(consultation.getConsultationDate())){
                        if(!consult.isDoctorAvailable(consultation.getPatientStartingTime(),consultation.getPatientEndingTime())){
                            System.out.println("doctor date and time matched");
                            isDoctorAvailable = false;
                            break;
                        }
                    }
                }
                boolean isPatientAvailable = true;
                int firstTimeHourlyRate = 15;
                int secondTimeHourlyRate = 25;
                int cost =0;

                if(isDoctorAvailable){
                    boolean isNewPatient = true;
                    for(int i =0; i< consultationsList.size();i++) {
                        if (consultationsList.get(i).getPatient().getPatientId().equals(idField.getText()) || consultationsList.size() == 0) {
                            isNewPatient = false;
                            break;
                        }
                    }
                    if (isNewPatient){
                        cost = firstTimeHourlyRate*(consultation.getIntegerTimeValue((String) Objects.requireNonNull(toComboBox.getSelectedItem())) - consultation.getIntegerTimeValue((String) fromComboBox.getSelectedItem()));
                    }else {
                        cost = secondTimeHourlyRate*(consultation.getIntegerTimeValue((String) Objects.requireNonNull(toComboBox.getSelectedItem())) - consultation.getIntegerTimeValue((String) fromComboBox.getSelectedItem()));
                    }
                    consultationsList.add(consultation);
                    costLabel.setText("TOTAL : $ " + String.valueOf(cost));
                    consultation.setCost(costLabel.getText());
                    costLabel.setHorizontalAlignment(SwingConstants.CENTER);

                } else {
                    ArrayList<Doctor> newDoctorList = wscm.getDoctorsInSelectedSpecialization(consultation.getDoctorName());
                    Collections.shuffle(newDoctorList);
                    Doctor randomlySelectedDoctor = null;

                    for(Doctor doctor : newDoctorList) {
                        boolean isAvailable = true;

                        for(Consultation consultation1 : consultationsList) {
                            System.out.println("array length: " + consultationsList.size());
                            System.out.println(doctor.fullname() + " ----- " + consultation1.getDoctorName());
                            if (consultation1.getDoctorName().equals(doctor.fullname()) && consultation1.getConsultationDate().equals(consultation.getConsultationDate() )) {
                                System.out.println("randomly selected doctor name and date matched");
                                if(!consultation1.isDoctorAvailable(consultation.getPatientStartingTime(), consultation.getPatientEndingTime())) {
                                    System.out.println("new doctor available");
                                    isAvailable = false;
                                    break;
                                }
                            }
                        }

                        if (isAvailable) {
                            randomlySelectedDoctor = doctor;
                            break;
                        }
                    }

                    if(randomlySelectedDoctor != null) {
                        consultation.setDoctorName(randomlySelectedDoctor.fullname());
                        boolean isNewPatient = true;
                        for(int i =0; i< consultationsList.size();i++) {
                            if (consultationsList.get(i).getPatient().getPatientId().equals(idField.getText()) || consultationsList.size() == 0) {
                                isNewPatient = false;
                                break;
                            }
                        }
                        if (isNewPatient){
                            cost = firstTimeHourlyRate*(consultation.getIntegerTimeValue((String) Objects.requireNonNull(toComboBox.getSelectedItem())) - consultation.getIntegerTimeValue((String) fromComboBox.getSelectedItem()));
                        }else {
                            cost = secondTimeHourlyRate*(consultation.getIntegerTimeValue((String) Objects.requireNonNull(toComboBox.getSelectedItem())) - consultation.getIntegerTimeValue((String) fromComboBox.getSelectedItem()));
                        }

                        consultationsList.add(consultation);
                        costLabel.setText("TOTAL : $ " + String.valueOf(cost));
                        costLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        consultation.setCost(costLabel.getText());
                        JOptionPane.showMessageDialog(null, "Dr. "+randomlySelectedDoctor.fullname() + " randomly assigned");

                    }
                }
                for(Consultation consultation1: consultationsList){
                    System.out.println(consultation1.toString());
                }
            }

        });

        rightColumn.add(costLabel);
        rightColumn.add(addDetails);

        rightColumn.add(costLabel);
        rightColumn.add(costLabel);
        rightColumn.add(costLabel);
        rightColumn.add(addDetails);

        JButton toBack = new JButton("BACK");


        toBack.setBackground(new Color(204, 204, 204));
        toBack.setFont(new Font("Century Gothic", 1, 14));
        toBack.setForeground(new Color(0, 0, 0));

        toBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultFrame.setVisible(false);
                consultFrame.dispose();
                new MyHome(wscm,consultationsList).setVisible(true);
            }
        });

        rightColumn.add(toBack);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        printData();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            printData();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }

    private void printData() {


        String name = firstNameField.getText();
        String surname = surnameField.getText();
        String dob = dobField.getText();
        String mobile = mobileField.getText();
        String id = idField.getText();

        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Mobile Number: " + mobile);
        System.out.println("ID: " + id);
    }


}
