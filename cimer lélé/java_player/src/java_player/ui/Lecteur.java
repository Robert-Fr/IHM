package java_player.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Lecteur extends BorderPane{
	
	Lecteur(){
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
		Button b2= new Button(":=");
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
	
	
}
