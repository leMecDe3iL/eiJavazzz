package jeux.composant;

import jeux.annotations.Init;
import jeux.annotations.Finish;


public class Composant4 {
	
	
	// Initialisaton et fermeture
	
	@Init
	public void initialiser() {
		throw new RuntimeException( "initialiser" );
	}
	
	@Finish
	public void femer() {
		throw new RuntimeException( "fermer" );
	}
	
	
	// Actions
	
	public void agir() {
	}

}
