
package electricity.billing.system;

import java.awt.Color;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */

public class CustomerDetails extends JFrame implements ActionListener{
 
    JTable customerTable;
    JButton printButton;
   
    CustomerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(400,150);
        
        try{
            Conn connection  = new Conn();
            String query = "select * from customer";
            ResultSet resultSet  = connection.statement.executeQuery(query);
            customerTable = new JTable(DbUtils.resultSetToTableModel(resultSet));
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
        printButton = new JButton("Print");
        printButton.setBackground(Color.DARK_GRAY);
        printButton.setForeground(Color.WHITE);
        add(printButton,"South");
        JScrollPane sp = new JScrollPane(customerTable);
        add(sp);
        printButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        try{
            customerTable.print();
        }catch(PrinterException e){}
    }
    
    public static void main(String[] args){
        new CustomerDetails().setVisible(true);
    }
    
}
