package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

			l.info("In authenticate : ");
			l.debug("Je vais lancer la authenticate");

			l.debug("Je viens de finir l'authenticate.");
			l.info("Out authenticate() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans authenticate() : " + e);}
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		try {

			l.info("In addOrUpdateEmploye : ");
			l.debug("Je vais lancer add Or Update ");

			l.debug("Je viens de finir add or update.");
			l.info("Out addOrUpdateEmploye without errors.");
			}
			catch (Exception e) { l.error("Erreur dans addOrUpdateEmploye() : " + e);}
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try {

			l.info("In mettreAjourEmailByEmployeId : ");
			l.debug("Je vais lancer la mise a jour de l'employe en fonction de l'id"+employeId);

			l.debug("Je viens de finir la mise a jour de l'employe.");
			l.info("Out authenticate() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans authenticate() : " + e);}
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);
		employeRepository.save(employe);

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
	

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);
		}
		try {

			l.info("In authenticate : ");
			l.debug("Je vais lancer la recherche du depManaged en fonction du depId:"+depId);
			l.debug("Je vais lancer la recherche du employeManagedEntity en fonction du employeId"+employeId);
			l.debug("Je viens de finir la recherche.");
			l.debug("je viens de commencer l'affectation les employe au departement");
			l.info("Out affecterEmployeADepartement  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans affecterEmployeADepartement : " + e);}
		// à ajouter? 
		deptRepoistory.save(depManagedEntity); 

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).get();


		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
		try {

			l.info("In authenticate : ");
			l.debug("Je vais lancer la recherche du depManaged en fonction du depId:"+depId);
			l.debug("Je vais lancer la recherche du employeManagedEntity en fonction du employeId"+employeId);
			l.debug("Je viens de finir la recherche.");
			l.debug("je viens de commencer l'desaffectation les employe au departement");
			l.info("Out affecterEmployeADepartement  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans desaffecterEmployeADepartement : " + e);}
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
	
		contratRepoistory.save(contrat);
		try {

			l.info("In authenticate : ");
			l.debug("Je vais lancer l'ajout du contrat:");
			l.debug("je viens de finir l'ajout du contrat");
			l.info("Out ajouterContrat  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans ajouterContrat : " + e);}
		return contrat.getReference();
		
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		try {

			l.info("In authenticate : ");
			l.debug("Je vais lancer la recherche du contrat en fonction du contratID:"+contratId);
			l.debug("Je vais lancer la recherche du employeManagedEntity en fonction du employeId"+employeId);
			l.debug("Je viens de finir la recherche.");
			l.debug("je viens de commencer l'affectation les contrat au employe");
			l.info("Out affecterContratAEmploye without errors.");
			}
			catch (Exception e) { l.error("Erreur dans affecterContratAEmploye : " + e);}
		

	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		try {

			l.info("In authenticate : ");
			l.debug("Je vais lancer l'ajout du contrat:");
			l.debug("je viens de finir l'ajout du contrat");
			l.info("Out ajouterContrat  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans ajouterContrat : " + e);}
		return employeManagedEntity.getPrenom();
	}
	 
	public void deleteEmployeById(int employeId)
	{
		Employe employe = employeRepository.findById(employeId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
		try {

			l.debug("Je vais lancer la recherche de l'employe en fn de l'id:"+employeId);
			l.debug("Je vais lancer l'supperession de l'employe");
			l.debug("je viens de finir la suppression");
			l.info("Out deleteEmployeById  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans deleteEmployeById : " + e);}
	}

	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);
		try {

			l.debug("Je vais lancer la recherche de l'employe en fn de l'id:"+contratId);
			l.debug("Je vais lancer l'supperession du contrat");
			l.debug("je viens de finir la suppression");
			l.info("Out deleteContratById  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans deleteContratById: " + e);}
	}

	public int getNombreEmployeJPQL() {
		try {
			l.info("in getNombreEmployeJPQL");
			l.debug("Je vais lancer l'affichage du nombre d'employe :");
			l.info("Out getNombreEmployeJPQL  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getNombreEmployeJPQL: " + e);}
		return employeRepository.countemp();
		
	}

	public List<String> getAllEmployeNamesJPQL() {
		try {
			l.info("in getNombreEmployeJPQL");
			l.debug("Je vais lancer l'affichage du nombre d'employe :");
			l.info("Out getNombreEmployeJPQL  without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getNombreEmployeJPQL: " + e);}
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		try {

			l.info("In getAllEmployeByEntreprise : ");
			l.debug("Je viens de lancer l'affichage des employe en fonction de l'entreprise.");
			l.debug("je viens de finir l'affichage des employe en fonction de l'entreprise:"+entreprise);
			l.info("Out getAllEmployeByEntreprise without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getAllEmployeByEntreprise : " + e); }
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
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
		try {

			l.info("In deleteAllContractJPQL : ");
			l.debug("Je viens de lancer la suppression des contract JPQL.");
			l.debug("je viens de finir la suppression des contract JPQL");
			l.info("Out deleteAllContratJPQL() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans deleteAllContratJPQL() : " + e); }
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		try {

			l.info("In getSalaireMoyenByEmployeIdJPQL : ");
			l.debug("Je viens de lancer l'affichage du salaire moyen en fonction de l'EmployeIDJPQL.");
			l.debug("je viens de finir l'affichage des salaire en fonction de l'EmployeIDJPQL:"+employeId);
			l.info("Out getSalaireByEmployeIdJPQL without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getSalaireByEmployeIdJPQL() : " + e); }
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		try {

			l.info("In getSalaireMoyenByDepartementId() : ");
			l.debug("Je viens de lancer l'affichage du salaire moyen en fonction de l'id du departement.");
			l.debug("je viens de finir l'affichage des salaire en fonction de departementID:"+departementId);
			l.info("Out getSalaireMoyenByDepartementId() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getSalaireMoyenByDepartementId() : " + e); }
		
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
		
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		try {

			l.info("In TimesheetsByMissionAndDate() : ");
			l.debug("Je vais afficher les Timesheets en fonction des missions et date.");
			l.info("Out getTimesheetsByMissionAndDate() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getTimesheetsByMissionAndDate() : " + e); }
		
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		try {

			l.info("In getAllPrducts() : ");
			l.debug("Je viens de lancer l'affichage les employés.");
			l.debug("je viens de finier l'affichage des employés");
			l.info("Out getAllPrducts() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans getAllEmployes() : " + e); }
		return (List<Employe>) employeRepository.findAll();
	}

}
