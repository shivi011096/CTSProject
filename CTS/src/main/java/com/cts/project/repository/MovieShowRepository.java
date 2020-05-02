package com.cts.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.project.entity.MovieShow;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> 
{

	Page<MovieShow> findByMovieId(Integer movieId, Pageable pageable);

	Optional<MovieShow> findByIdAndMovieId(Integer movieshowId, Integer movieId);

}
