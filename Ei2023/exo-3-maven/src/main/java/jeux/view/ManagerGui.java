package jeux.view;

import javafx.scene.Scene;
import jfox.javafx.view.ManagerGuiAbstract;
import jfox.javafx.view.View;


public class ManagerGui extends ManagerGuiAbstract {
	
	
	// Actions

	@Override
	public void configureStage()  {
		
		// Choisit la vue à afficher
		showView( EnumView.NombreJeu );

	}


	@Override
	public Scene createScene( View view ) {
		Scene scene = new Scene( view.getRoot() );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
}