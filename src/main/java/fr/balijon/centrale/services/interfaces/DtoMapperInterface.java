package fr.balijon.centrale.services.interfaces;

public interface DtoMapperInterface<T, L> {

    T toObject(L dto);

    L toDTO(T object);
}
