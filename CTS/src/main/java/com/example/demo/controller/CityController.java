package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;

import javax.validation.Valid;

@RestController
public class CityController 
{
	@Autowired
    private CityRepository cityRepository;

    @GetMapping("/city") 
    public Page<City> getAllCity(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @PostMapping("/city")
    public City createCity(@Valid @RequestBody City city) {
        return cityRepository.save(city);
    }

    @PutMapping("/city/{cityId}")
    public City updateCity(@PathVariable Integer cityId, @Valid @RequestBody City cityRequest) {
        return cityRepository.findById(cityId).map(city -> {
            city.setcName(cityRequest.getcName());
            return cityRepository.save(city);
        }).orElseThrow(() -> new ResourceNotFoundException("CityId " + cityId + " not found"));
    }


    @DeleteMapping("/city/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer cityId) {
        return cityRepository.findById(cityId).map(city -> {
            cityRepository.delete(city);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CityId " + cityId + " not found"));
    }

}
