package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hlobby extends JFrame implements ActionListener, KeyListener{
    JButton ba,shod,chap,dim,insed,ded,updi,bx1,bx15,bx2,bx25;
    boolean buttonpress=false;
    public JTextArea hlota;
    public int rei,doit=0;
    public String rest,d,ty;
    public float c=-1;
    JLabel ll;
    JTextField t1,t2,tfdn,tfdt,tfdp,cptfdn,cptfdp;
    public Hlobby(){
        setTitle("Hotel-user lobby");
        setSize(1800,1600);
        Color c=new Color(211,211,211);
        getContentPane().setBackground(c);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        JLabel l=new JLabel("___Login Successfully___");
        Font f1=new Font("Arial",Font.BOLD,26);
        l.setBounds(800,100,500,26);
        l.setFont(f1);
        add(l);

        ba=new JButton("<--Back");
        shod=new JButton("Show Details");
        chap=new JButton("Change Password");
        dim=new JButton("Display Menu");
        insed=new JButton("Insert a Dish");
        ded=new JButton("Delete a Dish");
        updi=new JButton("Update Prize of a Dish");

        ba.setBounds(50,50,150,22); ba.addActionListener(this);
        shod.setBounds(870,170,330,22); shod.addActionListener(this);
        chap.setBounds(870,210,330,22); chap.addActionListener(this);
        dim.setBounds(870,250,330,22);  dim.addActionListener(this);
        insed.setBounds(870,290,330,22);  insed.addActionListener(this);
        ded.setBounds(870,330,330,22);   ded.addActionListener(this);
        updi.setBounds(870,370,330,22);  updi.addActionListener(this);

        add(ba); add(shod); add(chap); add(dim); add(insed);
        add(ded); add(updi);

        hlota=new JTextArea();
        hlota.setBounds(700,410,700,500);
        hlota.setEditable(false);
        add(hlota);

        ll=new JLabel("<-Dish Name:");
        Font f2=new Font("Arial",Font.BOLD,22);
        ll.setFont(f2);
        ll.setBounds(1080,330,200,22);

        bx1=new JButton("x");
        bx1.addActionListener(this);
        bx15=new JButton("x");
        bx15.addActionListener(this);
        bx2=new JButton("x");
        bx2.addActionListener(this);
        bx25=new JButton("x");
        bx25.addActionListener(this);

        t1=new JTextField();
        t1.setBounds(1300,330,300,22);
        t2=new JTextField();
        t2.setBounds(1200,210,500,22);
        tfdn=new JTextField();
        tfdt=new JTextField();
        tfdp=new JTextField();
        tfdn.setBounds(80,230,420,22);
        tfdt.setBounds(80,330,420,22);
        tfdp.setBounds(80,430,420,22);
        cptfdn=new JTextField();
        cptfdp=new JPasswordField();
        cptfdn.setBounds(80,230,420,22);
        cptfdp.setBounds(80,330,420,22);

        t1.addKeyListener(this);
        t2.addKeyListener(this);
        tfdn.addKeyListener(this);
        tfdt.addKeyListener(this);
        tfdp.addKeyListener(this);
        cptfdn.addKeyListener(this);
        cptfdp.addKeyListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        String st=ae.getActionCommand();
        if(st.equals("<--Back")){
            rei=7;
        }
        if(st.equals("Show Details")){
            rei=1;
        }
        if(st.equals("Change Password")){
            rei=2;
        }
        if(st.equals("Display Menu")){
            rei=3;
        }
        if(st.equals("Insert a Dish")){
            rei=4;
        }
        if(st.equals("Delete a Dish")){
            rei=5;
        }
        if(st.equals("Update Prize of a Dish")){
            rei=6;
        }
        if(st.equals("x")){
            doit=-1;
        }
    }

    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke){
        char c=ke.getKeyChar();
        if(c=='\n'){   // '\n'=="Enter"
            doit=1;
        }
    }

    public String getchapass(){
        doit = 0;
        add(t2);
        bx2.setBounds(1680,190,50,22);  
        add(bx2);
        validate();
        repaint();
        while(true){  
            if(doit == 1){ 
                if(t2.getText().length()>=8){
                    rest = t2.getText(); 
                    doit=0; 
                    break;                 
                } 
                else {
                    hlota.setText("---Password must be 8 or more characters---");
                    doit=0;                
                }
            }
            if(doit == -1){ 
                doit=0; 
                t2.setText("");
                remove(t2); remove(bx2);
                validate();
                repaint();
                return null; 
            } 
            try{ Thread.sleep(80); }catch(Exception e){}
        }
        t2.setText("");
        remove(t2); remove(bx2);
        validate();
        repaint();
        return rest;  
    }

    public void inseddac(){
        JLabel l0=new JLabel("Insert Dish.");
        JLabel l1=new JLabel("Dish name:-");
        JLabel l2=new JLabel("Dish type(v/nv):-");
        JLabel l3=new JLabel("Dish Prize:-");
        Font f1=new Font("Arial",Font.BOLD,26);
        Font f2=new Font("Segoe UI",Font.PLAIN,22);
        l0.setFont(f1); l1.setFont(f2); l2.setFont(f2); l3.setFont(f2);
        l0.setBounds(80,150,200,26);
        l1.setBounds(100,200,200,22);
        l2.setBounds(100,300,200,22);
        l3.setBounds(100,400,200,22);
        add(l0); add(l1); add(l2); add(l3);

        add(tfdn);  add(tfdt);  add(tfdp);

        bx15.setBounds(330,150,50,22);
        add(bx15);
        validate();
        repaint();
        while(true){
            if(doit == 1){ 
                doit=0;
                d = tfdn.getText(); 
                ty=tfdt.getText();
                try{
                    if (d.length() == 0 || ty.length() == 0){
                        throw new Exception("Empty fields!");                           
                    }
                    if(!ty.equalsIgnoreCase("v") && !ty.equalsIgnoreCase("nv")){
                        throw new DishtypeException("!!Enter Valid Data!!");   
                    }
                    c=Float.parseFloat(tfdp.getText());   
                    if(c==-1){
                        throw new Exception("Empty fields!");                           
                    }
                }
                catch(DishtypeException e){
                    hlota.setText("!!Enter Valid Data!!"); 
                    tfdt.setText("");
                    c=-1;
                    continue;    
                }
                catch(NumberFormatException e){
                    hlota.setText("!!Enter Valid Prize!!");
                    tfdp.setText("");
                    c=-1;
                    continue;
                }
                catch (Exception e) {
                    hlota.setText("!!Enter all fields!!");
                    tfdn.setText(""); tfdt.setText(""); tfdp.setText("");
                    c=-1;
                    continue;
                }
                remove(l0); remove(l1); remove(l2); remove(l3);
                tfdn.setText(""); tfdt.setText(""); tfdp.setText("");
                remove(tfdn); remove(tfdt); remove(tfdp);
                remove(bx15);
                validate();
                repaint();
                hlota.setText("\n___Dish Successfully Inserted___.");  
                break;                                          
            }
            if(doit == -1){ 
                doit=0; 
                remove(l0); remove(l1); remove(l2); remove(l3);
                tfdn.setText(""); tfdt.setText(""); tfdp.setText("");
                remove(tfdn); remove(tfdt); remove(tfdp);
                remove(bx15);
                validate();
                repaint(); 
                return;
            }
            try{ Thread.sleep(80); }
            catch(Exception e){}
        }
    }

    public String getdina(){
        doit = 0; 
        add(ll);
        add(t1);
        bx1.setBounds(1080,308,50,22);
        add(bx1);
        
        validate();
        repaint();

        while(true){
            if(doit == 1){ 
                doit=0;
                rest = t1.getText(); 
                break;
            }
            if(doit == -1){ 
                doit=0; 
                t1.setText("");
                remove(ll); remove(t1); remove(bx1);
                validate();
                repaint(); 
                return null;
            }
            try{ Thread.sleep(80); }
            catch(Exception e){}
        }
        t1.setText("");
        remove(ll); remove(t1); remove(bx1);
        validate();
        repaint();
        return rest;
    }

    public void setPrize(){
        JLabel l0=new JLabel("+++ Update Prize +++.");
        JLabel l1=new JLabel("Dish name:-");
        JLabel l2=new JLabel("Dish Prize:-");
        Font f1=new Font("Arial",Font.BOLD,26);
        Font f2=new Font("Segoe UI",Font.PLAIN,22);
        l0.setFont(f1); l1.setFont(f2); l2.setFont(f2);

        l0.setBounds(80,150,300,26);
        l1.setBounds(100,200,200,22);
        l2.setBounds(100,300,200,22);
        add(l0); add(l1); add(l2);

        add(cptfdn); add(cptfdp);

        bx25.setBounds(380,150,50,22);
        add(bx25);
        validate();
        repaint();

        while(true){
            if(doit == 1){ 
                doit=0;
                d = cptfdn.getText(); 
                try{
                    if (d.length() == 0){
                        throw new Exception("Empty fields!");                  
                    }
                    c=Float.parseFloat(cptfdp.getText());   
                    if(c==-1){
                        throw new Exception("Empty fields!");                  
                    }
                }
                catch(NumberFormatException e){
                    hlota.setText("!!Enter Valid Prize!!");
                    cptfdp.setText("");
                    c=-1;
                    continue;
                }
                catch (Exception e) {
                    hlota.setText("!!Enter all fields!!");
                    cptfdn.setText(""); cptfdp.setText("");
                    c=-1;
                    continue;
                }
                remove(l0); remove(l1); remove(l2);
                cptfdn.setText(""); cptfdp.setText("");
                remove(cptfdn); remove(cptfdp);
                remove(bx25);
                validate();
                repaint();
                hlota.setText("\n___Dish Prize Updated Successfully___.");  
                break;                                                      
            }
            if(doit == -1){ 
                doit=0; 
                remove(l0); remove(l1); remove(l2);
                cptfdn.setText(""); cptfdp.setText("");
                remove(cptfdn); remove(cptfdp);
                remove(bx25);
                validate();
                repaint(); 
                return;                               
            }
            try{ Thread.sleep(80); }
            catch(Exception e){}
        }
    }

    public int result(){
        return rei;
    }
}