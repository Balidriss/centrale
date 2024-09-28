package fr.balijon.centrale.advisor;

import fr.balijon.centrale.exception.entity.EntityException;
import fr.balijon.centrale.exception.entity.user.ActivationCodeException;
import fr.balijon.centrale.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ActivationCodeException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Response activationCodeHandler(RuntimeException e) {
        if (e instanceof ActivationCodeException) {
            return new Response(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
        }
        return new Response();
    }

    @ResponseBody
    @ExceptionHandler(EntityException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Response entityHandler(RuntimeException e) {
        if (e instanceof EntityException) {
            return new Response(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage(),((EntityException) e).getEntity());
        }
        return new Response();
    }

}
