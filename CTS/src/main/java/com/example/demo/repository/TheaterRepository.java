package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Theater;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer>
{

	Page<Theater> findByCityId(Integer cityId, Pageable pageable);

	Optional<Theater> findByIdAndCityId(Integer theaterId, Integer cityId);

}
