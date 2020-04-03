package com.cinema.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.repositories.custom.SeanceRepositoryCustom;

public interface SeanceRepository extends MongoRepository<Seance, String>, SeanceRepositoryCustom {
	
	public List<Seance> findByDateBetween(LocalDateTime min, LocalDateTime max);
	public List<Seance> findAllByFilmTitre(String id);
	public List<Seance> findAllByFilmIn(Iterable<Film> films);
	

}
