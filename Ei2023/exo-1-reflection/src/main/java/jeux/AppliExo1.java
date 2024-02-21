package jeux;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jeux.view.ManagerGui;


public class AppliExo1 extends Application {
	
	
	// Titre de la fenêtre
	private final String TITRE = "Jeux - Exo 1";

	
	// Champs
	
	private Context	context;
	
	
	// Actions
	
	@Override
	public final void start(Stage stage) {
		
		try {

			// Context
			context = new Context();

			// ManagerGui
	    	ManagerGui managerGui = context.getBean( ManagerGui.class );
	    	managerGui.setFactoryController( context::getBean );
			managerGui.setStage( stage );
			managerGui.configureStage();
			
			// Affiche le stage
			stage.setTitle( TITRE );
			stage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText( "Impossible de démarrer l'application." );
	        alert.showAndWait();
	        Platform.exit();
		}

	}
	
	@Override
	public final void stop() throws Exception {
		if (context != null ) {
			context.close();
		}
	}

	
	// Classe interne Main
	
	public static class Main {
		public static void main(String[] args) {
			Application.launch( AppliExo1.class, args);
			System.exit(0);
		}
	}

}
