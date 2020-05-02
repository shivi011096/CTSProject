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

import com.cts.project.entity.Movie;
import com.cts.project.repository.MovieRepository;

@RestController
public class MovieController 
{
	@Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movie") 
    public Page<Movie> getAllMovie(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @PostMapping("/movie")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/movie/{movieId}")
    public Movie updateMovie(@PathVariable Integer movieId, @Valid @RequestBody Movie movieRequest) {
        return movieRepository.findById(movieId).map(movie -> {
            movie.setmName(movieRequest.getmName());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }


    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer movieId) {
        return movieRepository.findById(movieId).map(movie -> {
        	movieRepository.delete(movie);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }


}
