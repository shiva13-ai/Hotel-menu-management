package security;
import java.util.*;
import customer.*;
import hotel.*;
import exception.*;
import java.io.*;
import java.security.*;
import gui.*;

public class HsignIn implements Runnable{
    int index;
    int hotelId = -1;          // initialize to -1 so the signin loop works correctly
    int sucp=1,suci=1,succ=0;
    Hotel harr[];
    private String pass,st;
    Hsigningui hsig;
    boolean backpress=false;

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
    public HsignIn(Hotel harr[]){
        this.harr=harr;
    }
    private int findHotelIndex(int id, Hotel harr[]) {
        for (int i = 0; i < harr.length; i++) {
            if (harr[i] != null && harr[i].hotelid == id) {
                return i;
            }
        }
        return -1;
    }
    private String getStoredHash(int id) throws IOException, IncorrectPasswordException {
        try (BufferedReader br = new BufferedReader(new FileReader("hotel_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(":");
                if (parts.length == 2) {
                    try {
                        if (Integer.parseInt(parts[0]) == id) {
                            return parts[1];
                        }
                    }
                    catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
            throw new IncorrectPasswordException("\n!!!hotel id incorrect!!!\n");
        }
        catch (FileNotFoundException e) {
            throw new IncorrectPasswordException("\n!!!hotel id incorrect!!!\n");
        }
    }
    private void updatePasswordFile(int id, String newHashedPass) throws IOException {
        File inputFile = new File("hotel_credentials.txt");
        File tempFile = new File("hotel_credentials_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String parts[] = currentLine.split(":");
                if(parts.length == 2) {
                    if(Integer.parseInt(parts[0]) == id) {
                        writer.write(id + ":" + newHashedPass + System.getProperty("line.separator"));
                        continue;
                    }
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            throw new IOException("Could not update credentials file.");
        }
    }

    public synchronized void run() {
        Scanner scan=new Scanner(System.in);
        hsig=new Hsigningui();
        hsig.setVisible(true);
        while (succ == 0) {
            while (pass == null || hotelId == -1) {
                if (hsig.backpressed()) {
                    hsig.dispose();
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                pass = hsig.getpass();
                try {
                    hotelId = hsig.getuserid();
                } catch (NumberFormatException e) {
                    hotelId = -1;
                }
            }
            try{
                pass=hsig.getpass();
                String storedHash = getStoredHash(hotelId);
                String inputHash = getHash(pass);
                if (storedHash == null) {
                    throw new IncorrectPasswordException("\n---enter valid password/user-id---\n");
                }
                if (!storedHash.equals(inputHash) && !storedHash.equals(pass.trim())) {
                    IncorrectPasswordException ie = new IncorrectPasswordException("\n---enter valid password/user-id---\n");
                    sucp = 0;
                    hsig.hota.setText(ie.toString());
                }
                else {
                    sucp = 1;
                }
                index = findHotelIndex(hotelId, harr);
                if (index == -1 || harr[index] == null) {
                    NullPointerException ne = new NullPointerException();
                    suci = 0;
                    hsig.hota.setText(ne.toString());
                } 
                else {
                    suci = 1;
                }
                if (sucp == 1 && suci == 1) {
                    succ = 1;
                } 
                else {
                    pass = null;
                    hotelId = -1;
                }
            }
            catch (Exception e) {
                hsig.hota.setText(e.toString());
                pass = null;
                hotelId = -1;
            }
            if (hsig.backpressed()) {
                hsig.dispose();
                backpress=true;
                return;
            }
        }
        if(backpress){  return;  }
        hsig.dispose();
        Hlobby hlo=new Hlobby();
        while(true){
            try{
                hlo.setVisible(true);
                hlo.rei=0;
                int choice=hlo.result();
                while(choice==0){
                    try{ Thread.sleep(100); }
                    catch(InterruptedException e){}
                    choice=hlo.result();
                }
                switch(choice){
                    case 1:
                        hlo.hlota.setText("\nId:"+(harr[index].hotelid)+"\n");
                        break;
                    case 2:
                        pass=hlo.getchapass();
                        hlo.dispose();
                        if(pass!=null){
                            String newHashedPass = getHash(pass);
                            updatePasswordFile(harr[index].hotelid, newHashedPass);
                            hlo.hlota.setText("\nPassword changed successfully!\n");
                        }
                        break;
                    case 3:
                        harr[index].displayMenu(hlo);
                        break;
                    case 4:
                        hlo.inseddac();
                        harr[index].inDish(hlo.d,hlo.ty,hlo.c);
                        break;
                    case 5:
                        st=hlo.getdina();
                        if(st!=null){
                            harr[index].deDish(st,hlo.hlota);
                        }
                        break;
                    case 6:
                        hlo.setPrize();
                        harr[index].updatePrize(hlo,hlo.d,hlo.c);
                        break;
                    case 7:
                        hlo.dispose();
                        return;
                }
            }
            catch(IOException e){ System.out.println(e); }
            catch(InputMismatchException e){
                hsig.hota.setText("\n---Enter valid Choice---");
            }
            catch(NullPointerException e){
                hsig.hota.setText("\n!!!incorrect password/incorrect hotel id given by you!!!\n");
            }
            catch(IndexOutOfBoundsException e){
                hsig.hota.setText("\n!!!hotel id incorrect!!!\n");
            }
        }
    }
}