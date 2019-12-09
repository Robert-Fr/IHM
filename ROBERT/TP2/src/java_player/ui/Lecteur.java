package java_player.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lecteur extends BorderPane{
	
	TreeTableView liste_musique;
	
	Lecteur(Stage stage){
		//on construit par le bas :
		//partie gauche :
		HBox play_side_top=new HBox();
		Button play=new Button(">");
		Button back=new Button("<<");
		Button next=new Button(">>");
		play_side_top.getChildren().addAll(back,play,next);
		
		HBox play_side_bot=new HBox();
		Button stop=new Button("||");
		Button backward=new Button("|<");
		Button forward=new Button(">|");
		play_side_bot.getChildren().addAll(backward,stop,forward);
		
		VBox play_side=new VBox();
		play_side.getChildren().addAll(play_side_top,play_side_bot);
		
		this.setLeft(play_side);
		
		
		
		//partie droite :
		HBox liste=new HBox();
		Button b1=new Button("|||");
		ToggleButton b2= new ToggleButton(":=");
		//declaration de la liste de musique :
		liste_musique=new TreeTableView<String>();
		TreeTableColumn<String,String> col_nom= new TreeTableColumn("nom");
		TreeTableColumn<String,String> col_auteur= new TreeTableColumn("auteur");
		TreeTableColumn<String,String> col_duree= new TreeTableColumn("durée");
		liste_musique.getColumns().setAll(col_nom,col_auteur,col_duree);
		
		
		
		b2.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean old_v, Boolean new_v) {
				if (new_v) {
					affichage_liste_musique();
					stage.setHeight(500);
					
					stage.heightProperty().addListener(new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> arg0, Number old_v, Number new_v) {
						if ((double)new_v < 200) {
							b2.setSelected(false);
							masquage_liste_musique();
						}
							
						}
						
					});
					
				}
				else {
					masquage_liste_musique();
					stage.setHeight(200);
				}
				
			}

			
		});
		
		liste.getChildren().addAll(b1,b2);
		
		Slider volume_slider=new Slider();
		
		BorderPane volume=new BorderPane();
		volume.setRight(liste);
		volume.setLeft(volume_slider);
		
		BorderPane lect_labels=new BorderPane();
		Label l1=new Label("Lecteur Multimedia VLC");
		Label l2=new Label("00:00");
		lect_labels.setRight(l2);
		lect_labels.setLeft(l1);
		
		Slider time_slider= new Slider();
		
		BorderPane lecture=new BorderPane();
		lecture.setCenter(time_slider);
		lecture.setTop(lect_labels);
		
		VBox view_side=new VBox();
		view_side.getChildren().addAll(lecture,volume);
		
		this.setRight(view_side);
		
	}
	public void masquage_liste_musique() {
		this.setBottom(null);
		
	}

	public void affichage_liste_musique() {
		this.setBottom(liste_musique);
	}
	
}
