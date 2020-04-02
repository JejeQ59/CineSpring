package com.cinema.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Film;
import com.cinema.models.Salle;
import com.cinema.models.Seance;
import com.cinema.repositories.SeanceRepository;
import com.cinema.services.AssisterService;
import com.cinema.services.ClientService;
import com.cinema.services.FilmService;
import com.cinema.services.SalleService;
import com.cinema.services.SeanceService;


@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository repo;

	@Autowired
	private ClientService serviceC;
	
	@Autowired
	private AssisterService serviceA;
	
	@Autowired
	private FilmService serviceF;
	
	@Autowired
	private SalleService serviceS;

	@Override
	public Seance save(Seance s) {
		return this.repo.save(s);
	}

	@Override
	public List<Seance> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Seance> findById(String id) {
		return this.repo.findById(id);
	}

	@Override
	public Seance update(Seance s) {
		return this.repo.save(s);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);

	}

	//Ajout d'un client à une seance
	@Override
	public Seance addClient(String sId, String cId) {
		Seance sea= null;
		Optional<Seance> seance = this.repo.findById(sId);
		Assister ass = new Assister();
		float prix;

		if(seance.isPresent()) {
			Optional<Client> client = this.serviceC.findById(cId);
			sea = seance.get();
			if(seance.get()==null)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la salle n'existe pas");
			}
			else
			{
				if(salleRempli(sea))
				{
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "le nombre de client de la salle est déjà atteint");
				}
			}	
			
			if(client.isPresent())
			{
				
				Client cli = client.get();	
				if(testAge(sea, cli))
				{
					prix = calculPrixSeance(sea)- calculReduction(cli);
					ass.setPrix(prix);
					ass.setClient(cli);
					sea.getClients().add(ass);
					this.serviceA.save(ass);
					this.save(sea);
				}
				else
				{
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "le client n'a pas l'âge pour voir le film");
				}
			}
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le client d'id: " + cId + " n'existe pas");
			}
		}	
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la séance d'id: " + sId + " n'existe pas");
		}

		return sea;
	}

	//test age du client
	private boolean testAge(Seance sea, Client cli) {
		boolean test = false;
		if(Period.between(cli.getNaissance(), LocalDate.now()).getYears() 
				> sea.getFilm().getAgeLimite()) {
			test = true;
		}
		
		return test;
	}

	private boolean salleRempli(Seance sea) {
		if(sea.getSalle().getPlace() == sea.getClients().size()) {
			return true;
		}
		else
		{
			return false;
		}
	}

	//calcul de la reduction en fonction de l'age
	private float calculReduction(Client cli) {
		float reduction = 0;
		if(cli.isEtudient())
		{
			reduction = 2;
		}
		
		if(Period.between(cli.getNaissance(), LocalDate.now()).getYears() < 10) {
			reduction = 4;
		}
		return reduction;
	}

	//Calcul du prix de la seance pour le client
	private float calculPrixSeance(Seance seance) {

		float prixBase = 10;
		float prixFinal;

		switch(seance.getType()) {
		case "seance 3D":
			prixFinal = prixBase + 3;
			break;
		case "seance IMAX":
			prixFinal = prixBase + 6;
			break;
		case "seance 4DX":
			prixFinal = prixBase + 8;
			break;
		default:
			prixFinal = prixBase;
		}

		return prixFinal;
	}

	@Override
	public Seance addFilm(String sId, String fId) {
		Seance res = null;
		Optional<Seance> s = this.repo.findById(sId);
		
		if(s.isPresent())
		{
			Optional<Film> f =this.serviceF.findById(fId);
			if(f.isPresent()) { 
				res = s.get();
				res.setFilm(f.get());
				this.update(res);
			}
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le film d'id: " + fId + " n'existe pas");
			}
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la seance d'id: " + sId + " n'existe pas");
		}
		
		return res;
	}
	

	@Override
	public Seance addSalle(String sId, String saId) {
		Seance res = null;
		Optional<Seance> s = this.repo.findById(sId);
		
		if(s.isPresent())
		{
			Optional<Salle> sa =this.serviceS.findById(saId);
			if(sa.isPresent()) { 
				res = s.get();
				res.setSalle(sa.get());
				this.update(res);
			}
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la salle d'id: " + saId + " n'existe pas");
			}
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la seance d'id: " + sId + " n'existe pas");
		}
		
		return res;
	}

	//Recherche toutes les seances en fonction du titre du film
	@Override
	public List<Seance> findBySeanceByTitreFilm(String titre) {
		List<Seance> res = this.repo.findAll().stream().filter(sea -> sea.getFilm().getTitre()
				.equals(titre)).collect(Collectors.toList());
		return res;
		
	}

	//Recherche la recette d'une séance
	@Override
	public float findRecetteSeance(String id) {
		Optional<Seance> s = this.repo.findById(id);
		float res = 0;
		
		if(s.isPresent())
		{
			res = (float) s.get().getClients().stream().mapToDouble(se -> se.getPrix()).sum();
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la seance d'id: " + id + " n'existe pas");
		}
		
		return res;
	}

	@Override
	public int findPlacesRestantesSeance(String id) {
		Optional<Seance> s = this.repo.findById(id);
		int res = 0;
		
		if(s.isPresent())
		{
		
			if(s.get().getSalle()!=null)
			{
				res = s.get().getSalle().getPlace() - s.get().getClients().size();
			}
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la seance d'id: " + id + " n'existe pas");
		}
		
		return res;
	}

	@Override
	public List<Seance> findByDateBetween(LocalDateTime min, LocalDateTime max) {
		return this.repo.findByDateBetween(min, max);
	}


}
