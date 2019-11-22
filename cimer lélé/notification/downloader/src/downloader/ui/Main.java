package downloader.ui;

import downloader.fc.Downloader;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane pane=new BorderPane();
		VBox bars_container=new VBox();
		
		for(String url: getParameters().getRaw()) {
			Downloader downloader;
			try {
				downloader = new Downloader(url);
			}
			catch(RuntimeException e) {
				System.err.format("skipping %s %s\n", url, e);
				continue;
			}
			System.out.format("Downloading %s:", downloader);
			Thread t=new Thread(downloader);
			
			downloader.progressProperty().addListener((obs, o, n) -> {
				/*System.out.print(".");
				System.out.flush();*/
				// METTRE A JOUR PROGRESS BAR
			});
			
			String filename;
			try {
				filename = downloader.download();
			}
			catch(Exception e) {
				System.err.println("failed!");
				continue;
			}
			System.out.format("into %s\n", filename);
		}
		
	}  
	    
	    public static void main(String argv[]) {
			launch(argv);
		}

		
}


