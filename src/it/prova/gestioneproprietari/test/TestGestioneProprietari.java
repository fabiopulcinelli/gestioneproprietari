package it.prova.gestioneproprietari.test;

import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;

public class TestGestioneProprietari {

	public static void main(String[] args) {
		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {
			
			testInserisciProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietario().size() + " elementi.");
			
			testEliminaProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietario().size() + " elementi.");
			
			testInserisciAutomobile(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobile().size() + " elementi.");
			
			testEliminaAutomobile(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobile().size() + " elementi.");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
		// creo nuovo municipio
		Proprietario nuovoProprietario = new Proprietario("Fabio","Pulcinelli","PLCFBA01191",new SimpleDateFormat("dd-MM-yyyy").parse("20-06-2002"));
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
	}
	
	private static void testEliminaProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testEliminaProprietario inizio.............");
		
		List<Proprietario> listaPropretariPresenti = proprietarioService.listAllProprietario();
		if (listaPropretariPresenti.isEmpty())
			throw new RuntimeException("testEliminaProprietario fallito: non ci sono municipi a cui collegarci ");
		Proprietario proprietarioDaRimuovere = listaPropretariPresenti.get(listaPropretariPresenti.size()-1);
		
		proprietarioService.rimuovi(proprietarioDaRimuovere);
		// proviamo a vedere se è stato rimosso
		if (proprietarioService.caricaSingoloProprietario(proprietarioDaRimuovere.getId()) != null)
			throw new RuntimeException("testRimozioneAbitante fallito: record non cancellato ");
		
		System.out.println(".......testEliminaProprietario fine: PASSED.............");
	}
	
	
	////AUTOMOBILI
	
	private static void testInserisciAutomobile(AutomobileService automobileService, ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");
		
		List<Proprietario> proprietari = proprietarioService.listAllProprietario();
		if (proprietari.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono municipi a cui collegarci ");
		
		Automobile nuovaAutomobile = new Automobile("Ford","Fiesta","XX12345XX",new Date());
		nuovaAutomobile.setProprietario(proprietari.get(0));

		// salvo
		automobileService.inserisciNuovo(nuovaAutomobile);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");
				
		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il municipio ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
	}
	
	private static void testEliminaAutomobile(AutomobileService automobileService, ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testEliminaAutomobile inizio.............");
		
		List<Proprietario> listaPropretariPresenti = proprietarioService.listAllProprietario();
		if (listaPropretariPresenti.isEmpty())
			throw new RuntimeException("testEliminaAutomobile fallito: non ci sono municipi a cui collegarci ");
		
		List<Automobile> automobili = automobileService.listAllAutomobile();
		if (automobili.isEmpty())
			throw new RuntimeException("testEliminaAutomobile fallito: non ci sono municipi a cui collegarci ");
		
		Automobile automobileDaRimuovere = automobili.get(automobili.size()-1);
		automobileService.rimuovi(automobileDaRimuovere);
		// proviamo a vedere se è stato rimosso
		if (automobileService.caricaSingoloAutomobile(automobileDaRimuovere.getId()) != null)
			throw new RuntimeException("testRimozioneAbitante fallito: record non cancellato ");
		
		System.out.println(".......testEliminaAutomobile fine: PASSED.............");
	}
}
