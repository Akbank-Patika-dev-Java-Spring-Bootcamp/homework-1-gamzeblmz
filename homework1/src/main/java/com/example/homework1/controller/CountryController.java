package com.example.homework1.controller;
import com.example.homework1.model.CountryDto;
import com.example.homework1.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1")
public class CountryController {
    private final CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/")
    public ResponseEntity<CountryDto> createCountry (@RequestBody CountryDto countryDto){
        return ResponseEntity.ok(countryService.createCountry(countryDto));
//        return new ResponseEntity<>(this.countryService.createCountry(countryDto), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CountryDto>> getCountries(){
        return new ResponseEntity<>(this.countryService.gelCountries(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable long id){
        return ResponseEntity.ok(countryService.getCountrybyId(id));
    }

    @PutMapping("/{id}/{presidentName}")
    public ResponseEntity<CountryDto> updatePresidentbyId(@PathVariable long id, @PathVariable String presidentName){
        return ResponseEntity.ok(countryService.updatePresidentbyId(id,presidentName));
    }


}
