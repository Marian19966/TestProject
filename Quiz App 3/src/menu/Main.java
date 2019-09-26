package menu;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/menu/menu.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			//System.out.println(Database.penis);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
