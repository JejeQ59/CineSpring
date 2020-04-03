package com.cinema.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cinema.models.Cinema;
import com.cinema.models.Salle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechercheDTO {
	
	private String genre;
	private LocalDateTime min;
	private LocalDateTime max;
	private int ageLimite;
	private String type;

}
