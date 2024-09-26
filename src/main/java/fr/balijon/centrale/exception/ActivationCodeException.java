package fr.balijon.centrale.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ActivationCodeException extends RuntimeException {

    public ActivationCodeException(String message) {
        super(message);
    }
}
