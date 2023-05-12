package com.example.homework1.mapper;

import com.example.homework1.entities.Country;
import com.example.homework1.model.CountryDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ICountryMapper{

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void countryFromDto(CountryDto dto, @MappingTarget Country entity);
        void countryDtoFromEntity(Country entity,@MappingTarget CountryDto dto);
}
