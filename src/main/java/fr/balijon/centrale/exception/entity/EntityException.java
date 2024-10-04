package fr.balijon.centrale.exception.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class EntityException extends RuntimeException {

    private Object entity = null;

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Object entity) {
        super(message);
        this.entity = entity;
    }

    @Override
    public String toString() {
        return super.toString() + " Entity Details: " + entity;
    }
}
