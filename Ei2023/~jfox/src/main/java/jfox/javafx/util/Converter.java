package jfox.javafx.util;

import java.text.ParseException;

import javafx.util.StringConverter;
import jfox.localization.BundleMessages;


public abstract class Converter<T> extends StringConverter<T> {

	public final static String MSG_DEFAULT = BundleMessages.getString( "validation.invalid.value" );
	
	// Champs
	private String 	message;
	private boolean	flagParseError;
	private String	outputText;
	
	
	// Construteurs
	
	public Converter( String message ) {
		if ( message != null ) {
			this.message = message;
		} else {
			this.message = MSG_DEFAULT;
		}
	}
	
	
	// Getters & Setters
	
	public String getMessage() {
		return message;
	}
	
	public String getOutputText() {
		return outputText;
	}
	
	public boolean hasParseError() {
		return flagParseError;
	}
	
	
	// Actions
	
	@Override
	public String toString( T object) {
		if ( object == null ) {
			outputText = null;
		} else {
			outputText = format( object ); 
		}
		return outputText;
	}

	
	@Override
	public T fromString( String string ) {

		flagParseError = false;
		
		if ( string != null ) {
			string = string.replace( "\u0020", "" ).replace( "\u202f", "" );
		}
		
		if ( string == null || string.isEmpty() ) {
			outputText = null;
			return null;
		}

		try {
			T value = parse( string );
			toString( value );
			return value;
		} catch ( Exception e ) {
			flagParseError = true;
			return null;
		}
	}
	
	
	// Méthodes abstraites
	
	protected abstract String format( T object ); 
	
	protected abstract T parse( String string ) throws ParseException; 
	
	public abstract int compare( String string, T ref ) throws ParseException; 
	
}
