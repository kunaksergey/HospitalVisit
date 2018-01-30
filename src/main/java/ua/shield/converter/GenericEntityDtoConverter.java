package ua.shield.converter;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericEntityDtoConverter<E,D> {

    E createFromDto(D dto);

    D createFromEntity(E entity);

    E updateEntity(E entity,D dto);

    default List<D> createFromEntities(final Collection<E> entity){
        return entity
                .stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }

    default List<E> createFromDtos(final Collection<D> dtos){
        return dtos
                .stream()
                .map(this::createFromDto)
                .collect(Collectors.toList());
    }
}
