package stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@TestMethodOrder(MethodOrderer.MethodName.class )
public class TestModelStock {
	
    private static List<Produit> stock;
    private static ModelStock model;
 

    @BeforeAll
    public static void setUp() {
        stock = new ArrayList<>();
        initialiserSock(stock);
        model = new ModelStock();
        model.ajouterStock(stock);
    }
	// Méthodes auxiliaires

	
	private static void initialiserSock( List<Produit> stock ) {
		stock.clear();
		stock.add( new Produit( 1, "Produit 1", 9.99, 25 ) );
		stock.add( new Produit( 2, "Produit 2", 7.50,  0 ) );
		stock.add( new Produit( 3, "Produit 3", 0.00, 17 ) );
		stock.add( new Produit( 4, "Produit 4", 8.88, 12 ) );
		System.out.println("Stock initialisé !" );
	}
	   
    
	
	
	// Méthodes de test
	@Test
	public void test1_getNombreProduits() {

		/*List<Produit> stock = new ArrayList<>();
		initialiserSock( stock );
		ModelStock model = new ModelStock();
		model.ajouterStock( stock );*/
		
		assertEquals(4, model.getNombreProduits());
			
	}

	@Test
	public void test2_ajouterProduit() {

		/*List<Produit> stock = new ArrayList<>();
		initialiserSock( stock );
		ModelStock model = new ModelStock();
		model.ajouterStock( stock );
		*/
		model.ajouterProduit( new Produit( 5, "Test Ajouter", 25.50, 4 ));
		
		assertEquals(5, model.getNombreProduits());

	}
	
	@Test
	public void test3_supprimerProduit_OK() {

		/*List<Produit> stock = new ArrayList<>();
		initialiserSock( stock );
		ModelStock model = new ModelStock();
		model.ajouterStock( stock );*/
		
		model.supprimerProduit( 2 );

		assertEquals(3, model.getNombreProduits());

	}
	
	@Test
	public void test4_supprimerProduit_KO() {

        assertThrows(IndexOutOfBoundsException.class, () -> model.supprimerProduit(7));
		
	}
	
	@Test
	public void test5_getProduit() {

		Produit produit = model.getProduit( 4 );
		assertNotNull(produit);
        assertEquals("Produit 4", produit.getNom());

	}
	
	
	@ParameterizedTest
    @CsvSource({ "1, 249.75", "2, 0.00", "3, 0.00", "4, 106.56" })
    public void test6_getValeurProduit(int id, Double valeur) {

//        model.getValeurProduit( id )
        Double actualValue = model.getValeurProduit(id);
        assertEquals(valeur, actualValue, 0.01, "La valeur du produit devrait être " + valeur);

    }
	
	
}