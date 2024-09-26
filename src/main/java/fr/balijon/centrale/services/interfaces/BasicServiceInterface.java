package fr.balijon.centrale.services.interfaces;

public interface BasicServiceInterface<T, L, C> {

    T create(C o);

    Boolean delete(L id);
}
