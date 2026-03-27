package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hotel.*;

public class Ulobby extends JFrame implements ActionListener, KeyListener, ItemListener{
    public int rei,state=0;
    public float ra=-1;
    public String ty,rest,dn=null;
    Hotel harr[];
    JButton ba,shod,chap,dim,sor,sop,rate,bx1,bx2,bx3,bx4,bx5,bx6;
    public JTextArea ulota;
    JTextField t1,t2,t3,t3dn,t3r,t4,t5,t6;
    JComboBox<String> cho,cho1,cho2,cho3,cho6;
    DefaultListModel<String> lm, lm1, lm2, lm3, lm6;
    Font f1;
    int doit=0;

    public Ulobby(Hotel harr[]){
        this.harr=harr;
        setTitle("Customer-user Lobby");
        setSize(1800,1600);
        setLayout(null);
        getContentPane().setBackground(new Color(211,211,211));
        JLabel l=new JLabel("___Login Successfully___");
        f1=new Font("Arial",Font.BOLD,26);
        l.setBounds(700,100,500,26);
        l.setFont(f1);
        add(l);
        
        JMenuBar mbar = new JMenuBar();                    
        JMenu dishty=new JMenu("Dish Type");
        JMenuItem div=new JMenuItem("Veg");
        div.addActionListener(this);
        JMenuItem dinv=new JMenuItem("Non-Veg");
        dinv.addActionListener(this);
        dishty.add(div); dishty.add(dinv);
        mbar.add(dishty);
        setJMenuBar(mbar);

        ba=new JButton("<--Back");
        shod=new JButton("Show Details");
        chap=new JButton("Change Password");
        dim=new JButton("Display Menu");
        sor=new JButton("Sort by Rate");
        sop=new JButton("Sort by Prize");
        rate=new JButton("Rate a Dish");
        ba.setBounds(30,30,100,22); ba.addActionListener(this);
        shod.setBounds(740,170,270,22); shod.addActionListener(this);
        chap.setBounds(740,210,270,22); chap.addActionListener(this);
        dim.setBounds(740,250,270,22);  dim.addActionListener(this);
        sor.setBounds(740,290,270,22);  sor.addActionListener(this);
        sop.setBounds(740,330,270,22); sop.addActionListener(this);
        rate.setBounds(740,370,270,22); rate.addActionListener(this);
        add(ba); add(shod); add(chap); add(dim); add(sor);
        add(sop);  add(rate);

        ulota=new JTextArea();
        ulota.setBounds(600,410,700,500);
        ulota.setEditable(false);
        add(ulota);

        t1=new JTextField();
        t1.setBounds(1200,210,500,22);
        t1.addKeyListener(this);
        bx1=new JButton("x");
        bx1.addActionListener(this);

        t2=new JTextField();
        t2.setBounds(1030,250,500,22);
        t2.addKeyListener(this);
        cho=new JComboBox<>();
        int i=0;
        while(harr[i]!=null){
            String stit=Integer.toString(harr[i].hotelid);
            cho.addItem(stit);
            i++;
        }
        cho.addItemListener(this);
        bx2=new JButton("x");
        bx2.setBounds(1510,228,50,22);
        bx2.addActionListener(this);

        t4=new JTextField();
        t4.setBounds(1030,290,500,22);
        t4.addKeyListener(this);
        cho2=new JComboBox<>();
        i=0;
        while(harr[i]!=null){
            String stit=Integer.toString(harr[i].hotelid);
            cho2.addItem(stit);
            i++;
        }
        cho2.addItemListener(this);
        bx4=new JButton("x");
        bx4.setBounds(1510,268,50,22);
        bx4.addActionListener(this);

        t5=new JTextField();
        t5.setBounds(1030,330,500,22);
        t5.addKeyListener(this);
        cho3=new JComboBox<>();
        i=0;
        while(harr[i]!=null){
            String stit=Integer.toString(harr[i].hotelid);
            cho3.addItem(stit);
            i++;
        }
        cho3.addItemListener(this);
        bx5=new JButton("x");
        bx5.setBounds(1510,208,50,22);
        bx5.addActionListener(this);

        t3=new JTextField();
        t3dn=new JTextField();
        t3r=new JTextField();
        t3.addKeyListener(this);
        cho1=new JComboBox<>();
        i=0;
        while(harr[i]!=null){
            String stit=Integer.toString(harr[i].hotelid);
            cho1.addItem(stit);
            i++;
        }
        cho1.addItemListener(this);
        t3dn.addKeyListener(this);
        t3r.addKeyListener(this);
        bx3=new JButton("x");
        bx3.setBounds(330,150,50,22);
        bx3.addActionListener(this);

        t6=new JTextField();
        t6.setBounds(50,150,500,22);
        t6.addKeyListener(this);
        cho6=new JComboBox<>();
        i=0;
        while(harr[i]!=null){
            String stit=Integer.toString(harr[i].hotelid);
            cho6.addItem(stit);
            i++;
        }
        cho6.addItemListener(this);
        bx6=new JButton("x");
        bx6.setBounds(1510,128,50,22);
        bx6.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        String st=ae.getActionCommand();
        if(st.equals("<--Back")){
            rei=8;
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
        if(st.equals("Veg")){
            rei=4;
            ty="v";
        }
        if(st.equals("Non-Veg")){
            rei=4;
            ty="nv";
        }
        if(st.equals("Sort by Rate")){
            rei=5;
        }
        if(st.equals("Sort by Prize")){
            rei=6;
        }
        if(st.equals("Rate a Dish")){
            rei=7;
        }
        if(st.equals("x")){
            doit=-1;
        }
    }

    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke){
        char c=ke.getKeyChar();
        if(c=='\n'){   
            doit=1;
        }
    }

