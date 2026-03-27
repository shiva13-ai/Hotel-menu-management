package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hsignupgui extends JFrame implements ActionListener{
    private JPasswordField tfpass;
    public JTextArea tahsup;
    String rep=null;
    JButton ba;
    boolean backp=false;
    public Hsignupgui(){
        setSize(2000,1800);
        Color c=new Color(211,211,211);
        getContentPane().setBackground(c);
        setTitle("Hotel_Sign-up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        JLabel l1=new JLabel("Create new Hotel_Account:-");
        JLabel l2=new JLabel("Create Password:");
        Font f1=new Font("Arial",Font.BOLD,24);
        Font f2=new Font("Monospaced",Font.BOLD,20);
        l1.setFont(f1);
        l2.setFont(f2);
        l1.setBounds(650,200,800,24);
        l2.setBounds(600,270,200,20);
        add(l1);  add(l2);
        tfpass=new JPasswordField();
        tfpass.setBounds(830,270,600,20);
        tfpass.addActionListener(this);
        add(tfpass);
        tahsup=new JTextArea();
        tahsup.setBounds(600,350,800,400);
        add(tahsup);
        ba=new JButton("<--Back");
        ba.setBounds(50,50,80,22);
        ba.addActionListener(this);
        add(ba);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("<--Back")){ backp=true; }
        else{
            String st=new String(tfpass.getPassword());
            if(st.length()>=8){ rep=st; }
        }
    }
    public String getpass(){ return rep; }
    public boolean backpress(){ return backp; }
}