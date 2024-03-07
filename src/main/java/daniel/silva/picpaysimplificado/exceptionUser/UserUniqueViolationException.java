package daniel.silva.picpaysimplificado.exceptionUser;

public class UserUniqueViolationException extends RuntimeException{

    public UserUniqueViolationException(String message){
        super(message);
    }
}
