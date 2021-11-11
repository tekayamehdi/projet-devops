package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger l = Logger.getLogger(ControllerEmployeImpl.class);
	private static final Object Vauth = "In authenticate :";
	private static final Object Vrech ="Je vais lancer la recherche du employeManagedEntity en fonction du employeId";
	private static final Object Vrech2 ="Je viens de finir la recherche.";
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		try {

			l.info(Vauth);
			l.debug("Je vais lancer la authenticate");

			l.debug("Je viens de finir l'authenticate.");
			l.info("Out authenticate() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans authenticate() : " + e);}
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
	

			l.info("In addOrUpdateEmploye : ");
			l.debug("Je vais lancer add Or Update ");
			employeRepository.save(employe);
			l.debug("Je viens de finir add or update.");
			l.info("fin addOrUpdateEmploye without errors.");

		
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		l.info("In mettreAjourEmailByEmployeId ");
		l.debug("je vais mettre à jour email de l'employe");
		Optional<Employe> value = employeRepository.findById(employeId);
		if (value.isPresent()) {
			Employe employe = value.get();
			employe.setEmail(email);
			employeRepository.save(employe);
		}
		else {
			l.debug("je viens de mettre à jour email de l'employe");
			l.info("fin mettreAjourEmailByEmployeId");
		}
		}
	

	


	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {

		l.info("In affecterEmployeADepartement ");
		l.debug("je vais affecter employe à un departement");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if (value.isPresent()) {
			Departement depManagedEntity = value.get();
			Optional<Employe> value1 = employeRepository.findById(employeId);
			if (value1.isPresent()) {
				Employe employeManagedEntity = value1.get();

				if (depManagedEntity.getEmployes() == null) {

					List<Employe> employes = new ArrayList<>();
					employes.add(employeManagedEntity);
					depManagedEntity.setEmployes(employes);
				}
				else {

					depManagedEntity.getEmployes().add(employeManagedEntity);

				}
			}
		}
		l.debug("je viens d'affecter l'employe à un departement");
		l.info("fin affecterEmployeADepartement");

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		l.info("In desaffecterEmployeDuDepartement");
		l.debug("je vais desaffecter employe d'un departement");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if (value.isPresent()) {
			Departement dep = value.get();

			int employeNb = dep.getEmployes().size();
			for (int index = 0; index < employeNb; index++) {
				if (dep.getEmployes().get(index).getId() == employeId) {
					dep.getEmployes().remove(index);
					break;
				}
			}
		}
		l.debug("je viens de desaffecter l'employe du departement");
		l.info("fin desaffecterEmployeDuDepartement");
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
	
		l.info("In ajouterContrat");
		l.debug("je vais lancer save de contrat");
		contratRepoistory.save(contrat);
		l.debug("je viens de finir save de contrat");
		l.info("fin de ajouterContrat  ");
		return contrat.getReference();
		
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		l.info("In affecterContratAEmploye ");
		l.debug("je vais affecter un contrat à un employe");
		Optional<Contrat> value = contratRepoistory.findById(contratId);
		if (value.isPresent()) {
			Contrat contratManagedEntity = value.get();
			Optional<Employe> value1 = employeRepository.findById(employeId);
			if (value1.isPresent()) {

				Employe employeManagedEntity = value1.get();

				contratManagedEntity.setEmploye(employeManagedEntity);
				contratRepoistory.save(contratManagedEntity);
			}
		}
		l.debug("je viens d'affecter un contrat à un employe");
		l.info("fin affecterContratAEmploye");

	}

	public String getEmployePrenomById(int employeId) {
		l.info("In getEmployePrenomById ");
		l.debug("je vais récupéré le prenom d'un employe à travers son id");
		Optional<Employe> value = employeRepository.findById(employeId);
		if (value.isPresent()) {
			Employe employeManagedEntity = value.get();
			return employeManagedEntity.getPrenom();
		}
		else {

			l.debug("je viens de récupéré le prenom d'un employe à travers son id");
			l.info("fin getEmployePrenomById");
			return null;
		}
	
	}
	 
	public void deleteEmployeById(int employeId)
	{l.info("In deleteEmployeById");
	l.debug("je vais effacer un employe à travers son id");
	Optional<Employe> value = employeRepository.findById(employeId);
	if (value.isPresent()) {
		Employe employe = value.get();
		for (Departement dep : employe.getDepartements()) {
			dep.getEmployes().remove(employe);
		}
		employeRepository.delete(employe);
		l.debug("je viens d'effacer un employe à travers son id");
		l.info("fin deleteEmployeById");
	}

	l.debug("le employee n'existe pas");
	l.info("fin de la methode delete employe by id");
		}

	public void deleteContratById(int contratId) {
		l.info("In deleteContratById ");
		l.debug("je vais effacer un contrat à travers son id");
		Optional<Contrat> value = contratRepoistory.findById(contratId);
		if (value.isPresent()) {
			Contrat contratManagedEntity = value.get();
			contratRepoistory.delete(contratManagedEntity);
			l.debug("je viens d'effacer un contrat à travers son id");
			l.info("fin de la methode delete contrat by id");
		}
		else {
			l.debug("le contrat n'existe pas");
			l.info("fin deleteContratById");
		}
	}

	public int getNombreEmployeJPQL() {
		
			l.info("in getNombreEmployeJPQL");
			l.debug("Je vais lancer l'affichage du nombre d'employe :");
			int res = employeRepository.countemp();
			l.debug("Je viens de recuperer le nombre d'employe");
			l.info("Out getNombreEmployeJPQL  without errors.");
			
			
		return res ;
		
	}

	public List<String> getAllEmployeNamesJPQL() {
	
			l.info("in getNombreEmployeJPQL");
			l.debug("Je vais lancer l'affichage du nombre d'employe :");
			List<String> res =employeRepository.employeNames();
			l.info("Out getNombreEmployeJPQL  without errors.");
			l.debug("je viens récupérer les noms des tous les employes");
		return res;
		

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		l.info("In getAllEmployeByEntreprise");
		l.debug("je vais récupérer les employe par entreprise");
		List<Employe> res = employeRepository.getAllEmployeByEntreprisec(entreprise);
		l.debug("je viens de récupérer les employe par entreprise");
		l.info("fin de  la methode  getAllEmployeByEntreprise");
		return res;
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		try {

			l.info("In deleteAllContractJPQL : ");
			l.debug("Je viens de lancer la mise a jour des Emails avec IdJPQL.");
			l.debug("je viens de finir la mise a jour des Emails avec IdJPQL:"+employeId+"email:"+email);
			l.info("Out mettreAjourEmailByEmployeIdJPQL() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans mettreAjourEmailByEmployeIdJPQL() : " + e); }
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		l.info("In deleteAllContratJPQL");
		l.debug("je vais supprimer tous les contrats");
		employeRepository.deleteAllContratJPQL();
		l.debug("je viens de supprimer tous les contrats");
		l.info("fin de  la methode  deleteAllContratJPQL");
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {

		l.info("In getSalaireByEmployeIdJPQL");
		l.debug("je vais récupérer la salaire du employe by id");
		float res = employeRepository.getSalaireByEmployeIdJPQL(employeId);
		l.debug("je viens de récupérer la salaire du employe by id");
		l.info("fin de  la methode  getSalaireByEmployeIdJPQL");
		return res;
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		l.info("In getSalaireMoyenByDepartementId");
		l.debug("je vais récupérer la salaire moyene by departement");
		Double a = employeRepository.getSalaireMoyenByDepartementId(departementId);
		l.debug("je viens de récupérer la salaire moyene by departement");
		l.info("fin de  la methode  getSalaireMoyenByDepartementId");
		return a;
		
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {


			l.info("In getTimesheetsByMissionAndDate");
			l.debug("je vais récupérer la liste du Timesheet by mission and date");
			List<Timesheet> res = timesheetRepository.getTimesheetsByMissionAndDate(employe, mission,
					dateDebut, dateFin);
			l.debug("je viens de récupérer la liste du Timesheet by mission and date");
			l.info("fin de  la methode  getTimesheetsByMissionAndDate");
			return res;
	}

	public List<Employe> getAllEmployes() {
		l.info("In getAllEmployes");
		l.debug("je vais récupérer la liste de tous les employes");
		List<Employe> res = (List<Employe>) employeRepository.findAll();
		l.debug("je viens de récupérer la liste de tous les employes");
		l.info("fin de  la methode  get all  employe");
		return res;
	}
}
