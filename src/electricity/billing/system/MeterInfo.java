
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */

public class MeterInfo extends JFrame implements ActionListener{
    JLabel meterNumberHeadingLabel,meterLocationLabel,meterTypeLabel,billTypeLabel,phaseCodeLabel,daysHeadingLabel,noteHeadingLabel,meterInfoImageLabel,daysLabel, noteLabel, meterNumberLabel;
    Choice meterLocationChooser, meterTypeChooser, phaseCodeChooser,billTypeChooser, c5;
    JButton submitButton,cancelButton;
    MeterInfo(String meterNumber){
        setLocation(600,200);
        setSize(700,500);
        this.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(149, 175, 184));
        
        JLabel headingLabel = new JLabel("Meter Information");
        headingLabel.setBounds(180, 10, 220, 26);
        headingLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(headingLabel);
        
        meterNumberHeadingLabel = new JLabel("Meter Number");
        meterNumberHeadingLabel.setBounds(100, 80, 120, 20);
        
        meterNumberLabel = new JLabel(meterNumber);
        meterNumberLabel.setBounds(240, 80, 200, 20);
        panel.add(meterNumberHeadingLabel);
        panel.add(meterNumberLabel);
        
        meterLocationLabel = new JLabel("Meter Location");
        meterLocationLabel.setBounds(100, 120, 120, 20);
        meterLocationChooser = new Choice();
        meterLocationChooser.add("Outside");
        meterLocationChooser.add("Inside");
        meterLocationChooser.setBounds(240, 120, 200, 20);
        panel.add(meterLocationLabel);
        panel.add(meterLocationChooser);
        
        meterTypeLabel = new JLabel("Meter Type");
        meterTypeLabel.setBounds(100, 160, 100, 20);
        meterTypeChooser = new Choice();
        meterTypeChooser.add("Electric Meter");
        meterTypeChooser.add("Solar Meter");
        meterTypeChooser.add("Smart Meter");
        meterTypeChooser.setBounds(240, 160, 200, 20);
        panel.add(meterTypeLabel);
        panel.add(meterTypeChooser);
        
        phaseCodeLabel = new JLabel("Phase Code");
        phaseCodeLabel.setBounds(100, 200, 100, 20);
        phaseCodeChooser = new Choice();
        phaseCodeChooser.add("011");
        phaseCodeChooser.add("022");
        phaseCodeChooser.add("033");
        phaseCodeChooser.add("044");
        phaseCodeChooser.add("055");
        phaseCodeChooser.add("066");
        phaseCodeChooser.add("077");
        phaseCodeChooser.add("088");
        phaseCodeChooser.add("099");
        phaseCodeChooser.setBounds(240, 200, 200, 20);
        panel.add(phaseCodeLabel);
        panel.add(phaseCodeChooser);
        
        billTypeLabel = new JLabel("Bill Type");
        billTypeLabel.setBounds(100, 240, 100, 20);
        billTypeChooser = new Choice();
        billTypeChooser.add("Normal");
        billTypeChooser.add("Industrial");
        billTypeChooser.setBounds(240, 240, 200, 20);
        panel.add(billTypeLabel);
        panel.add(billTypeChooser);
        
        daysHeadingLabel = new JLabel("Days");
        daysHeadingLabel.setBounds(100, 280, 100, 20);
        panel.add(daysHeadingLabel);
        daysLabel = new JLabel("30 Days");
        daysLabel.setBounds(240, 280, 200, 20);
        panel.add(daysLabel);
        
        noteHeadingLabel = new JLabel("Note");
        noteHeadingLabel.setBounds(100, 320, 100, 20);
        panel.add(noteHeadingLabel);
        noteLabel = new JLabel("<html><body>By Default Bill is calculated<br> for 30 days only</body></html>");
        noteLabel.setBounds(240, 320, 320, 40);
        panel.add(noteLabel);
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 390, 100, 25);
        submitButton.setBackground(new Color(0,102,27));
        submitButton.setForeground(Color.WHITE);
        panel.add(submitButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 390, 100, 25);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        panel.add(cancelButton);
        
        setLayout(new BorderLayout());
        add(panel,"Center");
        
        ImageIcon meterInfoIcon = new ImageIcon(MeterInfo.class.getResource("icon/meter_info_banner.jpg"));
        Image meterInfoImage = meterInfoIcon.getImage().getScaledInstance(220, 550,Image.SCALE_AREA_AVERAGING);
        meterInfoIcon = new ImageIcon(meterInfoImage);
        meterInfoImageLabel = new JLabel(meterInfoIcon);
        add(meterInfoImageLabel,"West");
        
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);
        
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent events){
        if(events.getSource() == submitButton){
            String meterNumber = meterNumberLabel.getText();
            String meterLocation = meterLocationChooser.getSelectedItem();
            String meterType = meterTypeChooser.getSelectedItem();
            String phaseCode = phaseCodeChooser.getSelectedItem();
            String billType = billTypeChooser.getSelectedItem();
            String days = "30";

            String query = "insert into meter_info values('"+meterNumber+"','"+meterLocation+"','"+meterType+"','"+phaseCode+"','"+billType+"','"+days+"')";
            try{
                Conn connection = new Conn();
                connection.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Meter Info Added Successfully");
                this.setVisible(false);

            }catch(HeadlessException | SQLException ex){
                 ex.printStackTrace();
            }
        }else if(events.getSource() == cancelButton){
            this.setVisible(false);
        }
    }
    
    
    public static void main(String[] args){
        new MeterInfo("").setVisible(true);
    }
}