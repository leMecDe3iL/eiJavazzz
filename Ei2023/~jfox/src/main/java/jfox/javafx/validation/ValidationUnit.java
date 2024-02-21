package jfox.javafx.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;


public class ValidationUnit {
	
	
	// Fields
	
	public static final String CSS_ERROR = "validation-error";
	public static final String CSS_OK = "validation-ok";
	public static final String CSS_TOOLTIP_ERROR = "tooltip-error";
	
	private final Control		target;
	private final Callback<Node, Object>	extractor;
	private final List<Rule>	rules	= new ArrayList<>();
	
	private String		message;
	private Tooltip		toolTipDefault;
	private Tooltip		toolTipError;
	
	
	// Constructor
	
	public ValidationUnit( Control control, Callback<Node, Object> extractor) {
		this.target = control;
		this.extractor = extractor;
		toolTipDefault = control.getTooltip();
		toolTipError = new Tooltip( "Error" );
		toolTipError.getStyleClass().add( CSS_TOOLTIP_ERROR );
		if (! target.getStyleClass().contains( CSS_OK ) ) {
			target.getStyleClass().add( CSS_OK );
		}
		
	}
	
	// Getters & Setters
	
	public String getMessage() {
		return message;
	}
	
	public boolean isInvalid() {
		return message != null;
	}

	
	// Actions

	public void addRule( Rule rule ) {
		Objects.requireNonNull( rule );
		try {
			if( rule.check(null)!= null ) {
				rules.add(0, rule);
				return;
			}
		} catch (NullPointerException e) {
		}
		rules.add( rule  );
	}
	
	public String validate( boolean flagDisabled ) {
		message = null;
		if ( ! flagDisabled ) {
			for ( var rule : rules ) {
				try {
					String message = rule.check( extractor.call(target) );
					if ( message != null ) {
						this.message = message;
						toolTipError.setText(message);
						setTooltip(target, toolTipError);
						target.getStyleClass().remove(  CSS_OK  );
						if (! target.getStyleClass().contains( CSS_ERROR ) ) {
							target.getStyleClass().add( CSS_ERROR );
						}
						return message;
					}
				} catch (NullPointerException e) {
				}
			}
		}
		if (! target.getStyleClass().contains( CSS_OK ) ) {
			target.getStyleClass().add( CSS_OK );
		}
		target.getStyleClass().remove(  CSS_ERROR  );
		setTooltip(target, toolTipDefault);
		return null;
	}
	
	
	// Helper methods
	
	private void setTooltip( Control target, Tooltip toolTip ) {
		try {
			target.setTooltip(toolTip);
		} catch (Exception e) {
			if ( target.getParent() instanceof Control ) {
				((Control) target.getParent()).setTooltip(toolTip);
			}
		}
	}
	
}
