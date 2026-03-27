package security;
import java.util.*;
import customer.*;
import hotel.*;
import exception.*;
import java.io.*;
import java.security.*;
import gui.*;

public class UsignIn implements Runnable {
    int index;
    int userId = -1;
    int succ = 0, sucp = 1, suci = 1;
    User uarr[];
    Hotel harr[];
    private String pass = null;
    Thread t;
    Usigningui usig;
    boolean backpress=false;
    Scanner scan = new Scanner(System.in);

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

    public UsignIn(User uarr[], Hotel harr[]) {
        this.uarr = uarr;
        this.harr = harr;
    }

    private int findUserIndex(int id, User uarr[]) {
        for (int i = 0; i < uarr.length; i++) {
            if (uarr[i] != null && uarr[i].userid == id) {
                return i;
            }
        }
        return -1;
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
        try (BufferedReader br = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(":");
                if (parts.length == 2) {
                    try {
                        if (Integer.parseInt(parts[0]) == id) {
                            return parts[1].trim();
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
            throw new IncorrectPasswordException("\n!!!incorrect password/incorrect user id given by you!!!\n");
        } catch (FileNotFoundException e) {
            throw new IncorrectPasswordException("\n!!!incorrect password/incorrect user id given by you!!!\n");
        }
    }

    private void updatePasswordFile(int id, String newHashedPass) throws IOException {
        File inputFile = new File("user_credentials.txt");
        File tempFile = new File("user_credentials_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String parts[] = currentLine.split(":");
                if (parts.length == 2) {
                    if (Integer.parseInt(parts[0]) == id) {
                        writer.write(id + ":" + newHashedPass + System.lineSeparator());
                        continue;
                    }
                }
                writer.write(currentLine + System.lineSeparator());
            }
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            throw new IOException("Could not update credentials file.");
        }
    }

    public void run() {
        usig = new Usigningui();
        usig.setVisible(true);

        while (succ == 0) {
            while (pass == null || userId == -1) {
                if (usig.backpressed()) {
                    usig.dispose();
                    return;
                }
                try {
                    Thread.sleep(100);
                } 
                catch (InterruptedException e) {}
                pass = usig.getpass();
                try {
                    userId = usig.getuserid();
                } 
                catch (NumberFormatException e) {
                    userId = -1;
                    usig.tusa.setText("---Enter Valid Password---");
                }
            }
            try {
                String storedHash = getStoredHash(userId);
                String inputHash = getHash(pass.trim());
                if (!storedHash.equals(inputHash) && !storedHash.equals(pass.trim())) {
                    IncorrectPasswordException ie = new IncorrectPasswordException("\n---enter valid password/user-id---\n");
                    sucp = 0;
                    usig.tusa.setText(ie.toString());     } 
                else {
                    sucp = 1;       }
                index = findUserIndex(userId, uarr);
                if (index == -1 || uarr[index] == null) {
                    NullPointerException ne = new NullPointerException();
                    suci = 0;
                    usig.tusa.setText(ne.toString());   } 
                else {
                    suci = 1;                     }
                if (sucp == 1 && suci == 1) {
                    succ = 1;                     } 
                else {
                    pass = null;
                    userId = -1;                  }
            } 
            catch (Exception e) {
                usig.tusa.setText(e.toString());
                pass = null;
                userId = -1;
            }
            if (usig.backpressed()) {
                usig.dispose();
                backpress=true;
                return;                  }
        }
        if(backpress){  return;  }
        usig.dispose();
        Ulobby ulo=new Ulobby(harr);
        while (true) {
            int choice;
            try {
                ulo.setVisible(true);
                ulo.rei=0;
//                System.out.print("\n1.show details\n2.change password\n3.show Menu\n4.specific type\n5.sort by rate\n6.sort by price\n7.rate\n8.exit\nEnter choice:");
                choice = ulo.rei;
                while(choice==0){
                    try{ Thread.sleep(100); }
                    catch(InterruptedException e){}
                    choice=ulo.rei;
                }
                int hi;
                int hotelIndex;
                switch (choice) {
                    case 1:
                        ulo.ulota.setText("\nId:" + uarr[index].userid);
                        break;
                    case 2:
                        pass =ulo.getchapuss();
                        if(pass!=null){
                        String newHashedPass = getHash(pass);
                        updatePasswordFile(uarr[index].userid, newHashedPass);
                        ulo.ulota.setText("\nPassword changed successfully!\n");    
                        }
                        break;
                    case 3:
                        hi = ulo.getsehi();
                        if(hi!=-1){
                        hotelIndex = findHotelIndex(hi, harr);
                        if (hotelIndex == -1 || harr[hotelIndex] == null)
                            throw new IndexOutOfBoundsException();
                        harr[hotelIndex].displayMenu(ulo);
                        }
                        break;
                    case 4:
                        hi=ulo.getsehit();
                        if(hi!=-1){
                        hotelIndex = findHotelIndex(hi, harr);
                        if (hotelIndex == -1 || harr[hotelIndex] == null)
                            throw new IndexOutOfBoundsException();
                        harr[hotelIndex].typeMenu(ulo,ulo.ty);                      }
                        break;
                    case 5:
                        hi = ulo.getsehisr();
                        if(hi!=-1){
                        hotelIndex = findHotelIndex(hi, harr);
                        if (hotelIndex == -1 || harr[hotelIndex] == null)
                            throw new IndexOutOfBoundsException();
                        harr[hotelIndex].rateMergeSort();
                        harr[hotelIndex].displayMenu(ulo);                       }
                        break;
                    case 6:
                        hi = ulo.getsehisp();
                        if(hi!=-1){
                        hotelIndex = findHotelIndex(hi, harr);
                        if (hotelIndex == -1 || harr[hotelIndex] == null)
                            throw new IndexOutOfBoundsException();
                        harr[hotelIndex].costMergeSort();
                        harr[hotelIndex].displayMenu(ulo);                           }
                        break;
                    case 7:
                        hi = ulo.getsehir();
                        if(hi!=-1){
                        hotelIndex = findHotelIndex(hi, harr);
                        if (hotelIndex == -1 || harr[hotelIndex] == null)
                            throw new IndexOutOfBoundsException();
                        harr[hotelIndex].displayMenu(ulo);
                        harr[hotelIndex].rate(ulo,ulo.dn,ulo.ra);
                        ulo.ulota.setText("++++ Rating Successfull ++++");
                        ulo.dn=null;  ulo.ra=-1;                             }
                        break;
                    case 8:
                        ulo.dispose();
                        return;
                }
            } catch (InputMismatchException e) {
                usig.tusa.setText("\n---Enter valid Choice---");
                scan.nextLine();
            } catch (NullPointerException e) {
                usig.tusa.setText("\n!!!incorrect password/incorrect user id given by you!!!\n");
            } catch (IndexOutOfBoundsException e) {
                ulo.ulota.setText("\n!!!hotel not Found!!!\n");
                usig.tusa.setText("\n!!!hotel id incorrect!!!\n");
            } catch (Exception e) {
                usig.tusa.setText(e.toString());
            }
        }
    }
}