package tn.esprit.spring;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;

@SpringBootTest
class TimesheetApplicationTests {

	private static final Logger l = LogManager.getLogger(TimesheetApplication.class);

	@Autowired
	ControllerEmployeImpl employeController;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	ControllerEntrepriseImpl entrepriseController;
	@Autowired
	EmployeRepository entrepriseRepository;
	@Transactional
	@Test
	void ajouterEmployeTest() {

		l.info("lancer la methode ajouterEmployeTest");
		l.debug("je vais ajouter un employe");
		Employe employe = new Employe("hanini", "habib", "habhnini@gmail.com","123ingenieur", true, Role.INGENIEUR);
		int employeId = employeController.ajouterEmploye(employe);
		l.debug("je vais afficher un employe en fonction de l'id");
		Assert.assertNotNull(employeController.getEmployePrenomById(employeId));
		l.debug("je viens d'afficher l'entreprise que je viens d'ajouter");
		l.info("fin de  la methode  ajouterEmployeTest");
	

	}
	
	@Transactional
	@Test
	void ajouterEntrepriseTest() {
		l.info("lancer la methode ajouterEntrepriseTest");
		l.debug("je vais ajouter une entreprise ");
		Entreprise entreprise = new Entreprise("It","test");
		int entrepriseId = entrepriseController.ajouterEntreprise(entreprise);
		Assert.assertNotNull(entrepriseController.getEntrepriseById(entrepriseId));
		l.debug("je viens d'afficher l'entreprise que je viens d'ajouter");
		l.info("fin de la methode ajouterEntrepriseTest");
	}
	
	@Transactional
	@Test
	void ajouterDepartementetaffeterTest() {
		l.info("lancer la methode ajouterEntrepriseTest");
		l.debug("je vais mettre ajour l'email d'un employe a traver son id");
		Departement dep = new Departement("It");
	
		Assert.assertEquals(1,entrepriseController.ajouterDepartement(dep));
		l.debug("je viens d'afficher l'entreprise que je viens d'ajouter");
		
		l.info("fin de la methode ajouterEntrepriseTest");
	}
	
}
