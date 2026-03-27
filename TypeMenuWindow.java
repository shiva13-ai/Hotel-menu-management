package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TypeMenuWindow extends JDialog implements ActionListener {

    private int choice = -1;

    public TypeMenuWindow(Frame parent) {
        super(parent, "Choose Option", true);

        setSize(300, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel label = new JLabel("Select an option:", SwingConstants.CENTER);
        add(label);

        JButton b1 = new JButton("Show Menu");
        JButton b2 = new JButton("Sort by Rating");
        JButton b3 = new JButton("Sort by Price");
        JButton b4 = new JButton("Exit");

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);  
        b2.addActionListener(this);  
        b3.addActionListener(this);  
        b4.addActionListener(this);  

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
         String src = ae.getActionCommand();

        if (src.equals("Show Menu")) choice = 1;
        else if (src.equals("Sort by Rating")) choice = 2;
        else if (src.equals("Sort by Price")) choice = 3;
        else if (src.equals("Exit")) choice = 4;

        dispose();
    }
    public int getChoice() {
        return choice;
    }
}