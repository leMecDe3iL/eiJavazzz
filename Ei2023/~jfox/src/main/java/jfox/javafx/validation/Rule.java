package jfox.javafx.validation;

import java.util.function.Predicate;


@SuppressWarnings( { "rawtypes", "unchecked"} )
public class Rule {
	
	
	// Fields
	
	private String			message;
	private Predicate		checker;
	
	
	// Constructor

	public Rule( String message, Predicate checker ) {
		this.message = message;
		this.checker = checker;
	}

	
	// Actions
	
	public String check( Object	value ) {
		if ( checker.test( value ) ) {
			return message;
		}
		return null;
	}


}
