package ng.com.createsoftware.freestylebe.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
