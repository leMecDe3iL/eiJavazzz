package labofx.model;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ModelCalcul {

	
	// Champs
	
	private final Calcul	calcul = new Calcul();
	
	private IDaoCalcul 		daoCalcul;
	
	
	// Injecteurs
	
	public void setDaoCalcul(IDaoCalcul daoCalcul) {
		this.daoCalcul = daoCalcul;
	}
	
	
	// Initialisation et fermeture

	public void init() {
		try {
			calcul.update( new Calcul() );
			calcul.update( daoCalcul.lire() );
		} catch (FileNotFoundException e) {
			calcul.setOperation( "Fichier absent" );
		} catch ( IOException e) {
			calcul.setOperation( "Erreur lecture fichier" );
		}
	}
	
	public void close() {
		daoCalcul.enregistrer( calcul );
	}
	
	
	// Getters & Setters
	
	public Calcul getCalcul() {
		return calcul;
	}
	
	
	// Actions
	
	public void operation1() {
		if ( calcul.getDonnee() != null ) {
			double resultat = 1.;
			double donnee = calcul.getDonnee().doubleValue() ;
			double inf = Math.pow( 10,  (int) Math.log10( donnee )  );
			if ( donnee <= inf * 1.5 ) {
				resultat = inf;
			} else 	if ( donnee >= inf * 5 ) {
				resultat = inf * 10;
			}
			calcul.setResultat( Math.log10( resultat ) );
			calcul.setOperation( "Puissance de 10 ?" );
		}
	}
	
	public void operation2() {
		if ( calcul.getDonnee() != null ) {
			double e = calcul.getDonnee().intValue();
			double d = calcul.getDonnee().doubleValue() % 1;
			d = Math.round( d );
			calcul.setResultat( ( e + d ) % 2 );
			calcul.setOperation( "Paire ou impair ?" );
		}
	}

	
	// Méthode anonyme
	{
		System.out.println( "new " + this.getClass().getName() );
	}

}
