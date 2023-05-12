package com.example.homework1.services;

import com.example.homework1.entities.Country;
import com.example.homework1.model.CountryDto;
import com.example.homework1.mapper.ICountryMapper;
import com.example.homework1.repository.ICountryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CountryService{

    @Autowired
    private ICountryRepository countryRepository;

    private ICountryMapper countryMapper;

    public CountryDto createCountry (CountryDto countryDto){
        Country country = new Country();
        this.countryMapper = Mappers.getMapper(ICountryMapper.class);
        countryMapper.countryFromDto(countryDto, country);
        this.countryRepository.save(country);
        return countryDto;
    }

    public List<CountryDto> gelCountries(){
        this.countryMapper = Mappers.getMapper(ICountryMapper.class);
        List<Country> countryList = this.countryRepository.findAll();
        if(countryList != null){
            List<CountryDto> countryDtoList = new ArrayList<>();
            for(Country country : countryList) {
                CountryDto countryDto = new CountryDto();
                countryMapper.countryDtoFromEntity(country, countryDto);
                countryDtoList.add(countryDto);
            }
            return countryDtoList;
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: ");
    }

    public CountryDto getCountrybyId(long id) {
        Country country = this.countryRepository.getById(id);
        if (country != null) {
            this.countryMapper = Mappers.getMapper(ICountryMapper.class);
            CountryDto countryDto = new CountryDto();
            countryMapper.countryDtoFromEntity(country, countryDto);
            return countryDto;
        } else  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: " + id);
    }

    public CountryDto updatePresidentbyId(long id,String presidentName) {
        Country country = this.countryRepository.getById(id);
        if (country != null) {
           country.setPresident(presidentName);
           this.countryRepository.save(country);
            this.countryMapper = Mappers.getMapper(ICountryMapper.class);
            CountryDto countryDto = new CountryDto();
            countryMapper.countryFromDto(countryDto,country);
            return countryDto;
        } else  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: " + id);
    }

}
