package jeux.composant;

import jeux.annotations.Init;
import jeux.annotations.Resource;
import jeux.annotations.Finish;

public class Composant1 {
	
	// Champs
	
	@Resource
	private Composant2	comp2;
	@Resource
	private Composant3	comp3;
	
	private boolean		closed;
	
	
	// Initialisaton et fermeture
	
	@Init
	public void initialiser() {
		comp2.agir();
		comp3.agir();
	}
	
	@Finish
	public void femer() {
		closed = true;
		if( ! ( comp3.isClosed() && comp3.isClosed() ) ) {
			throw new IllegalStateException();
		}
	}
	
	
	// Getters & Setters
	
	public Composant2 getComp2() {
		return comp2;
	}
	
	public Composant3 getComp3() {
		return comp3;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	
	// Actions
	
	public void agir() {
		comp2.agir();
		comp3.agir();
	}

}
