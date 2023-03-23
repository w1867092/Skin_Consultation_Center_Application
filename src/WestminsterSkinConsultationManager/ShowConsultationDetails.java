package WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShowConsultationDetails extends javax.swing.JFrame {

    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTable jTable1;


    public ShowConsultationDetails(WestminsterSkinConsultationManager westminsterSkinConsultationManager, ArrayList<Consultation> consultationsList) {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" All Consultation Details ");
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Century Gothic", Font.BOLD, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("All Consultation Details");

        jTable1.setRowHeight(32);
        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setFont(new java.awt.Font("Century Gothic", Font.PLAIN, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));


                 String [] conTable = {
                        "Patient full name", "Mobile Number", "Date Of Birth", "Patient ID" , "Specialisation", "Doctor Full Name","Consultation Date" , "Consultation time duration","cost"
                };

        DefaultTableModel toAdd = new DefaultTableModel (conTable , 0);
        jTable1 = new JTable(toAdd);
        for(Consultation consultation : consultationsList){
            String [] rowDataCon = {consultation.getPatient().firstName + " " + consultation.getPatient().surName,
                    consultation.getPatient().mobilePhoneNumber,consultation.getPatient().dateOfBirth,
                    consultation.getPatient().getPatientId(),consultation.getDoctorSpecialization(),
                    consultation.getDoctorName(),consultation.getConsultationDate(),
                    consultation.getPatientStartingTime() + " To " + consultation.getPatientEndingTime(),consultation.getCost()};
            toAdd.addRow(rowDataCon);
//            consultation.getPatient().fullName()

        }


        jScrollPane1.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new MyHome(westminsterSkinConsultationManager,consultationsList).setVisible(true);dispose();
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(266, 266, 266)
                                .addComponent(jLabel2)
                                .addContainerGap(282, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jButton2)
                                                .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(11, 11, 11))
        );

        pack();
        setLocationRelativeTo(null);
    }


}



