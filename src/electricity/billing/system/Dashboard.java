package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */
public class Dashboard extends JFrame implements ActionListener {

    String meter;

    Dashboard(String meter, String person) {
        super("Electricity Billing System");
        this.meter = meter;

        setSize(1920, 1030);

        /* Adding background image */
        addBackgroundImage(person);

        /* First Column */
        JMenuBar menuBar = new JMenuBar();
        JMenu adminMenu = new JMenu("Admin");
        JMenuItem newCustomer = new JMenuItem("New Customer");
        JMenuItem customerDetails = new JMenuItem("Customer Details");
        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        

        /* ---- Customer Details ---- */
        newCustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon newCustomerMenuIcon = new ImageIcon(Dashboard.class.getResource("icon/new_customer_menu_icon.png"));
        Image newCustomerImage = newCustomerMenuIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(newCustomerImage));
        newCustomer.setMnemonic('D');
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        newCustomer.setBackground(Color.WHITE);

        /* ---- Meter Details ---- */
        customerDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon customerDetailsMenuIcon = new ImageIcon(Dashboard.class.getResource("icon/customer_details_icon.png"));
        Image customerDetailsImage = customerDetailsMenuIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(customerDetailsImage));
        customerDetails.setMnemonic('M');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        customerDetails.setBackground(Color.WHITE);

        /* ---- Deposit Details  ----- */
        depositDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon depositMenuIcon = new ImageIcon(Dashboard.class.getResource("icon/deposit_menu_icon.png"));
        Image depositIcon = depositMenuIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(depositIcon));
        depositDetails.setMnemonic('N');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        depositDetails.setBackground(Color.WHITE);

        calculateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon calculateBillMenuIcon = new ImageIcon(Dashboard.class.getResource("icon/calculate_bill_menu_icon.png"));
        Image calculateBillMenuImage = calculateBillMenuIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(calculateBillMenuImage));
        calculateBill.setMnemonic('B');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        calculateBill.setBackground(Color.WHITE);

        newCustomer.addActionListener(this);
        customerDetails.addActionListener(this);
        depositDetails.addActionListener(this);
        calculateBill.addActionListener(this);

        //-----------------------------
        /* Second Column */
        JMenu info = new JMenu("Information");
        JMenuItem updateInfo = new JMenuItem("Update profile");
        JMenuItem viewInfo = new JMenuItem("View profile");


        /* ---- Pay Bill ---- */
        updateInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon updateInfoIcon = new ImageIcon(Dashboard.class.getResource("icon/update_info_icon.png"));
        Image updateInfoImage = updateInfoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(updateInfoImage));
        updateInfo.setMnemonic('P');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        updateInfo.setBackground(Color.WHITE);

        /* ---- Last Bill ----*/
        viewInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon viewInfoIcon = new ImageIcon(Dashboard.class.getResource("icon/view_info_icon.png"));
        Image viewInfoImage = viewInfoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(viewInfoImage));
        viewInfo.setMnemonic('L');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        viewInfo.setBackground(Color.WHITE);

        updateInfo.addActionListener(this);
        viewInfo.addActionListener(this);

        // --------------------------------------------------------------------------------------------
        /* Second Column */
        JMenu user = new JMenu("User");
        JMenuItem payBill = new JMenuItem("Pay Bill");

        JMenuItem billDetails = new JMenuItem("Bill Details");
       

        /* ---- Pay Bill ---- */
        payBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon payBillIcon = new ImageIcon(Dashboard.class.getResource("icon/pay_bill_menu_icon.png"));
        Image payBillImage = payBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(payBillImage));
        payBill.setMnemonic('P');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        payBill.setBackground(Color.WHITE);

        /* ---- Last Bill ----*/
        billDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon lastBillIcon = new ImageIcon(Dashboard.class.getResource("icon/bill_details_menu_icon.png"));
        Image lastBillImage = lastBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(lastBillImage));
        billDetails.setMnemonic('L');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        billDetails.setBackground(Color.WHITE);

        payBill.addActionListener(this);
        billDetails.addActionListener(this);

        // --------------------------------------------------------------------------------------------- 
        /* Third Column*/
        JMenu report = new JMenu("Report");
        JMenuItem generateBill = new JMenuItem("Generate Bill");

        /* ---- Report ---- */
        generateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon generateBillIcon = new ImageIcon(Dashboard.class.getResource("icon/generate_bill_menu_icon.png"));
        Image image7 = generateBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(image7));
        generateBill.setMnemonic('R');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        generateBill.setBackground(Color.WHITE);

        generateBill.addActionListener(this);

        /*Fifth Column */
        JMenu exit = new JMenu("Logout");
        JMenuItem logout = new JMenuItem("Logout");

        /* ---- Exit ---- */
        logout.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon logoutIcon = new ImageIcon(Dashboard.class.getResource("icon/logout_menu_icon.png"));
        Image logoutImage = logoutIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        logout.setIcon(new ImageIcon(logoutImage));
        logout.setMnemonic('Z');
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        logout.setBackground(Color.WHITE);

        logout.addActionListener(this);

        // ---------------------------------------------------------------------------------------------
        adminMenu.add(newCustomer);
        adminMenu.add(customerDetails);
        adminMenu.add(depositDetails);
        adminMenu.add(calculateBill);

        info.add(updateInfo);
        info.add(viewInfo);

        user.add(payBill);
        user.add(billDetails);

        report.add(generateBill);

        exit.add(logout);

        if (person.equals("Admin")) {
            menuBar.add(adminMenu);
        } else {
            menuBar.add(info);
            menuBar.add(user);
            menuBar.add(report);
        }
        menuBar.add(exit);

        setJMenuBar(menuBar);

        setFont(new Font("Senserif", Font.BOLD, 16));
        setLayout(new FlowLayout());
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent events) {
        String menuItem = events.getActionCommand();
        switch (menuItem) {
            case "New Customer":
                new NewCustomer().setVisible(true);
                break;
            case "Customer Details":
                new CustomerDetails().setVisible(true);
                break;
            case "Calculate Bill":
                new CalculateBill().setVisible(true);
                break;
            case "Pay Bill":
                new PayBill(meter).setVisible(true);
                break;
            case "Logout":
                this.dispose();
                new Login().setVisible(true);
                break;
            case "Generate Bill":
                new GenerateBill(meter).setVisible(true);
                break;
            case "Deposit Details":
                new DepositDetails().setVisible(true);
                break;
            case "View profile":
                new ViewProfile(meter).setVisible(true);
                break;
            case "Update profile":
                new UpdateProfile(meter).setVisible(true);
                break;
            case "Bill Details":
                new BillDetails(meter).setVisible(true);
                break;
            default:
                break;
        }

    }
    
      private void addBackgroundImage(String person) {
        ImageIcon backgroundImageIcon = 
                ("Admin".equals(person)) ? new ImageIcon(Dashboard.class.getResource("icon/electricity_admin_dashboard_banner.jpg")):
                new ImageIcon(Dashboard.class.getResource("icon/electricity_user_banner.jpg"));
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(1900, 950, Image.SCALE_AREA_AVERAGING);
        backgroundImageIcon = new ImageIcon(backgroundImage);
        JLabel backgroundImageLabel = new JLabel(backgroundImageIcon);
        add(backgroundImageLabel);
    }

    public static void main(String[] args) {
        new Dashboard("", "").setVisible(true);
    }

  
}
