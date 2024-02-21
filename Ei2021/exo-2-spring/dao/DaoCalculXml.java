package labofx.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;


public class DaoCalculXml implements IDaoCalcul {
	
	
	// Constantes
	
	private static final String CHEMIN = "calcul.xml";

	
	// Actions
	
	@Override
	public Calcul lire() {
		System.out.println( getClass().getName() + "#lire()"  );
		Calcul calcul = new Calcul();
		try( var in = new FileInputStream( CHEMIN ); ) {
			Properties props = new Properties();
			props.loadFromXML( in );
			calcul.setDonnee( Double.valueOf( props.getProperty( "donnee" ) ) );
			calcul.setResultat( Double.valueOf( props.getProperty( "resultat" ) ) );
			calcul.setOperation( props.getProperty( "operation" ) );
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
		try( var out = new FileOutputStream( CHEMIN ) ) {
			Properties props = new Properties();
			props.setProperty( "donnee", calcul.getDonnee().toString() );
			props.setProperty( "resultat", calcul.getResultat().toString() );
			props.setProperty( "operation", calcul.getOperation() );
			props.storeToXML( out, null );
		} catch (final Exception e) {
			System.out.println( "Erreur lors de l'ecriture du fichier " + CHEMIN);
		}		
	}

	
	// Méthode anonyme
	{
		System.out.println( "new " + this.getClass().getName() );
	}

}
