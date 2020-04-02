package com.cinema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cinema.models.Film;

public interface FilmRepository extends MongoRepository<Film, String> {
	
	public Optional<Film> findAllByTitre(String titre);
	public List<Film> findAllByGenre(String genre);
	public List<Film> findAllByAgeLimite(int age);

}
