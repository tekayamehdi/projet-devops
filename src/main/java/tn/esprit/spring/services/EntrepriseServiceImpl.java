package tn.esprit.spring.services;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

			l.info("je vais affecterDepartement() : ");
			l.debug("Je vais verifier la disponibilite des departements a entreprise.");
			Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
			if(value.isPresent())
			{Entreprise entrepriseManagedEntity = value.get();
			
			l.debug("je viens de trouver l'entreprise" + entrepriseManagedEntity);
			l.debug("je vais lancer la recherche du departement par id ");
			Optional<Departement> value1 = deptRepoistory.findById(depId);
			if(value1.isPresent())
			{Departement depManagedEntity=value1.get();
				
				
			l.debug("je viens de trouver le departement" + depManagedEntity);
			l.debug("je vais lancer l'update de l'entreprise et l'enregistré");	
			
					depManagedEntity.setEntreprise(entrepriseManagedEntity);
					deptRepoistory.save(depManagedEntity);
					
			l.debug("je viens de faire l'update de l'entreprise et l'enregistré");	
			l.info("fin de la methode affectation departement a entreprise");
			}}}
			catch (Exception e) { l.error("Erreur d'affecter un Departement a une Entreprise() : " + e); }
			

	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		try {
		l.info("lancer la methode get AllDepartmentNamesByEntreprise");
		l.debug("lancer la recherche de l entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) 
		
		{Entreprise entrepriseManagedEntity= value.get();
			
		l.debug("je viens de trouver l'entreprise" +entrepriseManagedEntity);
		List<String> depNames = new ArrayList<>();
		l.debug("je vais lancer la boucle sur tous les departements et ajouter le nom du departement au tableau depNames");
		
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		l.debug("je viens de remplir le tableau depNames");
		l.info("fin de la methode getAllDepartmentsNamesByEntreprise");
		return depNames;
		}}
		catch (Exception e)
		{l.error("l'entreprise n'existe pas");
		l.info("fin de la methode getAllDepartmentsNamesByEntreprise");
		}
		return new ArrayList<>();
		
	}
	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		try {
		l.info("lancer la methode deleteEntrepriseById");
		l.debug("je vais lancer la methode deleteEntrepriseById");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise entreprise=value.get();
			entrepriseRepoistory.delete(entreprise);	
			
			l.debug("je viens de finir la deleteEntrepriseById");
			l.info("fin de la methode deleteEntrepriseById");	
		}}
		catch (Exception e) {l.error("l'entreprise n'existe pas");
		l.info("fin de la methode deleteEntrepriseById");	
			
		}		
	}


	@Transactional
	public void deleteDepartementById(int depId) {
		try {
		l.info("je vais commencer lancement la methode delete department by id");
		l.debug("je vais lancer la methode delete departement by id");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if (value.isPresent()) {
			Departement dep=value.get();
		deptRepoistory.delete(dep);
		
		l.debug("je viens de finir methode delete departement by id");
		l.info("fin de la methode delete department by id");
		}}
		
		catch (Exception e) {
			l.error("le departement n'existe pas");
			l.info("fin de la methode delete department by id");
		}
		}


	public Entreprise getEntrepriseById(int entrepriseId) {
		try {
		l.info("lancement de la methode get entreprise by id");
		l.debug("je vais lancer la recherche de l'entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent=value.get();
			
		l.debug("je viens de trouver l'entreprise par id"+ent);
		l.info("fin de la methode get entreprise by id");
		l.info("hello world");
		 return ent;
		}}
		
		catch(Exception e) {l.error("l'entreprise n'existe pas");}
		l.info("fin de la methode get entreprise by id"); 
		return null;}
	
	
}
