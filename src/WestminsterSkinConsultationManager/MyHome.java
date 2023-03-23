package WestminsterSkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyHome extends JFrame {

    private JButton consultationButton;
    private JButton viweCon;
    private JButton doctorsButton;
    private JLabel homeLabel;
    private JLabel titleLabel;


    public MyHome(WestminsterSkinConsultationManager westminsterSkinConsultationManager, ArrayList<Consultation> consultationsList) {

        consultationButton = new JButton();
        consultationButton.setBackground(new Color(0, 153, 153));
        consultationButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
        consultationButton.setForeground(new Color(0, 0, 0));
        consultationButton.setText("Consultation");
        consultationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ConsultationGui consultationGui = new ConsultationGui(westminsterSkinConsultationManager,consultationsList);
//                consultationGui.show();

                dispose();
            }
        });

        doctorsButton = new JButton();
        doctorsButton.setBackground(new Color(0, 153, 153));
        doctorsButton.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        doctorsButton.setForeground(new Color(0, 0, 0));
        doctorsButton.setText("Doctors");
        doctorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DoctorsGUI doctorsGUI = new DoctorsGUI(westminsterSkinConsultationManager,consultationsList);
                doctorsGUI.show();
                doctorsGUI.add_doc_details(westminsterSkinConsultationManager);
                dispose();
            }
        });

        viweCon = new JButton();
        viweCon.setBackground(new Color(0, 153, 153));
        viweCon.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        viweCon.setForeground(new Color(0, 0, 0));
        viweCon.setText("Con");
        viweCon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ShowConsultationDetails showConsultationDetails = new ShowConsultationDetails(westminsterSkinConsultationManager,consultationsList);
                showConsultationDetails.show();
//                showConsultationDetails.add_doc_details(westminsterSkinConsultationManager);
                dispose();
            }
        });

        homeLabel = new JLabel();
        homeLabel.setFont(new Font("Century Schoolbook", 1, 18)); // NOI18N
        homeLabel.setText("HOME");

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Century Schoolbook", 1, 24)); // NOI18N
        titleLabel.setText("Westminster Skin Care Centre Management System");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Westminster Skin Care Centre Management System");
        setBackground(new Color(0, 0, 0));


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(titleLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(264, 264, 264)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(consultationButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(doctorsButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(viweCon, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 272, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addComponent(homeLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(titleLabel,GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(homeLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(doctorsButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(consultationButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(viweCon, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }

}



