package hotel;
import java.util.*;
import java.io.*;
import gui.*;
import java.awt.*;
import javax.swing.*;   

public class Hotel {
    public Hnode head,vhead,nvhead;
    public int hotelid,Ncount=0;
    private String d,ty,password;
    private float r,c;
    Scanner scan=new Scanner(System.in);
    
    
    private String getMenuFileName() {
        return "menu_" + hotelid + ".txt";
    }

    public Hotel(){
        hotelid=100;
        password=null;
        loadMenu(); 
    }
    public Hotel(int hi,String pass){
        hotelid=hi;
        password=pass;
        loadMenu(); 
    }
    public String getPass(){ return password; }
    public void changePass(String pass){
        password=pass;
        
    }

    
    private void saveMenu() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getMenuFileName()))) {
            Hnode ptr = head;
            while (ptr != null) {
                
                writer.println(ptr.dish + "|" + ptr.type + "|" + ptr.cost + "|" + ptr.rating);
                ptr = ptr.next;
            }
        } catch (IOException e) {
            System.out.println("Error saving menu for hotel " + hotelid + ": " + e.getMessage());
        }
    }

    
    private void loadMenu() {
        head = vhead = nvhead = null;
        Ncount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(getMenuFileName()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String dish = parts[0];
                    String type = parts[1];
                    float cost = Float.parseFloat(parts[2]);
                    float rating = Float.parseFloat(parts[3]);
                    Hnode new_node = new Hnode(dish, type, cost);
                    new_node.rating = rating; 
                    if (head == null) {
                        head = new_node;                              } 
                    else {
                        Hnode ptr = head;
                        while (ptr.next != null) { ptr = ptr.next; }
                        ptr.next = new_node;                          }
                    Ncount++;
                    Hnode cnew_node = new Hnode(dish, type, cost);
                    cnew_node.rating = rating;
                    if (type.equals("v")) {
                        if (vhead == null) {
                            vhead = cnew_node;
                        } else {
                            Hnode ptr = vhead;
                            while (ptr.next != null) { ptr = ptr.next; }
                            ptr.next = cnew_node;
                        }
                    } else if (type.equals("nv")) {
                        if (nvhead == null) {
                            nvhead = cnew_node;
                        } else {
                            Hnode ptr = nvhead;
                            while (ptr.next != null) { ptr = ptr.next; }
                            ptr.next = cnew_node;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading menu for hotel " + hotelid + ": " + e.getMessage());
        }
    }

    public void inDish(String d,String ty,float c){
        this.d=d;
        this.ty=ty;
        this.c=c;
        Hnode new_node=new Hnode(d,ty,c);
        if(head==null){
            head=new_node;        }
        else if(head!=null){
            Hnode ptr=head;
            while(ptr.next!=null){ ptr=ptr.next;  }
            ptr.next=new_node;                           }
        Ncount++;
        Hnode cnew_node=new Hnode(d,ty,c);
        if(ty.equals("v")){
            if(vhead==null){
                vhead=cnew_node;        }
            else if(vhead!=null){
                Hnode ptr=vhead;
                while(ptr.next!=null){ ptr=ptr.next;  }
                ptr.next=cnew_node;                           }
        }
        if(ty.equals("nv")){
            if(nvhead==null){
                nvhead=cnew_node;        }
            else if(nvhead!=null){
                Hnode ptr=nvhead;
                while(ptr.next!=null){ ptr=ptr.next;  }
                ptr.next=cnew_node;                           }
        }
        else if(!ty.equals("v") && !ty.equals("nv")){
            System.out.println("---invalid choice---");     
        }
        saveMenu(); 
    }

    public void deDish(String name,JTextArea ta) {
    if (head == null) {
        ta.setText("\n!! Menu is Empty !!\n");
        return;
    }

    Hnode prev = null, curr = head;
    boolean found = false;

    
    while (curr != null) {
        if (curr.dish.trim().equalsIgnoreCase(name)) {
            found = true;
            if (prev == null)
                head = curr.next;  
            else
                prev.next = curr.next;
            Ncount--;
            break;
        }
        prev = curr;
        curr = curr.next;
    }

    if (!found) {
        ta.setText("\nDish not found!\n");
        return;
    }

    
    if (curr.type.trim().equalsIgnoreCase("v")) {
        vhead = deleteFromList(vhead, name);
    } else if (curr.type.trim().equalsIgnoreCase("nv")) {
        nvhead = deleteFromList(nvhead, name);
    }

    ta.setText("\nDish deleted successfully!\n");
    saveMenu(); 
}


private Hnode deleteFromList(Hnode headNode, String name) {
    Hnode prev = null, curr = headNode;
    while (curr != null) {
        if (curr.dish.trim().equalsIgnoreCase(name)) {
            if (prev == null)
                return curr.next;
            else
                prev.next = curr.next;
            break;
        }
        prev = curr;
        curr = curr.next;
    }
    return headNode;
    }
    public void updatePrize(Hlobby hlo,String d,float c){
        if(head==null){ 
            hlo.hlota.setText("???Menu is Empty???\n"); }
        this.d=d;
        this.c=c;
        if(d.length()==0 || c==-1){ return;  }
        try{
        Hnode ptr1=head;
        while(!ptr1.dish.equalsIgnoreCase(d)){  ptr1=ptr1.next; }
        ptr1.cost=c;
        hlo.hlota.setText("====Updated Successfully====");
        if(ptr1.type.equals("v")){
            Hnode ptr0=vhead;
            while(!ptr0.dish.equals(d)){  ptr0=ptr0.next; }
            ptr0.cost=c;
        }
        if(ptr1.type.equalsIgnoreCase("nv")){
            Hnode ptr0=nvhead;
            while(!ptr0.dish.equals(d)){  ptr0=ptr0.next; }
            ptr0.cost=c;
        }                                                           }
        catch(NullPointerException e){
            hlo.hlota.setText("\nDish not found!\n");              }
        saveMenu(); 
    }
    public void rate(Ulobby ulo,String food,float r){
        this.r=r; 
        Hnode ptr1=head;
        try{
        while(!ptr1.dish.equals(food)){  ptr1=ptr1.next; }
        ptr1.rating=(ptr1.rating + r)/2f;
        if(ptr1.type.equals("v")){
            ptr1=vhead;
            while(!ptr1.dish.equals(food)){  ptr1=ptr1.next; }
            ptr1.rating=(ptr1.rating + r)/2f;
        }
        if(ptr1.type.equals("nv")){
            ptr1=nvhead;
            while(!ptr1.dish.equals(food)){  ptr1=ptr1.next; }
            ptr1.rating=(ptr1.rating + r)/2f;
        }                                                                }
        catch(NullPointerException e){
            ulo.ulota.setText("\nthe entered dish doesn't exit.\n");    }
        saveMenu();
    }
    public void rateMergeSort() {
        if (head == null || head.next == null) {
            return; 
        }
        head = rmergeSort(head);
    }
    
    private Hnode rmergeSort(Hnode h) {
        if (h == null || h.next == null) {
            return h;
        }
        Hnode middle = getMiddle(h);
        Hnode nextOfMiddle = middle.next;  
        middle.next = null;
        Hnode left = rmergeSort(h);
        Hnode right = rmergeSort(nextOfMiddle);
        return rateMerge(left, right);
    }
    private Hnode rateMerge(Hnode left, Hnode right) {
        Hnode result = null;
        if (left == null) return right;
        if (right == null) return left;
        if (left.rating >= right.rating) {
            result = left;
            result.next = rateMerge(left.next, right);
        } else {
            result = right;
            result.next = rateMerge(left, right.next);
        }  
        return result;
    }
    public void costMergeSort() {
    if (head == null || head.next == null) {
        return;
    }
    head = mergeSortByCost(head);
    }
    private Hnode mergeSortByCost(Hnode h) {
        if (h == null || h.next == null) {
            return h;
        }
    Hnode middle = getMiddle(h);
    Hnode nextOfMiddle = middle.next;
    middle.next = null;
    Hnode left = mergeSortByCost(h);
    Hnode right = mergeSortByCost(nextOfMiddle);
    return costMerge(left, right);
    }
    private Hnode costMerge(Hnode left, Hnode right) {
        Hnode result = null;
    if (left == null) {
        return right;
    }
    if (right == null) {
        return left;
    }
    if (left.cost <= right.cost) {
        result = left;
        result.next = costMerge(left.next, right);
    } else {
        result = right;
        result.next = costMerge(left, right.next);
    }
    return result;
    }
    private Hnode getMiddle(Hnode h) {
        if (h == null) return h;  
        Hnode slow = h;
        Hnode fast = h; 
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public void typeMenu(Ulobby ulo,String ty){
    int choice;
    while(true){
        TypeMenuWindow tmw=new TypeMenuWindow(null);
        tmw.setVisible(true);
        choice=tmw.getChoice();
        switch(choice){
            case 1:
                if(ty.equalsIgnoreCase("v")){
                    displayMenu(ulo,vhead);     }
                else if(ty.equalsIgnoreCase("nv")){
                    displayMenu(ulo,nvhead);    }
                break;
            case 2:
                if(ty.equals("v")){
                    if (vhead == null || vhead.next == null) { return;   }
                    vhead=rmergeSort(vhead);          }
                else if(ty.equals("nv")){
                    if (nvhead == null || nvhead.next == null) { return;   }
                    nvhead=rmergeSort(nvhead);        }
                break;
            case 3:
                if(ty.equals("v")){
                    if (vhead == null || vhead.next == null) { return;   }
                    vhead=mergeSortByCost(vhead);
                }
                else if(ty.equals("nv")){
                    if (nvhead == null || nvhead.next == null) { return;   }
                    nvhead=mergeSortByCost(nvhead);
                }
                break;
            case 4:
                return;
        }
    }
    }
    public void displayMenu(){
        if(head==null){ 
            return;  }
        Hnode ptr=head;
        while(ptr!=null){
            System.out.println("\n____________________________________");
            System.out.println(String.format("\nDISH:%s\nCOST:$%.2f\nRATINGS:%.2f",ptr.dish,ptr.cost,ptr.rating));
            System.out.println("\n____________________________________");
            ptr=ptr.next;
        }
    }
    public void displayMenu(Ulobby ulo){
        if(head==null){ 
            ulo.ulota.setText("???Menu is Empty???");
            return;  }
        ulo.ulota.setText("\n..........MENU.........:-\n");
        Hnode ptr=head;
        while(ptr!=null){
            ulo.ulota.append("\n____________________________________");
            ulo.ulota.append(String.format("\nDISH:%s\nCOST:$%.2f\nRATINGS:%.2f",ptr.dish,ptr.cost,ptr.rating));
            ulo.ulota.append("\n____________________________________");
            ptr=ptr.next;
        }
    }
    public void displayMenu(Hlobby hlo){
        if(head==null){ 
            hlo.hlota.setText("???Menu is Empty???");
            return;  }
        hlo.hlota.setText("\n..........MENU.........:-\n");
        Hnode ptr=head;
        while(ptr!=null){
            hlo.hlota.append("\n____________________________________");
            hlo.hlota.append(String.format("\nDISH:%s\nCOST:$%.2f\nRATINGS:%.2f",ptr.dish,ptr.cost,ptr.rating));
            hlo.hlota.append("\n____________________________________");
            ptr=ptr.next;
        }
    }
    public void displayMenu(Ulobby ulo,Hnode nohe){
        if(nohe==null){ 
            ulo.ulota.setText("???Menu is Empty???");
            return;  }
        ulo.ulota.setText("\n..........MENU.........:-\n");
        Hnode ptr=nohe;
        while(ptr!=null){
            ulo.ulota.append("____________________________________");
            ulo.ulota.append(String.format("\nDISH:%s\nCOST:$%.2f\nRATINGS:%.2f\n",ptr.dish, ptr.cost, ptr.rating));
            ulo.ulota.append("____________________________________");
            ptr=ptr.next;
        }
    }
}