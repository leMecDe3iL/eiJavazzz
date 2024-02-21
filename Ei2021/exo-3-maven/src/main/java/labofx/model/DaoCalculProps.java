package labofx.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


public class DaoCalculProps implements IDaoCalcul {
	
	
	// Constantes

	@Inject
	private static final String CHEMIN = "calcul.properties";
	
	
	// Initilalisation
	
	@PostConstruct
	public void init() {
	}

	
	// Actions
	
	@Override
	public Calcul lire() throws IOException {
		System.out.println( getClass().getName() + "#lire()"  );
		try( var reader = new FileReader( CHEMIN ); ) {
			Calcul calcul = new Calcul();
			Properties props = new Properties();
			props.load( reader );
			String valeur;
			valeur = props.getProperty( "donnee" );
			if ( valeur != null && ! valeur.equals( "null" ) ) {
				calcul.setDonnee( Double.valueOf( valeur ) );
			}
			valeur = props.getProperty( "resultat" );
			if ( valeur != null && ! valeur.equals( "null" ) ) {
				calcul.setResultat( Double.valueOf( valeur ) );
			}
			valeur = props.getProperty( "operation" );
			if ( valeur != null && ! valeur.equals( "null" ) ) {
				calcul.setOperation( valeur );
			}
	    	return calcul;
		} catch (final IOException e) {
			throw e;
		} catch (final Exception e) {
			throw new IOException(e);
		}		
	}
	
	@Override
	public void enregistrer( Calcul calcul ) {
		System.out.println( getClass().getName() + "#enregistrer()"  );
		try( var writer = new FileWriter( CHEMIN ) ) {
			Properties props = new Properties();
			props.setProperty( "donnee", String.valueOf( calcul.getDonnee() ) );
			props.setProperty( "resultat", String.valueOf( calcul.getResultat() ) );
			props.setProperty( "operation", String.valueOf( calcul.getOperation() ) );
			props.store( writer, null );
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
