package jeux.view;

import jfox.javafx.view.IEnumView;
import jfox.javafx.view.View;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	NombreJeu			( "ViewNombreJeu.fxml" ),
	NombreConfig		( "ViewNombreConfig.fxml" ),
	;

	
	// Champs
	
	private final String path;

	
	// Constructeurs
	
	EnumView( String path, boolean flagReuse ) {
		this.path = path; 
	}
	
	EnumView( String path ) {
		this( path, true );
	}

	
	// Getters & setters
	
	@Override
	public View getView() {
		return new View( this, path, false );
	}
}
