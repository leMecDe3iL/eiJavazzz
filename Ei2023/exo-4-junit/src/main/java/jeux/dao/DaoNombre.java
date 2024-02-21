package jeux.dao;

import static jeux.dto.DtoNombre.EnumReponse.DEBUT;

import java.util.HashMap;
import java.util.Map;

import jeux.dto.DtoNombre;


public class DaoNombre implements IDaoNombre {

	
	// Champs
	
	private Map<Integer, DtoNombre> mapData = new HashMap<>();

	
	// Initialisation
	
	public void init() {
		initialiserDonnees();
	}
	
	public void init( int nombre ) {
		initialiserDonnees();
		setNombreMystere(nombre);
	}

	
	// Actions
	
	@Override
	public void inserer( DtoNombre nombre ) {
//		mapData.put( nombre.getIdJoueur(), nombre);
	}

	
	@Override
	public void modifier( DtoNombre nombre ) {
//		mapData.put( nombre.getIdJoueur(), nombre);
	}
	
	
	@Override
	public DtoNombre retrouver( int idJoueur ) {
		try {
			return (DtoNombre) mapData.get(idJoueur).clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException( e );
		}
		
	}
	
	
	// Méthodes auxiliaires

	private void initialiserDonnees() {
		
		DtoNombre nombre;
		
		nombre = new DtoNombre();
		nombre.setIdJoueur( 1 );
		nombre.setValeurMaxi( 32 );
		nombre.setNbEssaisMaxi( 5 );
		nombre.setNbEssaisRestants( 3 );
		nombre.setNombreMystere( 19 );
		nombre.setBorneInf(16);
		nombre.setBorneSup(24);
//		inserer(nombre);
		mapData.put( nombre.getIdJoueur(), nombre);
		
	}
	
	
	public void setNombreMystere( int nombreMystere ) {
		var courant = mapData.get( 1 );
		courant.setNombreMystere(nombreMystere);
		courant.setNbEssaisRestants( courant.getNbEssaisMaxi() );
		courant.setReponse( DEBUT );
		courant.setBorneInf( 0 );
		courant.setBorneSup(  courant.getValeurMaxi() );
	}
	
	
	// Méthode anonyme
	
	{
		System.out.println( "new " + this.getClass().getSimpleName() );
	}

}
