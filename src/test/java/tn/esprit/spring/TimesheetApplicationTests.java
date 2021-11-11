package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ord.junit.assert;

import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.EntrepriseRepository;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootTest
class TimesheetApplicationTests {

	@Test
	void contextLoads() {
	}
	private static final Logger l = LogManager.getLogger(TimesheetApplication.class);

	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Transactional
	@Test
	void AffecterDepartementaEntrepriseJPQL() {

		l.info("lancer la methode AffecterDepartementaEntrepriseJPQL");
		l.debug("je vais affecter un mission a un departement");
		Departement departement= new Departement("mehdi tekaya");
		int id = entrepriseControl.ajouterDepartement(departement);
		l.info(id);
		l.debug("je viens de mettre ajour l'email d'un employe a traver son id");
		l.info("fin de  la methode  mettreAjourEmailByEmployeIdJPQL");

	}
}
