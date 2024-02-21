package jeux.composant;

import jeux.annotations.Init;
import jeux.annotations.Finish;


public class Composant3 {
	
	
	// Champs
	
	private boolean		closed;
	
	
	// Initialisaton et fermeture
	
	@Init
	public void initialiser() {
	}
	
	@Finish
	public void femer() {
		closed = true;
	}
	
	
	// Getters
	
	public boolean isClosed() {
		return closed;
	}
	
	
	// Actions
	
	public void agir() {
	}

}
