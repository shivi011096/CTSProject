package com.cts.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.project.entity.Theater;
import com.cts.project.repository.TheaterRepository;

@RestController
public class TheaterController 
{
	@Autowired
    private TheaterRepository theaterRepository;

    @GetMapping("/theater") 
    public Page<Theater> getAllTheater(Pageable pageable) {
        return theaterRepository.findAll(pageable);
    }

    @PostMapping("/theater")
    public Theater createTheater(@Valid @RequestBody Theater theater) {
        return theaterRepository.save(theater);
    }

    @PutMapping("/theater/{theaterId}")
    public Theater updateTheater(@PathVariable Integer theaterId, @Valid @RequestBody Theater theaterRequest) {
        return theaterRepository.findById(theaterId).map(theater -> {
            theater.settName(theaterRequest.gettName());
            return theaterRepository.save(theater);
        }).orElseThrow(() -> new ResourceNotFoundException("Theater Id : " + theaterId + " not found"));
    }


    @DeleteMapping("/theater/{theaterId}")
    public ResponseEntity<?> deleteTheater(@PathVariable Integer theaterId) {
        return theaterRepository.findById(theaterId).map(theater -> {
        	theaterRepository.delete(theater);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Theater Id : " + theaterId + " not found"));
    }
}
