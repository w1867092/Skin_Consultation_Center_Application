package WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoctorsGUI extends JFrame {

    private JButton back;
    private JButton sortBtn;
    private JLabel titatleLabel;
    private JScrollPane jScrollPane;
    private JTable jTable;

    public DoctorsGUI(WestminsterSkinConsultationManager westminsterSkinConsultationManager, ArrayList<Consultation> consultationsList) {

        titatleLabel = new JLabel();
        jScrollPane = new JScrollPane();
        jTable = new JTable();
        back = new JButton();
        sortBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("All Doctors Information");
        setResizable(false);

        titatleLabel.setFont(new Font("Century Gothic", Font.BOLD, 36)); // NOI18N
        titatleLabel.setForeground(new Color(102, 0, 102));
        titatleLabel.setText("All Doctors and their Information");

        jTable.setBackground(new Color(255, 255, 255));
        jTable.setFont(new Font("Century Gothic", 0, 14)); // NOI18N
        jTable.setForeground(new Color(0, 0, 0));
        jTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "First Name", "SurName", "Address", "Date Of Birth", "Mobile Number", "Medical  Licence Number", "Specialisation"
                }
        ));
        jTable.setRowHeight(32);
        jTable.setSelectionBackground(new Color(0, 0, 0));
        jTable.setSelectionForeground(new Color(255, 255, 255));
        jScrollPane.setViewportView(jTable);

        back.setBackground(new Color(204, 204, 204));
        back.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        back.setForeground(new Color(0, 0, 0));
        back.setText("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new MyHome(westminsterSkinConsultationManager,consultationsList).setVisible(true);dispose();
            }
        });

        sortBtn.setBackground(new Color(153, 255, 255));
        sortBtn.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        sortBtn.setForeground(new Color(0, 0, 0));
        sortBtn.setText("Sort Alphabetically");


        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(266, 266, 266)
                                .addComponent(titatleLabel)
                                .addContainerGap(282, Short.MAX_VALUE))
                        .addComponent(jScrollPane, GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(sortBtn)
                                                .addGap(34, 34, 34))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(back)
                                                .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titatleLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(sortBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(back)
                                .addGap(11, 11, 11))
        );

        pack();
        setLocationRelativeTo(null);
    }

    public void add_doc_details( WestminsterSkinConsultationManager westminsterSkinConsultationManager){
        DefaultTableModel toAdd=(DefaultTableModel) jTable.getModel();
        Object rowData[]=new Object[7];
        for(Doctor doctor : westminsterSkinConsultationManager.doctorArrayList){
            rowData[0] = doctor.firstName;
            rowData[1] = doctor.surName;
            rowData[2] = doctor.address;
            rowData[3] = doctor.dateOfBirth;
            rowData[4] = doctor.mobilePhoneNumber;
            rowData[5] = doctor.getMedicalLicenceNumber();
            rowData[6] = doctor.getSpecialisationCategory();
            toAdd.addRow(rowData);

        }
    }

}


