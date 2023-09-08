/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import PersonalRepo.EnhancedFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author haser
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello, World!");
        System.out.println("Connecting to SQL Database");
        boolean loaded = false;
        List<String> quotes = new ArrayList<>();
        List<String> authors= new ArrayList<>();
        String connectionString = "jdbc:mariadb://10.0.0.148:3306/school";
        EnhancedFrame ef = new EnhancedFrame();                                                                         // SPLASH GRAPHIC
        try {                                                                                                   // Try connecting to remote database
            Connection con = DriverManager.getConnection(connectionString, "guest", "guest");
            System.out.println("SQL Connection Successful");
            loaded = true;
            java.sql.Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM quotes");
            
            System.out.println(res.toString());
            while(res.next()){
                quotes.add(res.getString("quote"));
                authors.add( res.getString("author"));
                
                
                
                
                
                
                
                
            }
            res.close();
            statement.close();
            con.close();
            System.out.println("SQL Connection Closed");
            loaded = true;
            System.out.println("Data Loaded Successfully");
            
            System.out.println("Create XML Backup\n *** NOT IMPLEMENTED ***");
            
            System.out.println("XML Backup Created");
            
        }
        catch(SQLException e){
            System.out.println("Connection not established. Trying XML Backup.");
            e.printStackTrace(System.out);
        }
        if(!loaded){                                                                                            // Try load from XML
            
        }                                                                                                    
        if(!loaded){                                                                                            // Use default dataset
        
            loaded = true;
        }
        if(loaded){                                                             
            ef.show(false);
            QuoteWindow win = new QuoteWindow(quotes, authors);
        }                                                                                                 // KILL SPLASH GRAPHIC
        
    }
    
}
