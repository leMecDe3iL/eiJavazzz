package jeux.dao;

import jeux.dto.DtoNombre;


public interface IDaoNombre {

	void	inserer( DtoNombre nombre );

	void	modifier( DtoNombre nombre );

	DtoNombre 	retrouver( int idJoueur );

}
