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

import com.cts.project.entity.Movie;
import com.cts.project.entity.MovieShow;
import com.cts.project.entity.Theater;
import com.cts.project.repository.MovieRepository;
import com.cts.project.repository.MovieShowRepository;
import com.cts.project.repository.TheaterRepository;

public class MovieShowController 
{
  
   @Autowired
   private MovieShowRepository movieShowRepository;
  
   @Autowired
   private TheaterRepository theaterRepository;
    
   @Autowired
   private MovieRepository movieRepository;

    

//    @GetMapping("/city/{cityId}/theater")
//    public Page<Theater> getAllTheatersByCityId(@PathVariable (value = "cityId") Integer cityId,
//                                                Pageable pageable) {
//        return theaterRepository.findByCityId(cityId, pageable);
//    }
    
    @GetMapping("/movie/{movieId}/movieshow")
    public Page<MovieShow> getAllMovieShowByMovieId(@PathVariable (value = "movieId") Integer movieId,
                                                Pageable pageable) {
        return movieShowRepository.findByMovieId(movieId, pageable);
    }

//    @PostMapping("/city/{cityId}/theater")
//    public Theater createTheater(@PathVariable (value = "cityId") Integer cityId,
//                                 @Valid @RequestBody Theater theater) {
//        return cityRepository.findById(cityId).map(city -> {
//            theater.setCity(city);
//            return theaterRepository.save(theater);
//        }).orElseThrow(() -> new ResourceNotFoundException("cityId " + cityId + " not found"));
//    }
    
    @PostMapping("/movie/{movieId}/movieshow")
    public MovieShow createMovieShow(@PathVariable (value = "movieId") Integer movieId,
                                 @Valid @RequestBody MovieShow movieshow) {
        return movieRepository.findById(movieId).map(movie -> {
            movieshow.setMovie(movie);
            return movieShowRepository.save(movieshow);
        }).orElseThrow(() -> new ResourceNotFoundException("Movie Id : " + movieId + " not found"));
    }

//    @PutMapping("/city/{cityId}/theater/{theaterId}")
//    public Theater updateTheater(@PathVariable (value = "cityId") Integer cityId,
//                                 @PathVariable (value = "theaterId") Integer theaterId,
//                                 @Valid @RequestBody Theater theaterRequest) {
//        if(!cityRepository.existsById(cityId)) {
//            throw new ResourceNotFoundException("CityId " + cityId + " not found");
//        }
//
//        return theaterRepository.findById(theaterId).map(theater -> {
//            theater.settName(theaterRequest.gettName());
//            return theaterRepository.save(theater);
//        }).orElseThrow(() -> new ResourceNotFoundException("TheaterId " + theaterId + "not found"));
//    }
    
    @PutMapping("/movie/{movieId}/movieshow/{movieshowId}")
    public MovieShow updateMovieShow(@PathVariable (value = "movieId") Integer movieId,
                                 @PathVariable (value = "movieshowId") Integer movieshowId,
                                 @Valid @RequestBody MovieShow movieshowRequest) {
        if(!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Movie Id : " + movieId + " not found");
        }

        return movieShowRepository.findById(movieshowId).map(movieshow -> {
            movieshow.setsTime(movieshowRequest.getsTime());
            return movieShowRepository.save(movieshow);
        }).orElseThrow(() -> new ResourceNotFoundException("Movie show Id : " + movieshowId + " not found"));
    }


//    @DeleteMapping("/city/{cityId}/theater/{theaterId}")
//    public ResponseEntity<?> deleteTheater(@PathVariable (value = "cityId") Integer cityId,
//                              @PathVariable (value = "theaterId") Integer theaterId) {
//        return theaterRepository.findByIdAndCityId(theaterId, cityId).map(theater -> {
//            theaterRepository.delete(theater);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Theater not found with id " + theaterId + " and CityId " + cityId));
//    }
    
    @DeleteMapping("/movie/{movieId}/movieshow/{movieshowId}")
    public ResponseEntity<?> deleteMovieShow(@PathVariable (value = "movieId") Integer movieId,
                              @PathVariable (value = "movieshowId") Integer movieshowId) {
        return movieShowRepository.findByIdAndMovieId(movieshowId, movieId).map(movieshow -> {
        	movieShowRepository.delete(movieshow);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Movie show not found with id : " + movieshowId + " and Movie Id : " + movieId));
    }

}
