package labofx.model;

import java.io.FileWriter;
import java.time.LocalDateTime;


public class Logger {
	
	
	// Constantes

	private static final String	NAME = "maven.log";
	private static final String	ROOT = "src";

	
	// Champs
	
	private static String path = ROOT + "/" + NAME;
	
	
	// Getters & Setters
	
	public static void setPath( Class<?> clazz) {
		path = ROOT + "/"  + clazz.getPackageName().replace( '.', '/' ) + "/" + NAME;
	}
	
	
	// Actions

	public static void log() {

		try {
			
			String computer = System.getenv( "COMPUTERNAME" );
			String user = System.getProperty( "user.name" );
			
			var out = new FileWriter( path );
			out.write(  String.format( "%td/%<tm/%<tY %<tH:%<tM:%<tS  %s  %s %n", LocalDateTime.now(), computer, user ) );
			out.flush();
			out.close();

		} catch (Exception e) {
		}
	}

	public static void log( Class<?> clazz ) {
		setPath(clazz);
		log();
	}

}
