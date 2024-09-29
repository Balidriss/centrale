package fr.balijon.centrale.advisor;

import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.exception.entity.user.ActivationCodeException;
import fr.balijon.centrale.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ActivationCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response activationCodeHandler(RuntimeException e) {
        if (e instanceof ActivationCodeException) {
            List<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return new Response(errors,HttpStatus.BAD_REQUEST.value(),null);
        }
        return new Response();
    }

    @ResponseBody
    @ExceptionHandler(EntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response entityHandler(RuntimeException e) {
        if (e instanceof EntityException) {
            List<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return new Response(errors,HttpStatus.BAD_REQUEST.value(),null);
        }
        return new Response();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response validationHandler(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<String>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });

        return new Response(errors,HttpStatus.BAD_REQUEST.value(),null);

    }

}
