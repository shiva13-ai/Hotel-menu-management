package security;
import java.util.*;
import hotel.*;
import java.io.*;
import java.security.*;
import gui.*;

public class HsignUp implements Runnable{
    Hotel harr[];
    int choice,hocount;
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
        } catch (Exception e) {
            return null; 
        }
    }
    private static int generateRandomId(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
    public HsignUp(Hotel harr[],int hocount){
        this.harr=harr;
        this.hocount=hocount;
    }
    public synchronized void run(){
        Hsignupgui husi=new Hsignupgui();
        husi.setVisible(true);
        pass=husi.getpass();
        while(pass==null){
            if(husi.backpress()){
                husi.dispose();
                return;                   }
            husi.tahsup.setText("!!!Password should have atleast 8 Characters!!!");
            try{ Thread.sleep(100);   }
            catch(InterruptedException e){}
            pass=husi.getpass();
        }
        int hotelid;
        String hashedPass = getHash(pass);
        do {  hotelid = generateRandomId(1000, 99999);    }
        while (isHotelIdUsed(hotelid, harr, hocount));
        try (FileWriter fw = new FileWriter("hotel_credentials.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(hotelid + ":" + hashedPass);
            
        } catch (IOException e) {
            husi.tahsup.setText("Error saving hotel credentials: " + e.getMessage());
            return;
        }
        harr[hocount]=new Hotel(hotelid,pass);
 //     System.out.println("\n!!!Created Succesfully!!!\nHotel id="
 //     +harr[hocount].hotelid+"\n");
        husi.tahsup.setText("");
        husi.tahsup.append("--Hotel Account created Succesfully--\n");
        husi.tahsup.append("\nHotel id="+harr[hocount].hotelid+"\n");
        husi.tahsup.append("Hotel password="+pass);
        while(!husi.backpress()){
           try{ Thread.sleep(100);  }
           catch(InterruptedException e){}
        }
        husi.dispose();
    }
    private boolean isHotelIdUsed(int id, Hotel harr[], int currentCount) {
        for (int i = 0; i < currentCount; i++) {
            if (harr[i] != null && harr[i].hotelid == id) {
                return true;                              }
        }
        return false;
    }
}