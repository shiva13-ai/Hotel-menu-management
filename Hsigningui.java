package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hsigningui extends JFrame implements ActionListener{
    private JTextField tfhi;
    private JPasswordField tfhpass;
    public JTextArea hota;
    JButton sib,ba;
    String pass=null;
    boolean backpress=false;
    public Hsigningui(){
        setSize(2000,1800);
        setTitle("Hotel_Sign-in");
        Color c=new Color(211,211,211);
        getContentPane().setBackground(c);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        JLabel l1=new JLabel("__WELCOME BACK__");
        JLabel l2=new JLabel("Enter Hotel Id:");
        JLabel l3=new JLabel("Enter password:");
        Font f1=new Font("Arial",Font.BOLD,24);
        Font f2=new Font("Monospaced",Font.BOLD,20);
        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);
        l1.setBounds(650,200,500,24);
        l2.setBounds(250,270,500,20);
        l3.setBounds(250,320,500,20);
        add(l1);  add(l2);  add(l3);
        tfhi=new JTextField();
        tfhpass=new JPasswordField();
        tfhi.setBounds(760,270,800,20);
        tfhpass.setBounds(760,320,800,20);
        add(tfhi);  add(tfhpass);
        sib=new JButton("Sign-in");
        ba=new JButton("<--Back");
        sib.setBounds(750,400,100,20);
        ba.setBounds(50,50,80,22);
        sib.addActionListener(this);
        ba.addActionListener(this);
        add(sib);   add(ba);
        hota=new JTextArea();
        hota.setBounds(250,450,600,400);
        hota.setEditable(false);
        add(hota);
    }
    public void actionPerformed(ActionEvent ae){
        String st=ae.getActionCommand();
        if(st.equals("<--Back")){   backpress=true;   }
        if(st.equals("Sign-in")){ pass=new String(tfhpass.getPassword()); }
    }
    public boolean backpressed(){ return backpress; }
    public String getpass(){ return pass; }
    public int getuserid(){ return Integer.parseInt(tfhi.getText()); }
}