    public void itemStateChanged(ItemEvent ie){
        if (state == 1 && cho.getSelectedItem() != null) {
        t2.setText(cho.getSelectedItem().toString());
        }
        if (state == 2 && cho1.getSelectedItem() != null) {
            t3.setText(cho1.getSelectedItem().toString());
         }
        if (state == 3 && cho2.getSelectedItem() != null) {
            t4.setText(cho2.getSelectedItem().toString());
        }
        if (state == 4 && cho3.getSelectedItem() != null) {
            t5.setText(cho3.getSelectedItem().toString());
       }
       if (state == 6 && cho6.getSelectedItem() != null) {
            t6.setText(cho6.getSelectedItem().toString());
        }
    }

    public String getchapuss(){
        doit = 0;
        add(t1);
        bx1.setBounds(1680,190,50,22);  
        add(bx1);
        validate();
        repaint();
        while(true){  
            if(doit == 1){ 
                if(t1.getText().length()>=8){
                    rest = t1.getText(); 
                    doit=0; 
                    break;                 
                } 
                else {
                    ulota.setText("---Password must be 8 or more characters---");
                    doit=0;                
                }
            }
            if(doit == -1){ 
                doit=0; 
                t1.setText("");
                remove(t1); remove(bx1);
                validate();
                repaint();
                return null; 
            } 
            try{ Thread.sleep(80); }catch(Exception e){}
        }
        t1.setText("");
        remove(t1); remove(bx1);
        validate();
        repaint();
        return rest;  
    }

    public int getsehi(){
        state=1;
        cho.setBounds(1030,273,500,22);
        add(t2); add(cho); add(bx2);
        String st=t2.getText();
        while(st.equals("")){
            if(doit==-1){
                doit=0;
                cho.setSelectedIndex(-1);
                remove(t2); remove(cho); remove(bx2);
                revalidate();
                repaint();
                return -1;
            }
            try{ Thread.sleep(100); }
            catch(InterruptedException e){}
            st=t2.getText();
        }
        t2.setText("");
        cho.setSelectedIndex(-1);
        remove(t2); remove(cho); remove(bx2);
        revalidate();
        repaint();
        int res=Integer.parseInt(st);
        return res;
    }

    public int getsehisp(){
        state=3;
        cho2.setBounds(1030,313,500,22);
        add(t4); add(cho2); add(bx4);
        String st=t4.getText();
        while(st.equals("")){
            if(doit==-1){
                doit=0;
                cho2.setSelectedIndex(-1);
                remove(t4); remove(cho2); remove(bx4);
                revalidate();
                repaint();
                return -1;
            }
            try{ Thread.sleep(100); }
            catch(InterruptedException e){}
            st=t4.getText();
        }
        t4.setText("");
        cho2.setSelectedIndex(-1);
        remove(t4); remove(cho2); remove(bx4);
        revalidate();
        repaint();
        int res=Integer.parseInt(st);
        return res;
    }

