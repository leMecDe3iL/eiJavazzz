package jeux;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jeux.view.ManagerGui;

@Configuration
@Import(ConfigExplo.class) // Remplacer ConfigExplo par ConfigExplo.class
public class AppliExo2 extends Application {
	
	
	// Titre de la fenêtre
	private final String TITRE = "Jeux - Exo 2";

	
	// Champs
	
	private AnnotationConfigApplicationContext	context;
	
	
	// Actions
	
	@Override
	public final void start(Stage stage) {
		
		try {

			// Context
            context = new AnnotationConfigApplicationContext();
             // Enregistrer dans ce contexte la classe de configuration 
            context.register(ConfigSimple.class);

            // Exécuter la méthode refresh() du contexte
            context.refresh();
            
            
			// ManagerGui
	    	ManagerGui managerGui = context.getBean(ManagerGui.class);
	    	
	    	//Qestion 
	    	managerGui.setFactoryController( context::getBean );
			
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
		if (context != null && context.isRunning() ) {
			context.close();
		}
	}

	
	// Classe interne Main
	
	public static class Main {
		public static void main(String[] args) {
			Application.launch( AppliExo2.class, args);
			System.exit(0);
		}
	}

}