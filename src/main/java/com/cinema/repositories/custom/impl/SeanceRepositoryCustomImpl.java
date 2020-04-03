package com.cinema.repositories.custom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.dto.RechercheDTO;
import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.repositories.custom.SeanceRepositoryCustom;

public class SeanceRepositoryCustomImpl implements SeanceRepositoryCustom {
	
	@Autowired
	private MongoTemplate template;

	@Override
	public List<Seance> rechercheByGenreFilmOrPlageHoraireOrAgeOrTypeSeance(RechercheDTO recherche) {
		List<Seance> res;
		List<Film> films;
		boolean rechercheFilm=false;
		Query query = new Query();
		Query queryfilm = new Query();
		
		if(recherche==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucun critère de recherche n'est renseigné");
		}
		
		if(recherche.getType()!=null)
		{
			query.addCriteria(Criteria.where("type").is(recherche.getType()));
		}
		
		if(recherche.getMin()!=null && recherche.getMax()!=null)
		{
			query.addCriteria(Criteria.where("date").gte(recherche.getMin()).and("date").lt(recherche.getMax()));
		}
		
		if(recherche.getGenre()!=null)
		{
			rechercheFilm = true;
			queryfilm.addCriteria(Criteria.where("genre").is(recherche.getGenre()));
		}
		
		if(recherche.getAgeLimite()!=0)
		{
			rechercheFilm = true;
			queryfilm.addCriteria(Criteria.where("ageLimite").lt(recherche.getAgeLimite()));
					
		}	
		
		if(rechercheFilm)
		{
			films=template.find(query, Film.class);
			query.addCriteria(Criteria.where("film").in(films));
		}
		
		res = template.find(query, Seance.class);
		
		
		for(Seance s : res)
		{
			s.setClients(null);
		}
		
		
		return res;
	}
	
	

}
