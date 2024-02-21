package jeux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jeux.composant.Composant1;
import jeux.composant.Composant2;
import jeux.composant.Composant3;
import jeux.composant.Composant4;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContext {

	
	// Méthodes de test
	
	@Test @Order(1)
	public void testChercher() throws Throwable {
		
		var context = new Context();
		var comp1 = new Composant1();
		var comp2 = new Composant2();
		var comp3 = new Composant3();
		ajouter( context, comp1 );
		ajouter( context, comp2 );
		ajouter( context, comp3 );
		
		assertSame( comp1, context.find( Composant1.class ));
		assertSame( comp2, context.find( Composant2.class ));
		assertSame( comp3, context.find( Composant3.class ));
	}

	
	@Test @Order(2)
	public void testInstancier() throws Throwable {

		var context = new Context();
		var comp1 = context.create( Composant1.class );
		var comp2 = context.create( Composant2.class );
		var comp3 = context.create( Composant3.class );
		
		assertSame( Composant1.class, comp1.getClass()  );
		assertSame( Composant2.class, comp2.getClass()  );
		assertSame( Composant3.class, comp3.getClass()  );
		
		
	}

	
	@Test @Order(3)
	public void testInjecter() throws Throwable {

		var context = new Context();
		var comp1 = new Composant1();
		var comp2 = new Composant2();
		var comp3 = new Composant3();
		ajouter( context, comp1 );
		ajouter( context, comp2 );
		ajouter( context, comp3 );

		context.inject( comp1 );
		context.inject( comp2 );
		context.inject( comp3 );
		
		assertSame( comp2, comp1.getComp2() );
		assertSame( comp3, comp1.getComp3() );
		assertSame( comp3, comp2.getComp3() );
		
	}

	
	@Test @Order(4)
	public void testInitialiser() throws Throwable {
		var context = new Context();
		var comp4 = new Composant4();
		var exception = assertThrows( InvocationTargetException.class, () -> context.start( comp4 ) );
		assertSame( RuntimeException.class, exception.getCause().getClass()  );
		assertEquals("initialiser", exception.getCause().getMessage());		
	}

	
	@Test @Order(5)
	public void testFermer() throws Throwable {
		var context = new Context();
		var comp4 = new Composant4();
		var exception = assertThrows( InvocationTargetException.class, () -> context.stop( comp4 ) );
		assertSame( RuntimeException.class, exception.getCause().getClass()  );
		assertEquals("fermer", exception.getCause().getMessage());		
	}

	
	@Test @Order(6)
	public void testgetBean() throws Throwable {

		var context = new Context();
		var comp1 = context.getBean( Composant1.class );
		var comp2 = context.getBean( Composant2.class );
		var comp3 = context.getBean( Composant3.class );
		
		assertSame( comp2, comp1.getComp2() );
		assertSame( comp3, comp1.getComp3() );
		assertSame( comp3, comp2.getComp3() );
	}

	
	@Test @Order(7)
	public void testClose() throws Throwable {
		
		var context = new Context();
		var comp1 = new Composant1();
		var comp2 = new Composant2();
		var comp3 = new Composant3();
		ajouter( context, comp1 );
		ajouter( context, comp2 );
		ajouter( context, comp3 );
		injecter( comp1, comp2, "comp2" );
		injecter( comp1, comp3, "comp3" );
		injecter( comp2, comp3, "comp3" );

		context.close();
		assertTrue( comp1.isClosed() );
		assertTrue( comp2.isClosed() );
		assertTrue( comp3.isClosed() );
	}
		
	
	
	
	
	// Méthodes auxiliaires
	
	private void ajouter( Context context, Object objet ) throws Throwable {
		var field = Context.class.getDeclaredField( "beans" );
		field.setAccessible(true);
		var method = field.getType().getMethod( "add", Object.class );
		method.invoke( field.get(context), objet  );
	}
	
	private void injecter( Object obj1, Object obj2, String champ ) throws Throwable {
		var field = obj1.getClass().getDeclaredField( champ );
		field.setAccessible(true);
		field.set( obj1, obj2 );
	}
	

}
