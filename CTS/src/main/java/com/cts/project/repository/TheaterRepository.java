package com.cts.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.project.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer>
{


}
