package jfox.javafx.view;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.Parent;
import javafx.scene.Scene;
import jfox.javafx.util.UtilFX;


public class View {
	
	// Champs
	
	private URL			location;
	private boolean		flagReuse;
	private Parent		root;
	private ControllerAbstract	controller;
	private Scene		scene;
	private final List<Object>	objectsToClose = new ArrayList<>();

	
	// Constructeurs
	
	public View( URL location, boolean flagReuse ) {
		this.location = Objects.requireNonNull(location);
		this.flagReuse = flagReuse;
	}
	
	public View( URL location ) {
		this( location, true );
	}
	
	public View( IEnumView idview, String path, boolean flagReuse ) {
		Objects.nonNull(idview);
		Objects.nonNull(path);
		var location = idview.getClass().getResource(path);
		if( location == null ) {
			throw UtilFX.runtimeException( new FileNotFoundException( path ) );
		}
		this.location = location;
		this.flagReuse = flagReuse;
	}

	
	// Getters & setters
	
	public URL getLocation() {
		return location;
	}

	public boolean isFlagReuse() {
		return flagReuse;
	}
	
	public Parent getRoot() {
		return root;
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public ControllerAbstract getController() {
		return controller;
	}

	public void setController(ControllerAbstract controller) {
		objectsToClose.remove(this.controller);
		this.controller = controller;
		addObjectToClose(controller);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void addObjectToClose( Object obj ) {
		if ( obj != null ) {
			objectsToClose.add( obj );
		}
	}

	public List<Object> getObjectsToClose() {
		return objectsToClose;
	}

}
