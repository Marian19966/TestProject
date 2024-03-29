package menu2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ControllerQuiz;
import dbUtil.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.PopupWindow;
import model.Question;
import model.QuestionBank;

public class ControllerMenu2 implements Initializable {
	
	   @FXML
	    private Button categoryComputer;

	    @FXML
	    private Button categroyProLan;

	    @FXML
	    private Button categoryJava;

	    @FXML
	    private Button categoryGermany;

	    @FXML
	    private Button categoryAnimal;

	    @FXML
	    private Label categoryLabel;

	    @FXML
	    private HBox statusHBox;
	    
	    @FXML
	    private Circle dbStatusLight;
	    
	    // Liste mit Fragen
	    ArrayList<Question> quizQuestionList = new ArrayList<>();
	    
		// Welche Kategorie soll im Quiz erscheinen
	    ArrayList<String> categoryList = new ArrayList<String>(); 
	    
		// Database
	    Database database = new Database();
	    QuestionBank questionBank = new QuestionBank();

	 // ************** Category **************
	    @FXML
	    void categroyComputerButton_Clicked(ActionEvent event) {
	    	
	    	Button button = (Button) event.getSource();
	    	getCategory(button.getText(), button);
	    }
	    
	    @FXML
	    void categroyProLanButton_Clicked(ActionEvent event) {

	    	Button button = (Button) event.getSource();
	    	getCategory(button.getText(), button);
	    }
	    
	    @FXML
	    void categoryJavaButton_Clicked(ActionEvent event) {

	    	Button button = (Button) event.getSource();
	    	getCategory(button.getText(), button);
	    }
	    
	    @FXML
	    void categoryGermanyButton_Clicked(ActionEvent event) {

	    	Button button = (Button) event.getSource();
	    	getCategory(button.getText(), button);
	    }
	    
	    @FXML
	    void categoryAnimalButton_Clicked(ActionEvent event) {

	    	Button button = (Button) event.getSource();
	    	getCategory(button.getText(), button);
	    }
	    
		// ************** HBox Status **************

	    @FXML
	    void cancelButton_Clicked(ActionEvent event) {
	    	categoryComputer.setDisable(false);
	    	categroyProLan.setDisable(false);
	    	categoryJava.setDisable(false);
	    	categoryGermany.setDisable(false);
	    	categoryAnimal.setDisable(false);
	    	
	    	statusHBox.setDisable(true);
	    	categoryLabel.setText("");
	    }

	   
	    @FXML
	    void okButton_Clicked(ActionEvent event) {

	    	for (String category : categoryList) {
				questionBank.loadCategoryQuestions(database.getStatement(), category);
			}
	    	
	    	quizQuestionList = questionBank.getQuestionsList();
	    	
	    	// Nur zum testen
//	    	for (Question question : quizQuestionList) {
//				System.out.println("ID " + question.getQuestion_id());
//				System.out.println("Frage " + question.getQuestion_text());
//				System.out.println("Antwort " + question.isQuestion_answer());
//				System.out.println("Erg�nzung " + question.getQuestion_complement());
//			}
	    	
			// Das Kategorie Fenster schlie�en
	    	Button okButton = (Button) event.getSource();
	    	Stage stage = (Stage) okButton.getScene().getWindow();
	    	stage.close();
	    	
	    	try {
				startQuiz();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
		// ************** initialize **************
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	statusHBox.setDisable(true);
	    	
	    	// Database Stuff
	    	boolean dbConntection = database.open();
	    	
	    	if (dbConntection) {
				dbStatusLight.setFill(Color.GREEN);
			} else {
				dbStatusLight.setFill(Color.RED);
				PopupWindow.display("Keine Verbindung m�glich \n Programm bitte neu starten");
			}
	    	
		}
	    
	 // ************** Methoden **************
	    private void getCategory(String categroy, Button button) {
			// Kategorie speichern
	    	categoryList.add(categroy);

	    	// Unten im Label den Namen anzeigen
	    	categoryLabel.setText(categoryLabel.getText() + " " + categroy);
	    	
			// Kategorie Button ausblenden
	    	button.setDisable(true);

			// Statusbox einsschalten
	    	statusHBox.setDisable(false);
	    }
	    
	    private void startQuiz() throws IOException {
	    	Stage stage3 = new Stage();
	    	stage3.setTitle("Quiz App");
	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader();
	    	
	    	Pane root = (Pane) fxmlLoader.load(getClass().getResource("/application/quiz.fxml").openStream());
	    	
	    	// Die ArrayList mit den Fragen muss zum ControllerQuiz, mit getController() erh�lt man die Controller Klasse aus der fxml Datei
	    	ControllerQuiz controllerQuiz = fxmlLoader.getController();
	    	controllerQuiz.setQuestions(quizQuestionList);
	    	
	    	Scene scene3 = new Scene(root);
	    	
	    	stage3.setScene(scene3);
	    	stage3.setResizable(false);
	    	stage3.show();
	    }
}
