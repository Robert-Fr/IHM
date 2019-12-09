package downloader.ui;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import downloader.fc.Downloader;

public class Main extends Application {

	double WIDTH = 600;
	double HEIGHT = 600;
	double MAX_WIDTH = 600;
	double MAX_HEIGHT = 600;

	@Override
	public void start(Stage stage) throws Exception {

		
		// ScrollPane pour pouvoir "scroll" si on télécharge beaucoup de lien en meme temps
		ScrollPane root = new ScrollPane();

		// VBox
		VBox v = new VBox();

		// ToolBar
		ToolBar dialog = new ToolBar();
		
		// SearchBar
		TextInputDialog text = new TextInputDialog("Entrez votre URL ici svp");
		
		//Button "Add"
		Button add = new Button("Add");
		add.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
			
				//On declre un nouveau dowloader que 'lon lance dans un autre fil d'execution
				Downloader new_d = new Downloader(text.getEditor().getText());
				Thread th=new Thread(new_d);
				th.start();
				
				// ToolBar
				ToolBar t = new ToolBar();
				
				// Label
				Label l = new Label(text.getEditor().getText() + " ");
				
				// ProgressBar
				ProgressBar p = new ProgressBar();
				
				// Button
				Button b = new Button("Pause");
				
				new_d.progressProperty().addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
							Number newValue) {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								p.setProgress((double)newValue);
							}
						});
						
					}

				});
				
				b.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if(b.getText().equals("Pause")) {
							b.setText("Reprendre");
							p.setVisible(false);
							//il faut faire s'endormir le thread .. Partie non implémenté, ca ne marche pas
							/*try {
								th.wait();
							}
							catch (InterruptedException Ie) {
								System.out.println(Ie.getMessage());
							}*/
						}
						else {
							b.setText("Pause");
							p.setVisible(true);
							//il faut reveiller le thread.. Partie non implémenté, ca ne marche pas
							//this.notifyAll();
						}						
					}
				});
				
				Button a = new Button("Annuler");
				a.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						v.getChildren().remove(t);
						//il faut stopper le thread
						th.interrupt();
					}
				});
				
				// on ajoute les 4 éléments propre a un téléchargement a la toolbar
				t.getItems().add(l);
				t.getItems().add(p);
				t.getItems().add(b);
				t.getItems().add(a);

				// on met le téléchargement dans la liste des téléchargements
				v.getChildren().add(t);

			}
		});

		//
		
		dialog.getItems().add(text.getEditor());
		dialog.getItems().add(add);

		v.getChildren().add(dialog);
		root.setContent(v);

		// Stage
		stage.setTitle("Downloader");
		stage.setScene(new Scene(root));
		stage.setWidth(WIDTH);
		stage.setHeight(HEIGHT);
		stage.setMaxWidth(MAX_WIDTH);
		stage.setMaxHeight(MAX_HEIGHT);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

