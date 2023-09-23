/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
/**
 *
 * @author haser
 */
public class JavaApplication1 {
    static File file = new File(System.getProperty("user.home"), "quotes.xml");
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws nu.xom.ParsingException
     */
    public static void main(String[] args) throws IOException, ParsingException{
        // TODO code application logic here
        System.out.println("Connecting to SQL Database");
        boolean loaded = false;
        List<String> quotes = new ArrayList<>();
        List<String> authors= new ArrayList<>();
        String connectionString = "jdbc:mariadb://10.0.0.148:3306/school";
        try {                                                                                                   // Try connecting to remote database
            Connection con = DriverManager.getConnection(connectionString, "guest", "guest");
            System.out.println("SQL Connection Successful");
            loaded = true;
            java.sql.Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM quotes");
            
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
            
            System.out.println("Create XML Backup");
            File file = new File( "quotes.xml");
            if(!file.exists()){
                file.createNewFile();
                }
            
            
            
            
            Element root = new Element("root");
            for (int i = 1; i <= quotes.size() ; i++){
                Element item = new Element("item");
                Element quote = new Element("quote");
                Element author = new Element("author");
                
                quote.appendChild(quotes.get(i-1));
                author.appendChild(authors.get(i-1));
                
                item.appendChild(quote);
                item.appendChild(author);
                
                root.appendChild(item);
            }
            
            Document doc = new Document(root);
            
            
            FileWriter writer = new FileWriter(file);
            writer.write(doc.toXML());
            writer.close();
            System.out.println("XML Backup Created");
            
        }
        catch(SQLException e){
            System.out.println("Connection not established. Trying XML Backup.");
            e.printStackTrace(System.out);
        }
        if(!loaded){                                                                                            // Try load from XML
            try{
                Builder parser = new Builder();
                Document doc = parser.build(file);
                Element root = doc.getRootElement();
                for(int i =0; i < root.getChildCount(); i++){
                    quotes.add(root.getChild(i).getChild(0).getValue());
                    authors.add(root.getChild(i).getChild(1).getValue());
                }
                System.out.println("XML Loaded Successfully");
                loaded = true;
            }
            catch (ParsingException ex){
                System.out.println("XML Loading: ParsingException");
                ex.printStackTrace(System.out);
            }
            catch (IOException ex){
                System.out.println("XML Loading: IOException");
                ex.printStackTrace(System.out);
            }
            
        }                                                                                                    
        if(!loaded){                                                                                            // Use default dataset
            quotes.add("Do or do not .. there is no try");authors.add("Yoda");
            quotes.add("Just keep swimming.");authors.add("Dory");
            quotes.add("Do you trust me?");authors.add("Aladdin");
            loaded = true;
        }
        if(loaded ){                                                   
            QuoteWindow win = new QuoteWindow(quotes, authors);
        }
        
    }
}
