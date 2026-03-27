package exception;

public class IncorrectPasswordException extends Exception{
    
    
    public IncorrectPasswordException(String message) {
        super(message);
    }
    
    
    public IncorrectPasswordException() {
        super("\n---enter valid password/user-id---\n");
    }

    public String toString(){
        
        
        return "\n---enter valid password/user-id---\n"; 
    }
}