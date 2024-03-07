package daniel.silva.picpaysimplificado.exception;

import daniel.silva.picpaysimplificado.exceptionUser.UserNotFoundException;
import daniel.silva.picpaysimplificado.exceptionUser.UserUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroMessage> errorMensageMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                    HttpServletRequest request,
                                                                                    BindingResult bindingResult){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s)", bindingResult));
    }

    @ExceptionHandler(UserUniqueViolationException.class)
    public ResponseEntity<ErroMessage> UserUniqueViolationException(UserUniqueViolationException exception,
                                                                    HttpServletRequest httpServletRequest){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(httpServletRequest, HttpStatus.CONFLICT, exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErroMessage> errorMensageUsuarioNotFoundException(RuntimeException exception,
                                                                             HttpServletRequest request){
        log.error("Api erro - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErroMessage(request, HttpStatus.NOT_FOUND, "User não encontrado!"));
    }

}
