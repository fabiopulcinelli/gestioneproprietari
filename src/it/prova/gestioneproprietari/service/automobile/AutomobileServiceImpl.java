package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileServiceImpl implements AutomobileService{
	
	private AutomobileDAO automobileDAO;

	public void setAutomobileDAO(AutomobileDAO automobileDAO) {
		this.automobileDAO = automobileDAO;
	}

	@Override
	public List<Automobile> listAllAutomobile() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Automobile caricaSingoloAbitante(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Automobile automobileInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idAutomobileInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
