package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.datetime.standard.DateTimeContext;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "showtime")

public class ShowTime 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(name = "stime")
	private DateTimeContext sTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DateTimeContext getsTime() {
		return sTime;
	}
	public void setsTime(DateTimeContext sTime) {
		this.sTime = sTime;
	}
	
	
}
