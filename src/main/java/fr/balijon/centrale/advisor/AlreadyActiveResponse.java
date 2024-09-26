package fr.balijon.centrale.advisor;

import fr.balijon.centrale.exception.ActivationCodeException;
import fr.balijon.centrale.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlreadyActiveResponse {

    @ResponseBody
    @ExceptionHandler(ActivationCodeException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    Response codeActivationExistsHandler(ActivationCodeException exception) {
        Response response = new Response();
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        response.setField(exception.getMessage());
        return response;
    }
}
