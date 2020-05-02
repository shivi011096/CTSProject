package com.cts.project.entity;

import java.sql.Time;  

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rental")

public class MovieShow 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(name = "stime")
	private Time sTime;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Movie movie;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "theater_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Theater theater;
//	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getsTime() {
		return sTime;
	}
	public void setsTime(Time sTime) {
		this.sTime = sTime;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
//	public Theater getTheater() {
//		return theater;
//	}
//	public void setTheater(Theater theater) {
//		this.theater = theater;
//	}
	
		
}
