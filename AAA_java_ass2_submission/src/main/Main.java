package main;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Rooms.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {


	public void start(Stage stage) {
		
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			String viewerFxml = "scenebuilderWORK.fxml";
			AnchorPane page = (AnchorPane) fxmlLoader.load(this.getClass().getResource(viewerFxml).openStream());
			Scene scene = new Scene(page);
			stage.setScene(scene);
			
			//MVC pattern
			Model model = new Model();
			MVCView view = (MVCView) fxmlLoader.getController();	
			Controller controller = new Controller(model,view);
			
			stage.show();
			
        
		} catch (IOException ex) {
		   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		   System.exit(1);
		}
	}
	
    public static void main(String args[]) {
    	
     	launch(args);
     	System.exit(0);

    }
}

