
package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

/**
 * @author souma (SMIT)
 * @version v1.0
 *
 */

public class BillDetails extends JFrame{
 
    JTable BillDetailsTable;
    
    BillDetails(String meter){
        super("Bill Details");
        setSize(700,650);
        setLocation(600,150);
        setLayout(null);
        this.setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        
        try{
            Conn connection  = new Conn();
            String query = "select * from bill where meter = " + meter;
            ResultSet resultSet  = connection.statement.executeQuery(query);
            
            BillDetailsTable = new JTable(DbUtils.resultSetToTableModel(resultSet));
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
        JScrollPane sp = new JScrollPane(BillDetailsTable);
        sp.setBounds(0, 0, 700, 650);
        add(sp);
        
    }
    
    public static void main(String[] args){
        new BillDetails("").setVisible(true);
    }
    
}