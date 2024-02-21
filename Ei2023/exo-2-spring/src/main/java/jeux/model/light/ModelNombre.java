package jeux.model.light;

import static jeux.dto.DtoNombre.EnumReponse.DEBUT;
import static jeux.dto.DtoNombre.EnumReponse.ERREUR;
import static jeux.dto.DtoNombre.EnumReponse.GAGNE;
import static jeux.dto.DtoNombre.EnumReponse.PERDU;
import static jeux.dto.DtoNombre.EnumReponse.TROP_GRAND;
import static jeux.dto.DtoNombre.EnumReponse.TROP_PETIT;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.springframework.stereotype.Component;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;
import jeux.dto.DtoNombre;
import jeux.model.IModelNombre;

@Component
public class ModelNombre implements IModelNombre {
	
	
	// Champs pour la vue Config
	
	private final ObjectProperty<Integer>	valeurMaxiVue		= new SimpleObjectProperty<>();
	private final ObjectProperty<Integer>	nbEssaisMaxiVue		= new SimpleObjectProperty<>();

	
	// Champs pour la vue Jeu
	
	private final ObjectProperty<Integer>	idJoueur	= new SimpleObjectProperty<>(1);
	private final StringProperty	messageVue			= new SimpleStringProperty();
	private final StringProperty	nbEssaisRestantsVue	= new SimpleStringProperty();
	private final StringProperty	propositionVue		= new SimpleStringProperty();
	private final BooleanProperty	flagPartieFinieVue	= new SimpleBooleanProperty();

	
	// Autres champs
	
	private DtoNombre	courant;
	private int			proposition;
	private final Random random = new Random();
	
	
	// Getters
	
	@Override
	public final ObjectProperty<Integer> valeurMaxiProperty() {
		return this.valeurMaxiVue;
	}

	@Override
	public final ObjectProperty<Integer> nbEssaisMaxiProperty() {
		return this.nbEssaisMaxiVue;
	}

	@Override
	public final StringProperty nbEssaisRestantsProperty() {
		return this.nbEssaisRestantsVue;
	}

	@Override
	public final StringProperty messageProperty() {
		return this.messageVue;
	}

	@Override
	public final StringProperty propositionProperty() {
		return this.propositionVue;
	}
	
	@Override
	public final BooleanProperty flagPartieFinieProperty() {
		return this.flagPartieFinieVue;
	}
	
	
	// Actions
	
	@Override
	public void preparerconfig() {
		valeurMaxiVue.set( courant.getValeurMaxi() );
		nbEssaisMaxiVue.set( courant.getNbEssaisMaxi() );
	}
	
	@Override
	public void enregistrerconfig() {
		courant.setValeurMaxi( valeurMaxiVue.get() );
		courant.setNbEssaisMaxi( nbEssaisMaxiVue.get() );
		nouvellePartie();
	}

	
	@Override
	public void nouvellePartie() {
		courant.setNombreMystere( random.nextInt( courant.getValeurMaxi() + 1 ) );
		courant.setNbEssaisRestants( courant.getNbEssaisMaxi() );
		courant.setReponse( DEBUT );
		courant.setBorneInf( 0 );
		courant.setBorneSup(  courant.getValeurMaxi() );
		actualiserVueJeu();
	}

	@Override
	public void retrouverPartie() {
		courant = new DtoNombre();
		courant.setIdJoueur( idJoueur.getValue() );
		courant.setNbEssaisMaxi( 5 );
		courant.setValeurMaxi( 32 );
		nouvellePartie();
	}
	
	
	@Override
	public void jouer() {
		
		try {
			proposition = Integer.parseInt( propositionVue.get() );
		} catch (NumberFormatException e) {
			courant.setReponse( ERREUR );
			actualiserVueJeu();		
			return;
		}
	
		courant.setNbEssaisRestants( courant.getNbEssaisRestants() - 1 );
		if ( proposition == courant.getNombreMystere()  ) {
			courant.setReponse(GAGNE);
		} else {
			if ( courant.getNbEssaisRestants() <= 0 ) {
				courant.setReponse(PERDU);
			}else if ( proposition < courant.getNombreMystere()  ) {
				if ( courant.getBorneInf() <= proposition ) {
					courant.setBorneInf( proposition + 1 );
				}
				courant.setReponse(TROP_PETIT);
			} else {
				if ( courant.getBorneSup() >= proposition ) {
					courant.setBorneSup( proposition - 1 );
				}
				courant.setReponse(TROP_GRAND);
			}
		}
		actualiserVueJeu();		
		
	}
	
	
	@Override
	public void tricher() {
		
		var message = messageVue.get();

		// Rétablit l'affichage normal au bout de 500 millisecondes 
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(500),
		        ae -> messageVue.set( message )) );
		timeline.play();
		
		// Affiche le message de triche
		messageVue.set( "La solution est " + courant.getNombreMystere() ) ;
		
	}
	
	
	// Méthodes auxiliaires
	
	private void actualiserVueJeu() {
		switch ( courant.getReponse() ) {
		case DEBUT :
			messageVue.set( String.format( "Trouvez un nombre entre %d et %d", courant.getBorneInf(), courant.getBorneSup(), courant.getNbEssaisRestants() ) );
			break;
		case ERREUR :
			messageVue.set( "Valeur incorreecte.\nRecommencez !" );
			break;
		case TROP_PETIT :
			messageVue.set( String.format( "%d est trop petit. !", proposition ) );
			break;
		case TROP_GRAND :
			messageVue.set( String.format( "%d est trop grand. !", proposition ) );
			break;
		case GAGNE :
			messageVue.set( "Gagné !\nLe nombre mystère était " + courant.getNombreMystere() );
			break;
		case PERDU :
			messageVue.set( "Perdu !\nLe nombre mystère était " + courant.getNombreMystere() );
			break;
		}
		
		if ( courant.getNbEssaisRestants() < 2) {
			nbEssaisRestantsVue.set( courant.getNbEssaisRestants() + " essai restant" );
		} else {
			nbEssaisRestantsVue.set( courant.getNbEssaisRestants() + " essais restants" );
		}
		flagPartieFinieVue.set( courant.getReponse() == GAGNE || courant.getReponse() == PERDU );
		if ( ! flagPartieFinieVue.get() ) {
			propositionVue.set(null);
		}
		
	}
	
}
