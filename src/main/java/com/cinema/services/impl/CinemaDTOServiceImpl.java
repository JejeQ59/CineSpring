package com.cinema.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.models.Salle;
import com.cinema.repositories.CinemaRepository;
import com.cinema.services.CinemaDTOService;
import com.cinema.services.SalleService;

@Service
public class CinemaDTOServiceImpl implements CinemaDTOService {

	@Autowired
	private CinemaRepository repo;
	
	@Autowired
	private SalleService serviceS;

	@Override
	public CinemaDTO save(CinemaDTO c) {
		Cinema cine = c.getCinema();
		if(cine==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "l'objet cinema n'est pas alimenté");
		}
		this.repo.save(cine);
		for(Salle s : c.getSalles())
		{
			Optional<Salle> salle = this.serviceS.findById(s.getId());
			salle.get().setCinema(cine);
			this.serviceS.save(salle.get());
		}
		
		return c;
	}

}
