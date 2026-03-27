package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserTypegui extends JFrame implements ActionListener {

    JButton b1, b2, b3;
    int action_result;

    public UserTypegui() {

        setTitle("User-Type");

        // Background
        Color c = new Color(211, 211, 211);
        getContentPane().setBackground(c);

        setSize(2000, 1800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label
        JLabel l = new JLabel("Who are you?");
        Font f1 = new Font("Arial", Font.BOLD, 24);
        l.setFont(f1);
        l.setBounds(650, 200, 400, 24);
        add(l);

        // Buttons
        b1 = new JButton("Hotel Management");
        b2 = new JButton("Customer");
        b3 = new JButton("<--Back");

        b1.setBounds(700, 300, 180, 30);
        b2.setBounds(700, 350, 150, 30);
        b3.setBounds(50, 50, 100, 30);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String st = ae.getActionCommand();

        if (st.equals("Hotel Management")) action_result = 1;
        if (st.equals("Customer")) action_result = 2;
        if (st.equals("<--Back")) action_result = 3;

        if (action_result == 3) {
            dispose();
        }
    }

    public int utre() {
        return action_result;
    }
}
