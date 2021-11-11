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
	ControllerEmployeImpl employeControl;
	@Autowired
	EmployeRepository employeRepository;
	@Transactional
	@Test
	void mettreAjourEmailByEmployeIdJPQL() {

		l.info("lancer la methode mettreAjourEmailByEmployeIdJPQL");
		l.debug("je vais mettre ajour l'email d'un employe a traver son id");
		Employe employe = new Employe("hanini", "habib", "habhnini@gmail.com",
				"123ingenieur", true, Role.INGENIEUR);
		int employeId = employeControl.ajouterEmploye(employe);
		l.info(employeId);
		Assert.assertNotNull(employeControl.getEmployePrenomById(employeId));
		employeRepository.mettreAjourEmailByEmployeIdJPQL("raed@gmail.com", employeId);
		l.debug("je viens de mettre ajour l'email d'un employe a traver son id");
		l.info("fin de  la methode  mettreAjourEmailByEmployeIdJPQL");

	}
}
