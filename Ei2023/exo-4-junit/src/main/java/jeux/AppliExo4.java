package jeux;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jeux.view.ManagerGui;


public class AppliExo4 extends Application {
	
	
	// Titre de la fenêtre
	private final String TITRE = "Jeux - Exo 4";

	
	// Champs
	
	private ClassPathXmlApplicationContext	context;
	
	
	// Actions
	
	@Override
	public final void start(Stage stage) {
		
		try {

			// ManagerGui
	    	var managerGui = ( context = new ClassPathXmlApplicationContext( "context-mock.xml" ) ).getBean( ManagerGui.class);
	    	managerGui.setFactoryController( type -> context.getBean(type) );
			
			// Affiche le stage
			managerGui.setStage( stage );
			managerGui.configureStage();
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
			Application.launch( AppliExo4.class, args);
			System.exit(0);
		}
	}

}
