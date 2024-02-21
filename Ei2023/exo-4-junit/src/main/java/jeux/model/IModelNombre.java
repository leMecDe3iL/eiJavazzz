package jeux.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;


public interface IModelNombre {

	ObjectProperty<Integer> valeurMaxiProperty();

	ObjectProperty<Integer> nbEssaisMaxiProperty();

	StringProperty nbEssaisRestantsProperty();

	StringProperty messageProperty();

	StringProperty propositionProperty();

	BooleanProperty flagPartieFinieProperty();

	void preparerconfig();

	void enregistrerconfig();

	void nouvellePartie();

	void retrouverPartie();

	void jouer( String valeur );

	void tricher();

}