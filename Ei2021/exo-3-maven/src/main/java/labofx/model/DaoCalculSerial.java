package labofx.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DaoCalculSerial implements IDaoCalcul {
	
	
	// Constantes
	
	private static final String CHEMIN = "calcul.serial";

	
	// Actions
	
	public Calcul lire() throws IOException {
		System.out.println( getClass().getName() + "#lire()"  );
		try( var in = new ObjectInputStream( new FileInputStream( CHEMIN ) ); ) {
			Calcul calcul = new Calcul();
			calcul.setDonnee( (Double) in.readObject() );
			calcul.setResultat( (Double) in.readObject() );
			calcul.setOperation( (String) in.readObject() );
	    	return calcul;
		} catch (final IOException e) {
			throw e;
		} catch (final Exception e) {
			throw new IOException(e);
		}		
	}
	
	public void enregistrer( Calcul calcul ) {
		System.out.println( getClass().getName() + "#enregistrer()"  );
		try( var out = new ObjectOutputStream( new FileOutputStream( CHEMIN ) ) ) {
			out.writeObject( calcul.getDonnee() );
			out.writeObject( calcul.getResultat() );
			out.writeObject( calcul.getOperation() );
			out.flush();
		} catch (final RuntimeException e) {
			throw e;
		} catch (final Exception e) {
			throw new RuntimeException( e );
		}		
	}

	
	// Méthode anonyme
	{
		System.out.println( "new " + this.getClass().getName() );
	}
	
}
