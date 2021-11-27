
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */

public class CalculateBill extends JFrame implements ActionListener{
    JLabel calculateElectricBillLabel,meterNumberLabel,unitsConsumedLabel,calculateBillBannerLabel,monthLabel;
    JTextField monthTextField;
    Choice meterChooser,monthChooser;
    JButton submitButton,cancelButton;
    JPanel panel;
    CalculateBill(){
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 237, 201));
        
        calculateElectricBillLabel = new JLabel("Calculate Electricity Bill");
        calculateElectricBillLabel.setBounds(30, 10, 400, 30);
        calculateElectricBillLabel.setFont(new Font("Senserif",Font.PLAIN,26));
        calculateElectricBillLabel.setHorizontalAlignment(JLabel.CENTER);
        
        meterNumberLabel = new JLabel("Meter No");
        meterNumberLabel.setBounds(60, 70, 100, 30);
        
        meterChooser = new Choice();
        meterChooser.setBounds(200, 70, 180, 20);
        populateMeterChooser(meterChooser);
        
        
        JLabel nameHeadingLabel = new JLabel("Name");
        nameHeadingLabel.setBounds(60, 120, 100, 30);
        
        JLabel nameLabel = new JLabel();
        nameLabel.setBounds(200, 120, 180, 20);
        panel.add(nameLabel);
        
        JLabel addressHeadingLabel = new JLabel("Address");
        addressHeadingLabel.setBounds(60, 170, 100, 30);
        
        JLabel addressLabel = new JLabel();
        addressLabel.setBounds(200, 170, 180, 20);
        panel.add(addressLabel);
        
        populateNameAndAddress(nameLabel,addressLabel);
        
        
        unitsConsumedLabel = new JLabel("Units Consumed");
        unitsConsumedLabel.setBounds(60, 220, 150, 30);
        
        monthLabel = new JLabel("Month");
        monthLabel.setBounds(60, 270, 100, 30);
        
        monthTextField = new JTextField();
        monthTextField.setBounds(200, 220, 180, 20);
        
        
        meterChooser.addItemListener((ItemEvent ae) -> {
            try{
                Conn c = new Conn();
                ResultSet rs = c.statement.executeQuery("select * from customer where meter = '"+meterChooser.getSelectedItem()+"'");
                while(rs.next()){
                    nameLabel.setText(rs.getString("name"));
                    addressLabel.setText(rs.getString("address"));
                }
            }catch(SQLException e){}
        });
        
        monthChooser = new Choice();
        monthChooser.setBounds(200, 270, 180, 20);
        monthChooser.add("January");
        monthChooser.add("February");
        monthChooser.add("March");
        monthChooser.add("April");
        monthChooser.add("May");
        monthChooser.add("June");
        monthChooser.add("July");
        monthChooser.add("August");
        monthChooser.add("September");
        monthChooser.add("October");
        monthChooser.add("November");
        monthChooser.add("December");
        
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 350, 100, 25);
        submitButton.setBackground(new Color(0,102,27));
        submitButton.setForeground(Color.WHITE);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 350, 100, 25);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        ImageIcon calculateBillBannerIcon = new ImageIcon(CalculateBill.class.getResource("icon/calculate_bill_banner_img.jpg"));
        Image calculateBillBannerImage = calculateBillBannerIcon.getImage().getScaledInstance(220, 270,Image.SCALE_AREA_AVERAGING);
        calculateBillBannerIcon = new ImageIcon(calculateBillBannerImage);
        calculateBillBannerLabel = new JLabel(calculateBillBannerIcon);
        
        
        panel.add(calculateElectricBillLabel);
        panel.add(meterNumberLabel);
        panel.add(nameHeadingLabel);
        panel.add(addressHeadingLabel);
        panel.add(meterChooser);
        panel.add(monthLabel);
        panel.add(monthChooser);
        panel.add(unitsConsumedLabel);
        panel.add(monthTextField);
        panel.add(submitButton);
        panel.add(cancelButton);
        
        setLayout(new BorderLayout(30,30));
        
        add(panel,"Center");
        add(calculateBillBannerLabel,"West");
        
        
        getContentPane().setBackground(Color.WHITE);        
        setSize(750,500);
        setLocation(550,220);
        this.setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent events){
        
        if(events.getSource() == submitButton){
            String meterNo = meterChooser.getSelectedItem();
            String units = monthTextField.getText();
            String month = monthChooser.getSelectedItem();

            int unitsConsumed = Integer.parseInt(units);

            int totalBill = 0;
            try{
                Conn c = new Conn();
                ResultSet rs = c.statement.executeQuery("select * from tax");
                while(rs.next()){
                    totalBill = unitsConsumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 7
                    totalBill += Integer.parseInt(rs.getString("meter_rent"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch(NumberFormatException | SQLException e){}

            String query = "insert into bill values('"+meterNo+"','"+month+"','"+units+"','"+totalBill+"', 'Not Paid')";

            try{
                Conn c1 = new Conn();
                c1.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                this.setVisible(false);
            }catch(HeadlessException | SQLException e){
                e.printStackTrace();
            }

        }else if(events.getSource()== cancelButton){
            this.dispose();
        }        
    }
    
     private void populateMeterChooser(Choice meterChooser) {
        try{
            Conn connection = new Conn();
            ResultSet resulSet = connection.statement.executeQuery("select * from customer");
            while(resulSet.next()){
                meterChooser.add(resulSet.getString("meter"));
            }
        }catch(SQLException e){}
        
    }
     
    private void populateNameAndAddress(JLabel nameLabel, JLabel addressLabel) {
        try{
            Conn connection = new Conn();
            ResultSet resultSet = connection.statement.executeQuery("select * from customer where meter = '"+meterChooser.getSelectedItem()+"'");
            while(resultSet.next()){
                nameLabel.setText(resultSet.getString("name"));
                addressLabel.setText(resultSet.getString("address"));
            }
        }catch(SQLException e){}
        
    }
    
       
    public static void main(String[] args){
        new CalculateBill().setVisible(true);
    }

    

   
}