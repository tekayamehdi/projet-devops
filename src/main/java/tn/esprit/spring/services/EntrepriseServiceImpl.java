package tn.esprit.spring.services;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {

			l.info("In ajouterEntreprise() : ");
			l.debug("Je vais verifier la disponibilite des Entreprises.");
			l.debug("Je viens de voir la dispo des entreprise. ");
			l.debug("Je viens de finir l'opération.");
			l.info("Out ajouterEntreprise() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans ajouterEntreprise() : " + e); }
			
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try {

			l.info("In ajouterDepartement() : ");
			l.debug("Je vais verifier la disponibilite des departements.");
			l.debug("Je viens de voir la dispo des departement. ");
			l.debug("Je viens de finir l'opération.");
			l.info("Out ajouterDepartement() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans ajouterDepartement() : " + e); }
			
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		try {

			l.info("In ajouterDepartement() : ");
			l.debug("Je vais verifier la disponibilite des departements a entreprise.");
			l.debug("Je viens de voir la dispo des departement. ");
			l.debug("Je viens de finir l'opération.");
			l.info("Out affecterDepartementAEntreprise() without errors.");
			}
			catch (Exception e) { l.error("Erreur dans affecterDepartementAEntreprise() : " + e); }
			
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();	
	}

}
