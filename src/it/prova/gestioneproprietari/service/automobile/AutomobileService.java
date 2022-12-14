package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileService {
	public List<Automobile> listAllAutomobile() throws Exception;

	public Automobile caricaSingoloAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;

	//TODO
	public List<Automobile> trovaAutomobiliCodiceFiscale(String input) throws Exception;
	
	public List<Automobile> trovaAutomobiliConErrori() throws Exception;
	
	//per injection
	public void setAutomobileDAO(AutomobileDAO automobileDAO);
}
