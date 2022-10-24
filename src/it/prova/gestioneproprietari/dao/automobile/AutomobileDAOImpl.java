package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Automobile o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Automobile o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Automobile o) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
