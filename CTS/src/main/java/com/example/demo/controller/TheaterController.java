package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
