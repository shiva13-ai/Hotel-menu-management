package hotel;

public class Hnode{
    String dish;
    String type;
    float rating;
    float cost;
    Hnode next;
    public Hnode(){
        dish="Salad";
        type="v";
        rating=(float)0.0;
        cost=(float)0.0;
    }
    public Hnode(String d,String ty,float c){
        dish=d;
        type=ty;
        rating=(float)0.0;
        cost=c;
        next=null;
    }
}                       