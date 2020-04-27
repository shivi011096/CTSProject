package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Theater;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowTimeRepository;
import com.example.demo.repository.TheaterRepository;

import javax.validation.Valid;

@RestController

public class TheaterController 
{
	@Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private ShowTimeRepository showTimeRepository;
    

    @GetMapping("/city/{cityId}/theater")
    public Page<Theater> getAllTheatersByCityId(@PathVariable (value = "cityId") Integer cityId,
                                                Pageable pageable) {
        return theaterRepository.findByCityId(cityId, pageable);
    }

    @PostMapping("/city/{cityId}/theater")
    public Theater createTheater(@PathVariable (value = "cityId") Integer cityId,
                                 @Valid @RequestBody Theater theater) {
        return cityRepository.findById(cityId).map(city -> {
            theater.setCity(city);
            return theaterRepository.save(theater);
        }).orElseThrow(() -> new ResourceNotFoundException("cityId " + cityId + " not found"));
    }

    @PutMapping("/city/{cityId}/theater/{theaterId}")
    public Theater updateTheater(@PathVariable (value = "cityId") Integer cityId,
                                 @PathVariable (value = "theaterId") Integer theaterId,
                                 @Valid @RequestBody Theater theaterRequest) {
        if(!cityRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("CityId " + cityId + " not found");
        }

        return theaterRepository.findById(theaterId).map(theater -> {
            theater.settName(theaterRequest.gettName());
            return theaterRepository.save(theater);
        }).orElseThrow(() -> new ResourceNotFoundException("TheaterId " + theaterId + "not found"));
    }

    @DeleteMapping("/city/{cityId}/theater/{theaterId}")
    public ResponseEntity<?> deleteTheater(@PathVariable (value = "cityId") Integer cityId,
                              @PathVariable (value = "theaterId") Integer theaterId) {
        return theaterRepository.findByIdAndCityId(theaterId, cityId).map(theater -> {
            theaterRepository.delete(theater);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Theater not found with id " + theaterId + " and CityId " + cityId));
    }
}
