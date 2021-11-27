
package electricity.billing.system;


import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */


public class UpdateProfile extends JFrame implements ActionListener{
    JTextField addressTextField, cityTextField, stateTextField, emailTextField, phoneTextField;
    JLabel nameLabel, meterNumberLabel;
    JButton updateButton, backButton;
    String meter;
    UpdateProfile(String meter){
        this.meter = meter;
        
        setBounds(500, 220, 1000, 450);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 190, 81));
        this.setResizable(false);
        
        JLabel headingLabel = new JLabel("UPDATE CUSTOMER INFORMATION");
        headingLabel.setBounds(110, 0, 400, 30);
        headingLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(headingLabel);
        
        JLabel nameHeadingLabel = new JLabel("Name");
        nameHeadingLabel.setBounds(30, 70, 100, 20);
        add(nameHeadingLabel);
        
        nameLabel = new JLabel();
        nameLabel.setBounds(230, 70, 200, 20);
        add(nameLabel);
        
        JLabel meterNumberHeadingLabel = new JLabel("Meter Number");
        meterNumberHeadingLabel.setBounds(30, 110, 120, 20);
        add(meterNumberHeadingLabel);
        
        meterNumberLabel = new JLabel();
        meterNumberLabel.setBounds(230, 110, 200, 20);
        add(meterNumberLabel);
        
        JLabel addressHeadingLabel = new JLabel("Address");
        addressHeadingLabel.setBounds(30, 150, 100, 20);
        add(addressHeadingLabel);
        
        addressTextField = new JTextField();
        addressTextField.setBounds(230, 150, 200, 20);
        add(addressTextField);
        
        JLabel cityHeadingLabel = new JLabel("City");
        cityHeadingLabel.setBounds(30, 190, 100, 20);
        add(cityHeadingLabel);
        
        cityTextField = new JTextField();
        cityTextField.setBounds(230, 190, 200, 20);
        add(cityTextField);
        
        JLabel stateHeadingLabel = new JLabel("State");
        stateHeadingLabel.setBounds(30, 230, 100, 20);
        add(stateHeadingLabel);
        
        stateTextField = new JTextField();
        stateTextField.setBounds(230, 230, 200, 20);
        add(stateTextField);
        
        JLabel emailHeadinLabel = new JLabel("Email");
        emailHeadinLabel.setBounds(30, 270, 100, 20);
        add(emailHeadinLabel);
        
        emailTextField = new JTextField();
        emailTextField.setBounds(230, 270, 200, 20);
        add(emailTextField);
        
        JLabel phoneHeadingLabel = new JLabel("Phone");
        phoneHeadingLabel.setBounds(30, 310, 100, 20);
        add(phoneHeadingLabel);
        
        phoneTextField = new JTextField();
        phoneTextField.setBounds(230, 310, 200, 20);
        add(phoneTextField);
        
        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(0,102,27));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBounds(70, 360, 100, 25);
        updateButton.addActionListener(this);
        add(updateButton);
        
        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(230, 360, 100, 25);
        backButton.addActionListener(this);
        add(backButton);
        
        try{
            Conn connection = new Conn();
            ResultSet resulatSet = connection.statement.executeQuery("select * from customer where meter = '"+meter+"'");
            while(resulatSet.next()){
                nameLabel.setText(resulatSet.getString(1));
                meterNumberLabel.setText(resulatSet.getString(2));
                addressTextField.setText(resulatSet.getString(3));
                cityTextField.setText(resulatSet.getString(4));
                stateTextField.setText(resulatSet.getString(5));
                emailTextField.setText(resulatSet.getString(6));
                phoneTextField.setText(resulatSet.getString(7));
                
            }
        }catch(SQLException e){}
        
        ImageIcon updateInformationBannerIcon = new ImageIcon(UpdateProfile.class.getResource("icon/update_info_icon.png"));
        Image updateInformationBannerImage  = updateInformationBannerIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        updateInformationBannerIcon = new ImageIcon(updateInformationBannerImage);
        JLabel updateInformationImageLabel  = new JLabel(updateInformationBannerIcon);
        updateInformationImageLabel.setBounds(550, 50, 400, 300);
        add(updateInformationImageLabel);
    }
    
    @Override
    public void actionPerformed(ActionEvent events){
        if(events.getSource() == updateButton){
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String email = emailTextField.getText();
            String phoneNumber = phoneTextField.getText();
            
            try{
                Conn connection = new Conn();
                connection.statement.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phoneNumber+"' where meter = '"+meter+"'");
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                this.setVisible(false);
                
            }catch(HeadlessException | SQLException e){}
            
        }else if(events.getSource() == backButton){
            this.dispose();
        }
    }
    
    public static void main(String[] args){
        new UpdateProfile("").setVisible(true);
        
    }
}
