
package electricity.billing.system;


import java.awt.*;
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

public class DepositDetails extends JFrame implements ActionListener{
 
    JTable depositeDetailsTable;
    JButton searchButton, printButton;
    JLabel meterNumberLabel, monthLabel;
    Choice meterChooser, monthChooser;
   
    DepositDetails(){
        super("Deposit Details");
        setSize(700,750);
        setLocation(600,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        
        meterNumberLabel = new JLabel("Sort by Meter Number");
        meterNumberLabel.setBounds(20, 20, 170, 20);
        add(meterNumberLabel);
        
        meterChooser = new Choice();
        
        monthLabel = new JLabel("Sort By Month");
        monthLabel.setBounds(400, 20, 100, 20);
        add(monthLabel);
        
        monthChooser = new Choice();
        
        
        try{
            Conn connection  = new Conn();
            String query1 = "select * from bill";
            ResultSet resultSet  = connection.statement.executeQuery(query1);
//            System.out.print(resultSet);
            depositeDetailsTable = new JTable(DbUtils.resultSetToTableModel(resultSet));
            
            String query2 = "select * from customer";
            resultSet = connection.statement.executeQuery(query2);
            while(resultSet.next()){
                meterChooser.add(resultSet.getString("meter"));
            }
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        meterChooser.setBounds(190,20, 150, 20);
        add(meterChooser);
        
        
        monthChooser.setBounds(520, 20, 150, 20);
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
        add(monthChooser);
        
        
        searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 90, 20);
        searchButton.setBackground(new Color(0,102,27));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);
        add(searchButton);
        
        printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 20);
        printButton.addActionListener(this);
        printButton.setBackground(new Color(125, 72, 163));
        printButton.setForeground(Color.WHITE);
        add(printButton);
        
        JScrollPane scrollPane = new JScrollPane(depositeDetailsTable);
        scrollPane.setBounds(0, 100, 700, 650);
        add(scrollPane);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent events){
        if(events.getSource() == searchButton){
            String query3 = "select * from bill where meter = '"+meterChooser.getSelectedItem()+"' AND month = '"+monthChooser.getSelectedItem()+"'";
            try{
                Conn connection = new Conn();
                ResultSet resultSet = connection.statement.executeQuery(query3);
//                depositeDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch(SQLException e){}
        }else if(events.getSource() == printButton){
            try{
                depositeDetailsTable.print();
            }catch(PrinterException e){}
        }
    }
    
    public static void main(String[] args){
        new DepositDetails().setVisible(true);
    }
    
}