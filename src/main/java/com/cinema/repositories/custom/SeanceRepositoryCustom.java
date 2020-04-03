package com.cinema.repositories.custom;

import java.util.List;

import com.cinema.dto.RechercheDTO;
import com.cinema.models.Seance;

public interface SeanceRepositoryCustom{
	
	public List<Seance> rechercheByGenreFilmOrPlageHoraireOrAgeOrTypeSeance(RechercheDTO recherche);

}
