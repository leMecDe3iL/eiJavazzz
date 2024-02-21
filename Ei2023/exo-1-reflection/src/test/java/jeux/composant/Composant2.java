package jeux.composant;

import jeux.annotations.Init;
import jeux.annotations.Resource;
import jeux.annotations.Finish;

public class Composant2 {
	
	
	// Champs
	
	@Resource
	private Composant3	comp3;
	
	private boolean		closed;
	
	
	// Initialisaton et fermeture
	
	@Init
	public void initialiser() {
		comp3.agir();
	}
	
	@Finish
	public void femer() {
		closed = true;
		if( ! comp3.isClosed() ) {
			throw new IllegalStateException();
		}
	}
	
	
	// Getters & Setters
	
	public Composant3 getComp3() {
		return comp3;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	
	// Actions
	
	public void agir() {
		comp3.agir();
	}

}
