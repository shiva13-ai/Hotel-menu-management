package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startingui extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    int action_result;

    public Startingui() {
        Color c = new Color(192, 192, 192);

        // Frame
        getContentPane().setBackground(c);
        setTitle("Hotel_Menu");
        setSize(2000, 1800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label
        JLabel l1 = new JLabel("--Welcome to Hotel_Menu_Checker--");
        Font f1 = new Font("Arial", Font.BOLD, 24);
        l1.setFont(f1);
        l1.setBounds(650, 200, 800, 24);
        add(l1);

        // Buttons
        b1 = new JButton("New");
        b2 = new JButton("Existing");
        b3 = new JButton("Exit");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        b1.setBounds(700, 300, 120, 30);
        b2.setBounds(700, 350, 120, 30);
        b3.setBounds(700, 400, 120, 30);

        add(b1);
        add(b2);
        add(b3);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        String st = ae.getActionCommand();
        if (st.equals("New")) action_result = 1;
        if (st.equals("Existing")) action_result = 2;
        if (st.equals("Exit")) {
            action_result = 3;
            dispose();
            System.exit(0);
        }
    }

    public int swre() {
        return action_result;
    }
}