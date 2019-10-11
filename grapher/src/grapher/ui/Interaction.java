package grapher.ui;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Interaction implements EventHandler<MouseEvent> {
	//on declare les attributs
	enum State {IDLE,ZOOM_OR_TRANSLATE,TRANSLATE,DEZOOM_OR_SELECT,SELECT};
	State curr_state;
	Point2D p;
	GrapherCanvas g;
	//constructeur
	Interaction(GrapherCanvas gc){
		g=gc;
		curr_state=State.IDLE;
	}
	@Override
	public void handle(MouseEvent e) {
		//selon l'état dans lequel on est
		switch (curr_state) {
			
			case IDLE:
				switch(e.getEventType().getName()) {//selon l'évenement on à une transition dans notre machine à état
				case "MOUSE_PRESSED":
					System.out.println("clic effectué");
					if(e.getButton()== MouseButton.PRIMARY) { //clic gauche
						curr_state=State.ZOOM_OR_TRANSLATE;
					}
					if(e.getButton()== MouseButton.SECONDARY) { //clic droit
						curr_state=State.DEZOOM_OR_SELECT;
					}
					break;
				default : //pas de transition dans les autres cas
					break;
				}
				break;
				
				
			case ZOOM_OR_TRANSLATE:
				switch(e.getEventType().getName()) {
				case "MOUSE_RELEASED":
					if(e.getButton()== MouseButton.PRIMARY) { //clic gauche
						curr_state=State.ZOOM_OR_TRANSLATE;
					}
					break;
				case "MOVED": //trouver le vrai nom
					curr_state=State.TRANSLATE;
					//executer du code sur le grapherCanvas
				}
				break;
				
				
			case TRANSLATE:
				switch(e.getEventType().getName()) {
				case :
					break;
				}
				break;
				
				
			case DEZOOM_OR_SELECT:
				switch(e.getEventType().getName()) {
				case :
					break;
				}
				break;
				break;
				
				
			case SELECT:
				switch(e.getEventType().getName()) {
				case :
					break;
				}
				break;
			
		}
		
	}

}
