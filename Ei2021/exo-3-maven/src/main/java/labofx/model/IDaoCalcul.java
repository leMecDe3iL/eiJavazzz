package labofx.model;

import java.io.IOException;


public interface IDaoCalcul {

	Calcul lire() throws IOException;

	void enregistrer(Calcul modelCalcul);

}