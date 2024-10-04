package fr.balijon.centrale.service.interfaces;

public interface BasicServiceInterface<T, L, C> {

    T create(C o);

    Boolean delete(L id);
}
