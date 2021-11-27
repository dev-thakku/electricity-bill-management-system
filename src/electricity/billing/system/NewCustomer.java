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
public class NewCustomer extends JFrame implements ActionListener {

    JLabel customerNameLabel, meterNoHeadingLabel, addressLabel, stateLabel, cityLabel, emailLabel, phoneNumberLabel, newCustomerImageLabel, meterNoLabel;
    JTextField customerNameTextField, t2, addressTextField, stateTextField, cityTextField, emailTextField, phoneNumberTextField;
    JButton nextButton, cancelButton;

    NewCustomer() {
        setLocation(600, 200);
        setSize(700, 500);
        this.setTitle("New Customer");
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBackground(new Color(252, 202, 0));

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 26);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(heading);

        customerNameLabel = new JLabel("Customer Name");
        customerNameLabel.setBounds(100, 80, 150, 20);

        customerNameTextField = new JTextField();
        customerNameTextField.setBounds(240, 80, 200, 20);
        panel.add(customerNameLabel);
        panel.add(customerNameTextField);

        meterNoHeadingLabel = new JLabel("Meter No");
        meterNoHeadingLabel.setBounds(100, 120, 100, 20);

        meterNoLabel = new JLabel();
        meterNoLabel.setBounds(240, 120, 200, 20);
        panel.add(meterNoHeadingLabel);
        panel.add(meterNoLabel);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(100, 160, 100, 20);

        addressTextField = new JTextField();
        addressTextField.setBounds(240, 160, 200, 20);
        panel.add(addressLabel);
        panel.add(addressTextField);

        cityLabel = new JLabel("City");
        cityLabel.setBounds(100, 200, 100, 20);

        cityTextField = new JTextField();
        cityTextField.setBounds(240, 200, 200, 20);
        panel.add(cityLabel);
        panel.add(cityTextField);

        stateLabel = new JLabel("State");
        stateLabel.setBounds(100, 240, 100, 20);

        stateTextField = new JTextField();
        stateTextField.setBounds(240, 240, 200, 20);
        panel.add(stateLabel);
        panel.add(stateTextField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(100, 280, 100, 20);

        emailTextField = new JTextField();
        emailTextField.setBounds(240, 280, 200, 20);
        panel.add(emailLabel);
        panel.add(emailTextField);

        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(100, 320, 120, 20);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(240, 320, 200, 20);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberTextField);

        nextButton = new JButton("Next");
        nextButton.setBounds(120, 390, 100, 25);
        nextButton.setBackground(new Color(0,102,27));
        nextButton.setForeground(Color.WHITE);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 390, 100, 25);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);

        panel.add(nextButton);
        panel.add(cancelButton);
        setLayout(new BorderLayout());

        add(panel, "Center");

        ImageIcon newCustomerIcon = new ImageIcon(NewCustomer.class.getResource("icon/new_customer_image.jpg"));
        Image newCustomerImage = newCustomerIcon.getImage().getScaledInstance(200, 300, Image.SCALE_AREA_AVERAGING);
        newCustomerIcon = new ImageIcon(newCustomerImage);
        newCustomerImageLabel = new JLabel(newCustomerIcon);
        add(newCustomerImageLabel, "West");

        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);

        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);

        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        meterNoLabel.setText("" + Math.abs(first));

    }

    @Override
    public void actionPerformed(ActionEvent events) {
        if (events.getSource() == nextButton) {
            String name = customerNameTextField.getText().trim();
            String meter = meterNoLabel.getText().trim();
            String address = addressTextField.getText().trim();
            String state = stateTextField.getText().trim();
            String city = cityTextField.getText().trim();
            String email = emailTextField.getText().trim();
            String phone = phoneNumberTextField.getText().trim();

            String query1 = "insert into customer values('" + name + "','" + meter + "','" + address + "','" + city + "','" + state + "','" + email + "','" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '', '', '')";

            try {
                Conn connection = new Conn();
                connection.statement.executeUpdate(query1);
                connection.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            } catch (HeadlessException | SQLException ex) {
                ex.printStackTrace();
            }
        } else if (events.getSource() == cancelButton) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new NewCustomer().setVisible(true);
    }
}
