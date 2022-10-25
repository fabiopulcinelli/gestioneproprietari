package it.prova.gestioneproprietari.dao.automobile;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;

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
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Automobile o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Automobile o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));
	}

	@Override
	public List<Automobile> findAllAutomobiliCodiceFiscaleStarts(String input) throws Exception {
		TypedQuery<Automobile> query = entityManager.createQuery("from Automobile a join fetch a.proprietario WHERE codicefiscale LIKE ?1", Automobile.class);
		return query.setParameter(1, input + "%").getResultList();
	}

	@Override
	public List<Automobile> findAllAutomobiliConErrori() throws Exception {
		TypedQuery<Automobile> query = entityManager.createQuery("from Automobile a join fetch a.proprietario WHERE (YEAR(?1) - YEAR(datadinascita)) < 18", Automobile.class);
		return query.setParameter(1, new Date()).getResultList();
	}
}
