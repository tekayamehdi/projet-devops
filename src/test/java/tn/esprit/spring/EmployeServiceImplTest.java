package tn.esprit.spring;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
public class EmployeServiceImplTest {
	
	private Employe employe;
	private Contrat contrat;

		public void init() {
			//set employe
			boolean actif = true;
			employe = new Employe();
			employe.setId(0);
			employe.setNom("habib");
			employe.setPrenom("hanini");
			employe.setEmail("habibhnini@gmail.com");
			employe.setPassword("habib123");
			employe.setActif(actif);
			employe.setRole(Role.INGENIEUR);
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = format.parse("26/10/1985");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//set contrat
			contrat.setDateDebut(date);
			
		}
	
	
		private EmployeRepository employeRepository ;
		

		private EmployeServiceImpl employeService = new EmployeServiceImpl();



		public void addOrUpdateEmployeTest() {
		employeService.addOrUpdateEmploye(employe);
		}
		
		public void getAllEmployesTest() {
			employeService.getAllEmployes();
		}
		
}
