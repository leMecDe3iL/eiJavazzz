package labofx.gui;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ManagerGui {

	
	// Champs
	
	private ApplicationContext context;
	
	
	// Injecteur
	
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
	
	// Actions
	
	public void showViewCalcul( Stage stage ) throws IOException {

		FXMLLoader loader = new FXMLLoader( getClass().getResource("/labofx/gui/ViewCalcul.fxml"));
		loader.setControllerFactory( context::getBean );
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle( "LaboFX" );
		stage.show();
	}
	
	
}
