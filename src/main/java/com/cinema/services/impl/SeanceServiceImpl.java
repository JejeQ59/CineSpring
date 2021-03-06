package com.cinema.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.dto.RechercheDTO;
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
	public void deleteById(String id) {
		this.repo.deleteById(id);

	}
	
	@Override
	public void delete(Seance s) {
		this.repo.delete(s);

	}

	/*
	 * Ajout d'un client à une seance
	 */
	@Override
	public Seance addClient(String sId, String cId) {
		Seance sea= null;
		Optional<Seance> seance = this.repo.findById(sId);
		Assister ass = new Assister();
		float prix;
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

		return sea;
	}
	
	/*
	 * Ajout d'un film sur une séance
	 */
	@Override
	public Seance addFilm(String sId, String fId) {
		Seance res = null;
		Optional<Seance> s = this.repo.findById(sId);
		Optional<Film> f =this.serviceF.findById(fId);
		res = s.get();
		res.setFilm(f.get());
		this.update(res);
		return res;
	}
	

	/*
	 * Ajout d'une salle dans la seance
	 */
	@Override
	public Seance addSalle(String sId, String saId) {
		Seance res = null;
		Optional<Seance> s = this.repo.findById(sId);
		Optional<Salle> sa =this.serviceS.findById(saId);
		res = s.get();
		res.setSalle(sa.get());
		this.update(res);
		return res;
	}

	/*
	 * Recherche toutes les seances en fonction du titre du film
	 */
	@Override
	public List<Seance> findBySeanceByTitreFilmLike(String titre) {
		List<Film> films = this.serviceF.findByTitreLike(titre);
		return this.repo.findAllByFilmIn(films);
		
	}

	/*
	 * Recherche la recette d'une séance
	 */
	@Override
	public float findRecetteSeance(String id) {
		Optional<Seance> s = this.repo.findById(id);
		float res = 0;
		res= (float) s.get().getClients().stream().mapToDouble(se -> se.getPrix()).sum();
		return res;
	}

	/*
	 * Recherche le nombre de place restante pour la séance
	 */
	@Override
	public int findPlacesRestantesSeance(String id) {
		Optional<Seance> s = this.repo.findById(id);
		int res = 0;
		if(s.get().getSalle()!=null)
		{
				res = s.get().getSalle().getPlace() - s.get().getClients().size();
		}
		return res;
	}

	@Override
	public List<Seance> findByDateBetween(LocalDateTime min, LocalDateTime max) {
		return this.repo.findByDateBetween(min, max);
	}


	/*
	 * Recherche la recette d'un film sur toutes les seances
	 */
	@Override
	public float recetteFilm(Film f) {
		float res=0.00f;
		List<Seance> seance = this.findBySeanceByTitreFilmLike(f.getTitre());
		for(Seance s : seance)
		{
			res+=(float) s.getClients().stream().mapToDouble(se -> se.getPrix()).sum();
		}
		return res;
	}

	/*
	 * Recherche les seances par genre de film
	 */
	@Override
	public List<Seance> findAllByFilmGenre(String genre) {
		List<Film> films = this.serviceF.findAllByGenre(genre);
		if(films!=null)
		{
			return this.repo.findAllByFilmIn(films);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun film n'est du genre : " + genre);
		}
	}

	/*
	 *  Recherche les seances par age limite
	 */
	@Override
	public List<Seance> findAllByFilmAgeLimite(int age) {
		List<Film> films = this.serviceF.findAllByAgeLimite(age);
		if(!films.isEmpty())
		{
			return this.repo.findAllByFilmIn(films);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun film n'a d'âge limite à : " + age + " ans");
		}
	}


	/*
	 * test age du client
	 */
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

	/*
	 * calcul de la reduction en fonction de l'age
	 */
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

	/*
	 * Calcul du prix de la seance pour le client
	 */
	private float calculPrixSeance(Seance seance) {

		float prixBase = 10;
		float prixFinal;

		switch(seance.getType()) {
		case "3D":
			prixFinal = prixBase + 3;
			break;
		case "IMAX":
			prixFinal = prixBase + 6;
			break;
		case "4DX":
			prixFinal = prixBase + 8;
			break;
		default:
			prixFinal = prixBase;
		}

		return prixFinal;
	}

	/*
	 * Recherche en fonction de critères de recherche
	 */
	@Override
	public List<Seance> rechercheByGenreFilmOrPlageHoraireOrAgeOrTypeSeance(RechercheDTO recherche) {
		return this.repo.rechercheByGenreFilmOrPlageHoraireOrAgeOrTypeSeance(recherche);
	}
	
	/*
	*	Recherche la moyenne d'un film
	*/
	@Override
	public float moyenneFilm(Film f) {
		float res=0.00f;
		List<Seance> seance = this.findBySeanceByTitreFilmLike(f.getTitre());
		for(Seance s : seance)
		{
			res+= s.getClients().stream().filter(se -> se.getNote()!=null).mapToDouble(se -> se.getNote()).average().orElse(-0);
		}
		
		if(res <= 0)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le film n'a aucune note");
		}
		return res;
	}

	/*
	 * ajout d'une note client sur le film
	 */
	@Override
	public Seance addNote(String aId, String assId, Integer note) {
		if(note < 1 || note > 10)
		{
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "la note doit être comprise entre 1 et 10");
		}
		Optional<Seance> s = this.repo.findById(aId);
		Optional<Assister> ass = this.serviceA.findById(assId);
		ass.get().setNote(note);
		this.serviceA.update(ass.get());
		this.update(s.get());
		return s.get();
	}
	
	
}
