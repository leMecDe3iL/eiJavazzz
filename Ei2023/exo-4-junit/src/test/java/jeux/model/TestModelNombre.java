package jeux.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jeux.dao.DaoNombre;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestModelNombre {

	private static DaoNombre daoNombre;
	private static ModelNombre modelNombre;

	@BeforeAll
	public static void setUp() {
		// Initialisation du DAO
		daoNombre = new DaoNombre();
		daoNombre.init(15);
		// Initialisation du Model
		modelNombre = new ModelNombre();
		modelNombre.setDaoNombre(daoNombre);
	}

	@Test
	public void test1_jouer_trop_petit() {

		// Appel de la méthode jouer() avec une valeur inférieure au nombre mystère
		modelNombre.jouer("10");

		// Vérification que la réponse est -1
		assertEquals(-1, modelNombre.getReponse());

	}

	@Test
	public void test2_jouer_trop_grand() {

		modelNombre.jouer("19");
		assertEquals(1, modelNombre.getReponse());

	}

	@Test
	public void test3_jouer_gagnant() {

		modelNombre.jouer("15");
		assertEquals(0, modelNombre.getReponse());

	}
	@Test
	public void test4_jouer_perdant() {
		assertFalse(modelNombre.isPartieFinie());

        // Jouer 5 fois avec des mauvaises valeurs
        for (int i = 0; i < 5; i++) {
            modelNombre.jouer("1");
        }

        assertTrue(modelNombre.isPartieFinie());
   
	}
	@Test
	public void test5_jouer_exception() {
		assertThrows(NumberFormatException.class, () -> modelNombre.jouer("abc"));

	}

	@ParameterizedTest
	@CsvSource({ "2, -1", "50, 1", "15, 0" })
	public void test6_jouer_reponse(String valeur, int reponseAttendue) {
		// Appel de la méthode jouer() avec la valeur donnée
		modelNombre.jouer(valeur);
		// Vérification que la réponse est celle attendue
		assertEquals(reponseAttendue, modelNombre.getReponse());
	}

}