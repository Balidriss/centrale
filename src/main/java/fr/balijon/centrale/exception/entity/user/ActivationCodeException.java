package fr.balijon.centrale.exception.entity.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ActivationCodeException extends RuntimeException {

    public ActivationCodeException(String message) {
        super(message);
    }
}
