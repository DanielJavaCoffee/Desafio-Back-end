package daniel.silva.picpaysimplificado.exceptionUser;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }
}
