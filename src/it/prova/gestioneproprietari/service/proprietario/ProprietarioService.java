package it.prova.gestioneproprietari.service.proprietario;

import java.util.Date;
import java.util.List;

import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioService {
	public List<Proprietario> listAllProprietario() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	//TODO
	public int contaProprietariDaAnnoImmatricolazione(Date annoInPoi) throws Exception;
	
	//per injection
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
}
