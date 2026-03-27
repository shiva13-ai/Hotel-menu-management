package test;
import java.util.*;
import security.*;
import hotel.*;
import customer.*;
import java.io.*;
import gui.*;

class TestMenu{
    private static void loadExistingEntities(Hotel harr[], User uarr[], int[] counts) {
        int hocount = 0;
        int uscount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("hotel_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(":");
                if (parts.length == 2) {
                    try {
                        int hotelid = Integer.parseInt(parts[0]);
                        if (hocount < harr.length) {
                            harr[hocount++] = new Hotel(hotelid, "DUMMY_PASS");   }
                    } 
                    catch (NumberFormatException e) {
                        continue;
                    }                                   }
            }
        } 
        catch (IOException e) {}
        try (BufferedReader br = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(":");
                if (parts.length == 2) {
                    try {
                        int userid = Integer.parseInt(parts[0]);
                        if (uscount < uarr.length) {
                            uarr[uscount++] = new User(userid, "DUMMY_PASS");   }
                    } 
                    catch (NumberFormatException e) {
                        continue;
                    }                                      }
            }
        } 
        catch (IOException e) {}
        counts[0] = hocount;
        counts[1] = uscount;
    }
    public static void main(String args[]){
        int choice,choice1;
        int hocount = 0, uscount = 0; 
        Hotel harr[]=new Hotel[100000];
        User uarr[]=new User[1000000];
        Scanner scan=new Scanner(System.in);
        int[] counts = {hocount, uscount};
        loadExistingEntities(harr, uarr, counts);
        hocount = counts[0];
        uscount = counts[1];
        while(true){
            try{
//          System.out.print("\n1.New\n2.Existing\n3.exit\nEnter choice:");
            Startingui sagi=new Startingui();
            sagi.setVisible(true);
            while(sagi.swre()==0){ 
                try{ Thread.sleep(100);   }
                catch(InterruptedException e){}
            }
            switch(sagi.swre()){
                case 1:
                    sagi.dispose();
                    UserTypegui usgi =new UserTypegui();
//                  Sytem.out.println(1.hotel management\n2.customer\n3.exit);
                    usgi.setVisible(true);
                    while(usgi.utre()==0){ 
                        try{ Thread.sleep(100);   }
                        catch(InterruptedException e){}
                    }
                    if(usgi.utre()==3){ 
                        usgi.dispose();
                        break;                     }
                    if(usgi.utre()==1){
                        usgi.dispose();
                        HsignUp hsu=new HsignUp(harr,hocount); 
                        Thread th=new Thread(hsu,"hotel_sign-up");
                        th.start();
                        try {
                            th.join();                                            }
                        catch (InterruptedException e) {  e.printStackTrace();    }
                        hocount++;                                                  }
                    else if(usgi.utre()==2){
                        usgi.dispose();
                        UsignUp usu=new UsignUp(uarr,uscount);
                        Thread th=new Thread(usu,"user_sign-up");
                        th.start();
                        try {
                            th.join();                                            }
                        catch (InterruptedException e) {  e.printStackTrace();    }
                        uscount++;                                                  }
                    break;
                case 2:
                    sagi.dispose();
                    usgi =new UserTypegui();
//                  System.out.print("\n1.Hotel Management\n2.Customer\nEnter Choice:");
                    usgi.setVisible(true);
                    while(usgi.utre()==0){ 
                        try{ Thread.sleep(100);   }
                        catch(InterruptedException e){}
                    }
                    if(usgi.utre()==3){     
                        usgi.dispose();
                        break;                     }
                    if(usgi.utre()==1){
                        usgi.dispose();
                        HsignIn hsi=new HsignIn(harr);  
                        Thread th=new Thread(hsi,"hotel_sign-in");
                        th.start();
                        try {
                            th.join();                                            }
                        catch (InterruptedException e) {  e.printStackTrace();    } }
                    else if(usgi.utre()==2){
                        usgi.dispose();
                        UsignIn usi=new UsignIn(uarr,harr);
                        Thread th=new Thread(usi,"user_sign-in");
                        th.start();
                        try {
                            th.join();                                            }
                        catch (InterruptedException e) {  e.printStackTrace();    } }
                    break;
                case 3:
                    System.exit(0);
                    break;
            }  
            } 
            catch(InputMismatchException e){
                System.out.println("\n---Enter valid choice---");
                scan.nextLine();
            }                            
        }
    }
}