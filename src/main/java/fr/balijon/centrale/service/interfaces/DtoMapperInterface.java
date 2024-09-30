package fr.balijon.centrale.service.interfaces;

public interface DtoMapperInterface<T, L> {

    T toObject(L dto);

    L toDTO(T object);
}
