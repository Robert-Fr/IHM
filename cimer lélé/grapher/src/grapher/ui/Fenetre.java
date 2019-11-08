package grapher.ui;


import java.util.ArrayList;
import java.util.Optional;

import grapher.fc.FunctionFactory;
import javafx.application.Application.Parameters;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Fenetre extends SplitPane {
	GrapherCanvas gc;
	BorderPane bp;
	String fct;
	
	public Fenetre(Parameters params) {
		this.gc = new GrapherCanvas();
		bp = new BorderPane();
		
		for(String param: params.getRaw()) {
			gc.functions.add(FunctionFactory.createFunction(param));
		}
		
		//on créer tout les composant de la partie gauche de la fenetre 
		Button plus = new Button(" + ");
		Button moins = new Button(" - ");
		ToolBar tb = new ToolBar(plus,moins);
		ListView<String> lv = new ListView<String>();
		lv.getItems().addAll(params.getRaw());
		lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
	        @Override
	        public void handle(MouseEvent event) {
	        	for (String f : lv.getSelectionModel().getSelectedItems()) {
	        		if (!gc.bold.contains(f) ) { 
	        			gc.bold.add(FunctionFactory.createFunction(f));
	        		}else {
	        			gc.bold.remove(FunctionFactory.createFunction(f));
	        		}
	        		
	        	}
	            gc.redraw();
	            gc.bold.clear();
	        }
	    });
		
		
		EventHandler<ActionEvent> button_handler = new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e ) {
				if(e.getSource()==plus) {
					TextInputDialog dialog= new TextInputDialog();
					dialog.setContentText("nouvelle expression : ");
					dialog.setHeaderText("Expression");
					dialog.setTitle("Expression");
					Optional<String> result= dialog.showAndWait();
					if(!result.isPresent()) {return; }
					String expression= result.get();
					try {
						gc.functions.add(FunctionFactory.createFunction(expression));
						lv.getItems().add(expression);
						gc.redraw();
					} catch (RuntimeException re) {
						Alert alert = new Alert(AlertType.ERROR,"\"" + expression +"\"\n" + "Expression non valide !");
						alert.showAndWait();
					}
				}
				else {
					//supprimer la fonction : récupérer l'indice dasn la liste view si y'en a une de sélectionnée, supprimer et supprimer du gc
					System.out.println("moins");
				}
			}
		};
		plus.addEventHandler(ActionEvent.ANY, button_handler);
		moins.addEventHandler(ActionEvent.ANY, button_handler);
		
		
		
		bp.setCenter(lv);
		bp.setBottom(tb);
		this.getItems().addAll(bp,this.gc);
	}
}
