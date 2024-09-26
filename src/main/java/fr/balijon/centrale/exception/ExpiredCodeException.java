package fr.balijon.centrale.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class ExpiredCodeException extends RuntimeException  {

    public ExpiredCodeException(String message) {
        super(message);
    }
}
