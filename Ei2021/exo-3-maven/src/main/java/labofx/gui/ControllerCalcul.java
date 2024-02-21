package labofx.gui;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import labofx.model.Calcul;
import labofx.model.ModelCalcul;


public class ControllerCalcul {
	
	
	// Composants de la vue
	
	@FXML
	private TextField		fieldDonnee;
	@FXML
	private Label			labelDonnee;
	@FXML
	private Label			labelResultat;
	@FXML
	private Label			labelOperation;
	@FXML
	private Button			buttonOperation1;
	@FXML
	private Button			buttonOperation2;

	
	// Autres champs

	private ModelCalcul		modelCalcul;

	
	// Injecteurs
	
	public void setModelCalcul(ModelCalcul modelCalcul) {
		this.modelCalcul = modelCalcul;
	}
	
	
	// Initialisation
	
	@FXML
	private void initialize() {
		
		Calcul calcul = modelCalcul.getCalcul();
		
		// Data binding
		fieldDonnee.textProperty().bindBidirectional( calcul.donneeProperty(), new ConverterDouble() );
		labelDonnee.textProperty().bind( new BindingDouble( calcul.donneeProperty() ) );
		labelResultat.textProperty().bind( new BindingDouble( calcul.resultatProperty() ) );
		labelOperation.textProperty().bind( calcul.operationProperty() );
		
	}
	
	
	// Actions
	
	@FXML
	private void doOperaton1() {
		configurerAffichage( 1 );
		modelCalcul.operation1();
	}	
	
	@FXML
	private void doOperaton2() {
		configurerAffichage( 2 );
		modelCalcul.operation2();
	}	
	
	@FXML
	private void doQuitter() {
		Platform.exit();
	}
	
	
	// Méthodes auxiliaires
	
	private void configurerAffichage( int op ) {
		fieldDonnee.setText( labelDonnee.getText() );
		buttonOperation1.setDefaultButton(false);
		buttonOperation2.setDefaultButton(false);
		switch ( op ) {
		case 1:
			buttonOperation1.setDefaultButton(true);
			break;
		case 2:
			buttonOperation2.setDefaultButton(true);
			break;
		}
	}
	
	
	// Classes auxiliaires
	
	public static class ConverterDouble extends StringConverter<Double> {

		private final NumberFormat	format = NumberFormat.getInstance();
		
		public ConverterDouble() {
			( (DecimalFormat) format ).applyPattern("#,##0.000");		
		}

		@Override
		public Double fromString( String source ) {
			try {
				source = source.replace( " ", "");
				return format.parse( source ).doubleValue();
			} catch ( Exception e ) {
				return null;
			}
		}

		@Override
		public String toString( Double source ) {
			if( source == null ) {
				return null;
			}
			return format.format( source );
		}
		
	}

	
	public class BindingDouble extends StringBinding {
		
		private final Property<Double>	prop;
		private final NumberFormat		format = NumberFormat.getInstance();

		public BindingDouble( Property<Double> prop ) {
			( (DecimalFormat) format ).applyPattern("#,##0.###");		
			this.prop = prop;
			bind( prop );
		}
		
		@Override
		public void dispose() {
			unbind( prop );
		}

		@Override
		protected String computeValue() {
			if ( prop.getValue() == null ) {
				return null;
			}
			return format.format( prop.getValue() );
		} 
		
	}

	
	// Méthode anonyme
	{
		System.out.println( "new " + this.getClass().getName() );
	}
	
}
