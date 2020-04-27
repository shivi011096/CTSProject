package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.ShowTime;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {

}
