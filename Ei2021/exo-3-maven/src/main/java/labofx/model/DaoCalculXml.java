package labofx.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class DaoCalculXml implements IDaoCalcul {
	
	
	// Constantes
	
	private static final String CHEMIN = "calcul.xml";

	
	// Actions
	
	public Calcul lire() throws IOException {
		System.out.println( getClass().getName() + "#lire()"  );
		try( var in = new FileInputStream( CHEMIN ); ) {
			Calcul calcul = new Calcul();
			Properties props = new Properties();
			props.loadFromXML( in );
			calcul.setDonnee( Double.valueOf( props.getProperty( "donnee" ) ) );
			calcul.setResultat( Double.valueOf( props.getProperty( "resultat" ) ) );
			calcul.setOperation( props.getProperty( "operation" ) );
	    	return calcul;
		} catch (final FileNotFoundException e) {
			throw e;
		} catch (final Exception e) {
			throw (IOException) e;
		}		
	}
	
	public void enregistrer( Calcul calcul ) {
		System.out.println( getClass().getName() + "#enregistrer()"  );
		try( var out = new FileOutputStream( CHEMIN ) ) {
			Properties props = new Properties();
			props.setProperty( "donnee", String.valueOf( calcul.getDonnee() ) );
			props.setProperty( "resultat", String.valueOf( calcul.getResultat() ) );
			props.setProperty( "operation", String.valueOf( calcul.getOperation() ) );
			props.storeToXML( out, null );
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
