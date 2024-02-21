package jeux.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jeux.annotations.Resource;
import jeux.model.ModelNombre;
import jfox.javafx.view.ControllerAbstract;


public class ControllerNombreJeu extends ControllerAbstract {
	
	
	// Composants de la vue
	
	@FXML
	private Label		labelMessage;
	
	@FXML
	private Label		labelNbEssaisRestants;

	@FXML
	private TextField	textFieldProposition;
	
	@FXML
	private Button		buttonJouer;
	
	
	// Autre champs
	
	@Resource
	private ManagerGui	managerGui;
	@Resource
	private ModelNombre	modelNombre;


	// Initialisation du Controller
	
	public void initialize() {
		
		// Initialise les données du modèle
		modelNombre.retrouverPartie();
		
		// Data binding
		labelMessage.textProperty().bind( modelNombre.messageProperty() );
		labelNbEssaisRestants.textProperty().bind( modelNombre.nbEssaisRestantsProperty() );
		textFieldProposition.textProperty().bindBidirectional( modelNombre.propositionProperty() );
		buttonJouer.disableProperty().bind( modelNombre.flagPartieFinieProperty() );
	}
	
	
	// Actions
	
	@FXML
	private void doNouvellePartie() {
		modelNombre.nouvellePartie();
	}
	
	@FXML
	private void doJouer() {
		modelNombre.jouer();
	}
	
	public void doTricher() {
		modelNombre.tricher();
	}
	
	@FXML
	private void doConfig() {
		modelNombre.preparerconfig();
		managerGui.showView( EnumView.NombreConfig );;;
	}
	
	@FXML
	private void doQuitter() {
		managerGui.exit();
	}
	

	// Gestion des évènements
	
	@FXML
	private void onClick( MouseEvent event) {
		// Test double-clic
		if( event.getClickCount() == 2  && event.getButton() == MouseButton.PRIMARY ) {
			doTricher();
		}
	}

	@FXML
	private void onClickBtnMenu( MouseEvent event) {
		if ( event.isControlDown() ) {
			managerGui.showDialog( EnumView.NombreJeu ) ;
		}
	}
	
}
