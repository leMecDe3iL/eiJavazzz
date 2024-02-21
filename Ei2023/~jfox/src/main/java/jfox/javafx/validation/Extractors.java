package jfox.javafx.validation;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.util.Callback;


public class Extractors {
	
	// Fields

    private static final List<ObservableExtractor> extractors = FXCollections.observableArrayList(); 

    private static final List<ValueExtractor> valueExtractors = FXCollections.observableArrayList();

    
    // Constructor
    
    private Extractors() {
    }

    
    
    // Actions
    
    public static void addObservableExtractor( Predicate<Control> test, Callback<Control, ObservableValue<?>> extract ) {
        extractors.add(0, new ObservableExtractor(test, extract));
    }
    
    public static void addValueExtractor(Predicate<Node> test, Callback<Node, Object> extractor) {
        valueExtractors.add(0, new ValueExtractor(test, extractor));
    }
	

    public static final Callback<Control, ObservableValue<?>> getObservableExtractor(final Control c) {
        for( var e: extractors ) {
            if ( e.applicability.test(c)) return e.extraction;
        }
        throw new IllegalArgumentException( "No ObservableExtractor found for type " + c.getClass()  );  
    }
	
    public static final Callback<Node, Object> getValueExtractor(final Node n) {
        for( var e: valueExtractors ) {
            if ( e.applicability.test(n)) return e.extraction;
        }
        throw new IllegalArgumentException( "No ValueExtractor found for type " + n.getClass()  );  
    }
    
    public static Object getValue(Node n) {
    	for( ValueExtractor nve: valueExtractors ) {
            if ( nve.applicability.test(n)) return nve.extraction.call(n);
        }
        return null;
    }



    // Helper classes
    
	private static class ObservableExtractor {

        public final Predicate<Control> applicability;
		public final Callback<Control, ObservableValue<?>> extraction;

        public ObservableExtractor( Predicate<Control> applicability, Callback<Control, ObservableValue<?>> extraction ) {
            this.applicability = Objects.requireNonNull(applicability);
            this.extraction    = Objects.requireNonNull(extraction);
        }

    }
    
    
    private static class ValueExtractor {

        public final Predicate<Node> applicability;
		public final Callback<Node, Object> extraction;

        public ValueExtractor( Predicate<Node> applicability, Callback<Node, Object> extraction ) {
            this.applicability = Objects.requireNonNull(applicability);
            this.extraction    = Objects.requireNonNull(extraction);
        }

    }
    
    
    // Static methodes

    static {
        addObservableExtractor( c -> c instanceof Labeled, c -> ((Labeled)c).textProperty());
        addObservableExtractor( c -> c instanceof TextInputControl, c -> ((TextInputControl)c).textProperty());
        addObservableExtractor( c -> c instanceof ComboBox && !((ComboBox<?>)c).isEditable(), c -> ((ComboBox<?>)c).valueProperty());
        addObservableExtractor( c -> c instanceof ComboBox && ((ComboBox<?>)c).isEditable(),  c -> ((ComboBox<?>)c).getEditor().textProperty() );
        addObservableExtractor( c -> c instanceof ChoiceBox,        c -> ((ChoiceBox<?>)c).valueProperty());
        addObservableExtractor( c -> c instanceof CheckBox,         c -> ((CheckBox)c).selectedProperty());
        addObservableExtractor( c -> c instanceof Slider,           c -> ((Slider)c).valueProperty());
        addObservableExtractor( c -> c instanceof ColorPicker,      c -> ((ColorPicker)c).valueProperty());
        addObservableExtractor( c -> c instanceof DatePicker,       c -> ((DatePicker)c).valueProperty());
//        addObservableExtractor( c -> c instanceof DatePicker,       c -> ((DatePicker)c).getEditor().textProperty() );

        addObservableExtractor( c -> c instanceof ListView,         c -> ((ListView<?>)c).itemsProperty());
        addObservableExtractor( c -> c instanceof TableView,        c -> ((TableView<?>)c).itemsProperty());

        // FIXME: How to listen for TreeView changes???
        //addObservableValueExtractor( c -> c instanceof TreeView,         c -> ((TreeView<?>)c).Property());
    }
    
    static {
        addValueExtractor( n -> n instanceof Labeled, c -> ((Labeled) c).getText());
        addValueExtractor( n -> n instanceof TextInputControl, ta -> ((TextInputControl)ta).getText());
        addValueExtractor( n -> n instanceof CheckBox,         cb -> ((CheckBox)cb).isSelected());
        addValueExtractor( n -> n instanceof ChoiceBox,        cb -> ((ChoiceBox<?>)cb).getValue());
        addValueExtractor( n -> n instanceof ComboBox && !((ComboBox<?>)n).isEditable(), cb -> ((ComboBox<?>)cb).getValue());
        addValueExtractor( n -> n instanceof ComboBox && ((ComboBox<?>)n).isEditable(),  cb -> ((ComboBox<?>)cb).getEditor().getText() );
        addValueExtractor( n -> n instanceof DatePicker,       dp -> ((DatePicker)dp).getValue());
//        addValueExtractor( n -> n instanceof DatePicker,       dp -> ((DatePicker)dp).getEditor().getText() );
        addValueExtractor( n -> n instanceof RadioButton,      rb -> ((RadioButton)rb).isSelected());
        addValueExtractor( n -> n instanceof Slider,           sl -> ((Slider)sl).getValue());
        
        addValueExtractor( n -> n instanceof ListView, lv -> {
            MultipleSelectionModel<?> sm = ((ListView<?>)lv).getSelectionModel();
            return sm.getSelectionMode() == SelectionMode.MULTIPLE ? sm.getSelectedItems() : sm.getSelectedItem();
        });
        addValueExtractor( n -> n instanceof TreeView, tv -> {
            MultipleSelectionModel<?> sm = ((TreeView<?>)tv).getSelectionModel();
            return sm.getSelectionMode() == SelectionMode.MULTIPLE ? sm.getSelectedItems() : sm.getSelectedItem();
        });
        addValueExtractor( n -> n instanceof TableView, tv -> {
            MultipleSelectionModel<?> sm = ((TableView<?>)tv).getSelectionModel();
            return sm.getSelectionMode() == SelectionMode.MULTIPLE ? sm.getSelectedItems() : sm.getSelectedItem();
        });
        addValueExtractor( n -> n instanceof TreeTableView, tv -> {
            MultipleSelectionModel<?> sm = ((TreeTableView<?>)tv).getSelectionModel();
            return sm.getSelectionMode() == SelectionMode.MULTIPLE ? sm.getSelectedItems() : sm.getSelectedItem();
        });
    }


}