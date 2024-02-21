package labofx;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import labofx.gui.ManagerGui;
import labofx.model.Logger;


public class AppliLaboFX extends Application {
	
	
	// Champs
	
	private ClassPathXmlApplicationContext context;
	
	
	// Initialisation et fermeture
	
	@Override
	public final void start( Stage stage ) throws IOException {
		context = new ClassPathXmlApplicationContext( "context-serial.xml" );
		context.getBean( ManagerGui.class ).showViewCalcul(stage);
	}
	
	@Override
	public void stop() throws Exception {
		context.close();
		Logger.log();
	}

	
	// Classe auxiliaire

	public static class MainExo3 {
		public static void main(String[] args) {
			Application.launch( AppliLaboFX.class, args);
		}
	}

}
