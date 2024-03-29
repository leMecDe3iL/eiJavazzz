package labofx;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import labofx.gui.ManagerGui;
import labofx.model.ModelCalcul;


public class AppliLaboFX extends Application {
	
	
	// Cahmps
	
	private AnnotationConfigApplicationContext	context;
	
	
	@Override
	public final void start( Stage stage ) throws IOException {

		context = new AnnotationConfigApplicationContext();
		context.register(ConfigLaboFX.class);
		context.refresh();
		
		ManagerGui managerGui = context.getBean(ManagerGui.class);
		managerGui.showViewCalcul(stage);
		
		// Initialise le ModelCalcul
		
		//ModelCalcul modelCalcul = context.getBean(ModelCalcul.class);
		//modelCalcul.init();
		
	}
	
	@Override
	public void stop() throws Exception {

		
		// Ferme le ModelCalcul
		//ModelCalcul modelCalcul = context.getBean(ModelCalcul.class);
		//modelCalcul.close();
		context.close();
	}

	
	// Classe auxiliaire
	
	public static class MainExo2 {
		public static void main(String[] args) {
			Application.launch( AppliLaboFX.class, args);
		}
	}

}
