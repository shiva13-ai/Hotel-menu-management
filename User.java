package customer;
import java.util.*;

public class User{
    public int userid;
    private String password;
    public User(){
        userid=50000;
        password=null;
    }
    public User(int ui,String pass){
        userid=ui;
        password=pass;
    }
    public void changePass(String pass){
        password=pass;
    }
    public String getPass(){ return password;  }
}