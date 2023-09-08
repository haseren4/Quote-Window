/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import PersonalRepo.EnhancedFrame;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
/**
 *
 * @author haser
 */
public class QuoteWindow extends EnhancedFrame {
    static JLabel authorLabel = new JLabel("author");
    static JLabel quoteLabel = new JLabel("quote");
    List<String> quotes = new ArrayList<>();
    List<String> authors= new ArrayList<>();


    QuoteWindow(List<String> q, List<String> a) {
        init();
        quotes = q;
        authors = a;
        newQuote();
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                newQuote();
            }
        }, 0, 5000); // 30 seconds
    }
    private  void init(){
        
        authorLabel = new javax.swing.JLabel();
        quoteLabel = new javax.swing.JLabel();
        
        setTitle("Quotes");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        authorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        authorLabel.setText("authors");

        quoteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quoteLabel.setText("quotes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(quoteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(authorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quoteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(authorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

            pack();
    }
    private void newQuote(){
        System.out.println("New Quote");
        Random r = new Random();
        int i;
        i = Math.abs(r.nextInt() % quotes.size());
        
        quoteLabel.setText("<html><p style=\"width:200px\">"+quotes.get(i)+"</p></html>");
        authorLabel.setText(" - " +authors.get(i));
    }
    
}
