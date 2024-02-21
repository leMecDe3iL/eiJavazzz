package analyseur;

import java.time.LocalDate;
import java.time.Period;

public class Personne {
	
	
	// Champs
	private String		nom;
	private String		prenom;
	private LocalDate	dateNaissance;
	private int			age;
	@Deprecated
	private boolean		majeur;
	
	
	// Constructeurs
	
	public Personne() {
	}
	
	public Personne(String nom, String prenom, LocalDate dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		calculerAge();
	}
	
	
	// Getters & Setters
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	
	public int getAge() {
		return age;
	}

	@Deprecated
	public boolean isMajeur() {
		return majeur;
	}

	
	// Méthodes auxilaires
	
	private void calculerAge() {
		if( dateNaissance == null ) {
			age = 0;
		} else {
			age = Period.between( dateNaissance, LocalDate.now() ).getYears();
		}
		majeur = age >= 18; 
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private void actualiserAge( LocalDate dateNaissance ) {
		this.dateNaissance = dateNaissance;
		calculerAge();
	}
	
	
	// toString()

	@Override
	public String toString() {
		String date;
		if (dateNaissance == null) {
			date = "null";
		} else {
			date = String.format( "%td/%<tm/%<tY", dateNaissance );
		}
		return String.format( "%s %s %s %d %b", nom, prenom, date, age, majeur );
	}


}
