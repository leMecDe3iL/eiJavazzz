package jeux.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DtoNombre implements Serializable {

	
	// Enumératon
	public static enum EnumReponse { DEBUT, ERREUR, TROP_PETIT, TROP_GRAND, GAGNE, PERDU }

	
	// Champs
	
	private int			idJoueur;
	
	private int			valeurMaxi;
	private int			nbEssaisMaxi;
	private int			nbEssaisRestants;
	private EnumReponse reponse;

	private int			nombreMystere;
	private int			borneInf;
	private int			borneSup;

	
	// Getters & Setters
	
	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public int getValeurMaxi() {
		return valeurMaxi;
	}

	public void setValeurMaxi(int valeurMaxi) {
		this.valeurMaxi = valeurMaxi;
	}

	public int getNbEssaisMaxi() {
		return nbEssaisMaxi;
	}

	public void setNbEssaisMaxi(int nbEssaisMaxi) {
		this.nbEssaisMaxi = nbEssaisMaxi;
	}

	public int getNbEssaisRestants() {
		return nbEssaisRestants;
	}

	public void setNbEssaisRestants(int nbEssaisRestants) {
		this.nbEssaisRestants = nbEssaisRestants;
	}

	public EnumReponse getReponse() {
		return reponse;
	}

	public void setReponse(EnumReponse reponse) {
		this.reponse = reponse;
	}

	public int getNombreMystere() {
		return nombreMystere;
	}

	public void setNombreMystere(int nombreMystere) {
		this.nombreMystere = nombreMystere;
	}

	public int getBorneInf() {
		return borneInf;
	}

	public void setBorneInf(int borneInf) {
		this.borneInf = borneInf;
	}

	public int getBorneSup() {
		return borneSup;
	}

	public void setBorneSup(int borneSup) {
		this.borneSup = borneSup;
	}

}
