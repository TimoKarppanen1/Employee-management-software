package net.javaguides.SpringBootAngular.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Jos HttpStatus palauttaa 404 - NOT FOUND, suoritetaan alla oleva metodi. */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersioUID = 1L;
    public ResourceNotFoundException(String message){
        super(message);
    }

}
