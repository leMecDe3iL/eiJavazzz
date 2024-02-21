package stock;

import java.util.ArrayList;
import java.util.List;


public class ModelStock {
	
	
	// Champs
	
	private List<Produit>	stock	= new ArrayList<>();
	
	
	// Setters
	
	public void ajouterStock(List<Produit> stock) {
		this.stock.addAll( stock );
	}
	
	
	// Getters

	public Produit getProduit( int id ) {
		for( Produit item : stock ) {
			if( item.getId() == id ) {
				return item;
			}
		}
		return null;
	}

	public Produit getProduit( String nom ) {
		for( Produit item : stock ) {
			if( item.getNom().equals(nom) ) {
				return item;
			}
		}
		return null;
	}
	
	public double getValeurProduit( int id ) {
		Produit produit = getProduit( id );
		if ( produit == null ) {
			return Double.NaN;
		} else {
			return produit.getPrix() * produit.getQuantite();
		}
	}
	
	public int getNombreProduits() {
		return stock.size();
	}
	
	public boolean isStockVide() {
		return stock.isEmpty();
	}
	
	public double getValeurStock() {
		double valeur = 0;
		for( Produit item : stock ) {
			valeur += item.getPrix() * item.getQuantite();
		}
		return valeur;
	}
	
	
	
	// Actions
	
	public void ajouterProduit( Produit produit ) {
		stock.add( produit );
	}
	
	public void supprimerProduit( int rang ) {
		stock.remove( rang );
	}
	
	
	// Méthode anonyme
	
	{
		System.out.println( "new " + this.getClass().getSimpleName() );
	}
	

}