    public int getsehisr(){
        state=4;
        cho3.setBounds(1030,353,500,22);
        add(t5); add(cho3); add(bx5);
        String st=t5.getText();
        while(st.equals("")){
            if(doit==-1){
                doit=0;
                cho3.setSelectedIndex(-1);
                remove(t5); remove(cho3); remove(bx5);
                revalidate();
                repaint();
                return -1;
            }
            try{ Thread.sleep(100); }
            catch(InterruptedException e){}
            st=t5.getText();
        }
        t5.setText("");
        cho3.setSelectedIndex(-1);
        remove(t5); remove(cho3); remove(bx5);
        revalidate();
        repaint();
        int res=Integer.parseInt(st);
        return res;
    }

    public int getsehir(){
        state=2;
        JLabel l1=new JLabel("Rating-Dish:-");
        JLabel l2=new JLabel("Enter Hotel-id:");
        JLabel l3=new JLabel("Enter dish name:");
        JLabel l4=new JLabel("Enter Rating(1-5):");
        Font f2=new Font("Segoe UI",Font.PLAIN,22);
        l1.setFont(f1); l2.setFont(f2); l3.setFont(f2); l4.setFont(f2);
        l1.setBounds(80,150,400,26);
        l2.setBounds(100,200,400,22);
        l3.setBounds(100,320,400,22);
        l4.setBounds(100,420,400,22);
        add(l1); add(l2); add(l3); add(l4);
        
        t3.setBounds(100,220,200,22);
        cho1.setBounds(100,243,200,22);
        t3dn.setBounds(100,340,200,22);
        t3r.setBounds(100,440,200,22);
        add(t3); add(cho1); add(t3dn); add(t3r); add(bx3);
        validate();
        repaint();
        String shi;
        while(true){
            if(doit == 1){ 
                doit=0;
                shi = t3.getText(); 
                dn=t3dn.getText();
                try{
                if (shi.length() == 0 || dn.length() == 0){
                    throw new Exception("Empty fields!");                           }
                ra=Float.parseFloat(t3r.getText());   
                if(ra==-1){
                    throw new Exception("Empty fields!");                           }
                }
                catch(NumberFormatException e){
                    ulota.setText("!!Enter Valid rate!!");
                    t3r.setText("");
                    ra=-1;
                    continue;
                }
                catch (Exception e) {
                    ulota.setText("!!Enter all fields!!");
                    t3.setText(""); t3dn.setText(""); t3r.setText("");
                    ra=-1;
                    continue;
                }
                remove(l1); remove(l2); remove(l3); remove(l4);
                t3.setText(""); t3dn.setText(""); t3r.setText("");
                cho1.setSelectedIndex(-1);
                remove(t3); remove(cho1); remove(t3dn); remove(t3r);
                remove(bx3);
                validate();
                repaint(); 
                break;                                                  
            }
            if(doit == -1){ 
                doit=0; 
                remove(l1); remove(l2); remove(l3); remove(l4);
                t3.setText(""); t3dn.setText(""); t3r.setText("");
                cho1.setSelectedIndex(-1);
                remove(t3); remove(cho1); remove(t3dn); remove(t3r);
                remove(bx3);
                validate();
                repaint(); 
                return -1;                                                 
            }
            try{ Thread.sleep(80); }
            catch(Exception e){}
        }
        return Integer.parseInt(shi); 
    }

    public int getsehit(){
        state=6;
        JLabel l9=new JLabel("Enter Hotel-id to see Menu:");
        Font f2=new Font("Segoe UI",Font.PLAIN,22);
        l9.setFont(f2);
        l9.setBounds(50,120,600,22);
        add(l9);
        cho6.setBounds(50,173,500,22);
        add(t6); add(cho6); add(bx6);
        String st=t6.getText();
        while(st.equals("")){
            if(doit==-1){
                doit=0;
                cho6.setSelectedIndex(-1);
                remove(l9); remove(t6); remove(cho6); remove(bx6);
                revalidate();
                repaint();
                return -1;
            }
            try{ Thread.sleep(100); }
            catch(InterruptedException e){}
            st=t6.getText();
        }
        t6.setText("");
        cho6.setSelectedIndex(-1);
        remove(l9); remove(t6); remove(cho6); remove(bx6);
        revalidate();
        repaint();
        int res=Integer.parseInt(st);
        return res;
    }
}