package labofx.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Component;

@Component
public class DaoCalculSerial implements IDaoCalcul {
	
	
	// Constantes
	
	private static final String CHEMIN = "calcul.serial";

	
	// Actions
	
	@Override
	public Calcul lire() {
		System.out.println( getClass().getName() + "#lire()"  );
		Calcul calcul = new Calcul();
		try( var in = new ObjectInputStream( new FileInputStream( CHEMIN ) ); ) {
			calcul.setDonnee( (Double) in.readObject() );
			calcul.setResultat( (Double) in.readObject() );
			calcul.setOperation( (String) in.readObject() );
		} catch (final FileNotFoundException e) {
			calcul.setOperation( "Fichier absent" );
		} catch (final Exception e) {
			calcul.setOperation( "Erreur lecture fichier" );
		}		
    	return calcul;
	}
	
	@Override
	public void enregistrer( Calcul calcul ) {
		System.out.println( getClass().getName() + "#enregistrer()"  );
		try( var out = new ObjectOutputStream( new FileOutputStream( CHEMIN ) ) ) {
			out.writeObject( calcul.getDonnee() );
			out.writeObject( calcul.getResultat() );
			out.writeObject( calcul.getOperation() );
			out.flush();
		} catch (final Exception e) {
			System.out.println( "Erreur lors de l'ecriture du fichier " + CHEMIN);
		}		
	}

	
	// Méthode anonyme
	{
		System.out.println( "new " + this.getClass().getName() );
	}
	
}
