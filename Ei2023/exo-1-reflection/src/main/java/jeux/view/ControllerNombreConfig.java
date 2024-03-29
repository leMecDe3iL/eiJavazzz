package jeux.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import jeux.annotations.Resource;
import jeux.model.ModelNombre;
import jfox.javafx.util.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;


public class ControllerNombreConfig extends ControllerAbstract {
	
	
	// Composants de la vue

	@FXML
	private TextField	txfValeurMaxi;
	@FXML
	private TextField	txfNbEssaisMaxi;
	@FXML
	private Button		btnValider;
	
	
	// Autre champs
	
	@Resource
	private ManagerGui	managerGui;
	@Resource
	private ModelNombre	modelNombre;


	// Initialisation du Controller
	
	public void initialize() {
		
		// Data binding
		
		bindBidirectional( txfValeurMaxi, modelNombre.valeurMaxiProperty(), new ConverterInteger() );
		bindBidirectional( txfNbEssaisMaxi, modelNombre.nbEssaisMaxiProperty(), new ConverterInteger() );
		
		validator.addRuleMinValue(txfValeurMaxi, 1);
		validator.addRuleMinValue(txfNbEssaisMaxi, 1);
		btnValider.disableProperty().bind( validator.invalidProperty() );
	}
	
	
	// Actions
	
	@FXML
	private void doValider() {
		modelNombre.enregistrerconfig();
		managerGui.showView( EnumView.NombreJeu ) ;
	}

	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.NombreJeu ) ;
	}

	
}
