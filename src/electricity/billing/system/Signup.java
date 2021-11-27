package electricity.billing.system;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Signup extends JFrame implements ActionListener{
    JPanel panel;
    JTextField usernameTextField, nameTextField,meterNumberTextField;
    JPasswordField passwordField;
    Choice chooseAccount;
    JButton createButton, backButton;
    Signup(){
        initComponant();
    }
    
    private void initComponant() {
        this.setTitle("Create Account");
        this.setResizable(false);
        setBounds(600, 250, 700, 400);
        
        panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setLayout( null);
        panel.setBackground(Color.WHITE);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.DARK_GRAY);
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        usernameLabel.setBounds(100, 50, 100, 20);
        panel.add(usernameLabel);
        
        usernameTextField = new JTextField();
        usernameTextField.setBounds(260, 50, 150, 20);
        panel.add(usernameTextField);
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(100, 90, 100, 20);
        panel.add(nameLabel);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(260, 90, 150, 20);
        panel.add(nameTextField);
        
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(100, 130, 100, 20);
        panel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(260, 130, 150, 20);
        panel.add(passwordField);
        
        
        JLabel accountTypeLabel = new JLabel("Create Account As");
        accountTypeLabel.setForeground(Color.DARK_GRAY);
        accountTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        accountTypeLabel.setBounds(100, 170, 150, 20);
        panel.add(accountTypeLabel);
        
        chooseAccount = new Choice();
        chooseAccount.add("Admin");
        chooseAccount.add("Customer");
        chooseAccount.setBounds(260, 170, 150, 20);
        panel.add(chooseAccount);
        
        JLabel meteNumberLabel = new JLabel("Meter Number");
        meteNumberLabel.setForeground(Color.DARK_GRAY);
        meteNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        meteNumberLabel.setBounds(100, 210, 120, 20);
        meteNumberLabel.setVisible(false);
        panel.add(meteNumberLabel);
        
        meterNumberTextField = new JTextField();
        meterNumberTextField.setBounds(260, 210, 150, 20);
        meterNumberTextField.setVisible(false);
        panel.add(meterNumberTextField);
        
       
        
        chooseAccount.addItemListener((ItemEvent ae) -> {
               String user = chooseAccount.getSelectedItem();
               if(user.equals("Customer")){
                   meteNumberLabel.setVisible(true);
                   meterNumberTextField.setVisible(true);
               }else{
                   meteNumberLabel.setVisible(false);
                   meterNumberTextField.setVisible(false);
               }
        });
        
        
        createButton = new JButton("Create");
        createButton.setBackground(new Color(0,102,27));
        createButton.setForeground(Color.WHITE);
        createButton.setBounds(140, 290, 120, 30);
        createButton.addActionListener(this);
        panel.add(createButton);
        
        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(300, 290, 120, 30);
        backButton.addActionListener(this);
        panel.add(backButton);
        
        ImageIcon signupIcon = new ImageIcon(Signup.class.getResource("icon/user.png"));
        Image signupImage = signupIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        signupIcon = new ImageIcon(signupImage);
        JLabel signupImageLabel = new JLabel(signupIcon);
        signupImageLabel.setBounds(450, 30, 250, 250);
        panel.add(signupImageLabel);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == createButton){
            String username = usernameTextField.getText().trim();
            String name = nameTextField.getText().trim();
            String password = passwordField.getText().trim();
            String user = chooseAccount.getSelectedItem();
            String meter = meterNumberTextField.getText().trim();
            
            if(!validate(username,name,password)){
                JOptionPane.showMessageDialog(null, "Please fill all the field");
                return;
            }
            
            try{
                Conn connection = new Conn();
                String query = null;
                if(user.equals("Admin")){
                    query = "insert into login values('"+meter+"', '"+username+"', '"+name+"', '"+password+"', '"+user+"')";
                }else{
                    query = "update login set username = '"+username+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = '"+meterNumberTextField.getText()+"'";
                }
                
                connection.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            }catch(HeadlessException | SQLException e){
                e.printStackTrace();
            }
       
        } else if(ae.getSource()== backButton){
           this.dispose();
            new Login().setVisible(true);
        }
    }
    
     private boolean validate(String username, String name, String password) {
         return !(username.equals("") || name.equals("") || password.equals(""));
    }

        
    public static void main(String[] args){
        new Signup().setVisible(true);
    }

   
    
}