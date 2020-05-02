package com.cts.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.project.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> 
{

}
