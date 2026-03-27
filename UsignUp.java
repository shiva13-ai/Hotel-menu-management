package security;
import java.util.*;
import customer.*;
import java.io.*;
import java.security.*;
import gui.*;

public class UsignUp implements Runnable{
    User uarr[];
    int choice,uscount;
    private String pass;
    
    private static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                sb.append(String.format("%02x", hash[i]));
            }
            return sb.toString();
        } 
        catch (Exception e) {
            return null; 
        }
    }
    private static int generateRandomId(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
    public UsignUp(User uarr[],int uscount){
        this.uarr=uarr;
        this.uscount=uscount;
    }
    public void run(){
        Usignupgui uugi=new Usignupgui();
        uugi.setVisible(true);
        pass=uugi.getpass();
        while(pass==null){
            if(uugi.backpress()){
                uugi.dispose();
                return;
            }
            uugi.tausup.setText("!!!Password should have atleast 8 Characters!!!");
            try{ Thread.sleep(100); }
            catch(InterruptedException e){}
            pass=uugi.getpass();
        }
        int userid;
        String hashedPass = getHash(pass);

        do {  userid = generateRandomId(50000, 999999);   } 
        while (isUserIdUsed(userid, uarr, uscount));
        try (FileWriter fw = new FileWriter("user_credentials.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(userid + ":" + hashedPass);   
        } 
        catch (IOException e) {
            uugi.tausup.setText("Error saving user credentials: " + e.getMessage());
            return;
        }
        uarr[uscount]=new User(userid,pass);
        uugi.tausup.setText("");
        uugi.tausup.append("!!!Created Succesfully!!!\n");
        uugi.tausup.append("\nUser id="+uarr[uscount].userid+"\n");
        uugi.tausup.append("Password="+pass);
        while(!uugi.backpress()){
           try{ Thread.sleep(100);  }
           catch(InterruptedException e){}
        }
        uugi.dispose();
    }
    private boolean isUserIdUsed(int id, User uarr[], int currentCount) {
        for (int i = 0; i < currentCount; i++) {
            if (uarr[i] != null && uarr[i].userid == id) {
                return true;                                  }
        }
        return false;
    }
}