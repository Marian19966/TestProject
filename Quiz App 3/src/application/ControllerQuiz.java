package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Question;

public class ControllerQuiz {

	  	@FXML
	    private Label questionLabel;

	    @FXML
	    private Button trueButton;

	    @FXML
	    private Button falseButton;

	    @FXML
	    private Label questionCountLabel;

	    @FXML
	    private Label scoreLabel;

	    @FXML
	    private Label bestScoreLabel;
	    
	    @FXML
	    private Label answerStatusLabel;
	    
	    @FXML
	    private HBox progressHboxbar;
	    
	    @FXML
	    private Button nächsteFrage_Button;
	    
	    @FXML
	    private HBox hBoxAnswerButtons;

	    
	    private ArrayList<Question> questionList = new ArrayList<>();
	    
	    // Fragen Zeug
	    private boolean pickedAnswer = false;
	    private int currentQuestionNumber = 0;
	    private int score = 0;
	    private int questionCount = 0; // Anzahl der Gesamtfragen
	    

	    @FXML
	    void answerButton_Clicked(ActionEvent event) {
	    	
	    	Button tappedButton = (Button) event.getSource();
	    	hBoxAnswerButtons.setDisable(true);
	    	
	    	// Bestimmen ob Nutzer stimmt oder stimmt nicht geklickt hat 
	    	if (tappedButton.getId().equals("trueButton")) {
				pickedAnswer = true;
			} else if (tappedButton.getId().equals("falseButton")) {
				pickedAnswer = false;
			}
	    	
	    	// 1. Antwort überprüfen
	    	checkAnswer();
	    	// 2. Eine Frage weiterspringen
	    	currentQuestionNumber = currentQuestionNumber + 1;
	    	
	    	// 3. nächste anzeigen
	    	
	    }
	    
	    @FXML
	    void nächsteFrageClicked(ActionEvent event) {
	    	hBoxAnswerButtons.setDisable(false);
	    	answerStatusLabel.setText("");
	    	nextQuestion();
	    }
	    
	    private void checkAnswer() {
	    	Question question = questionList.get(currentQuestionNumber);
	    	boolean answer = question.isQuestion_answer();
	    	String complement = question.getQuestion_complement();
	    	
	    	if(answer == pickedAnswer) {
	    		if(complement != null) {
	    			answerStatusLabel.setText("Richtig! \n" + complement);
	    			score++;
	    		}else {
	    			answerStatusLabel.setText("Richtig!");
	    			score++;
	    		}
	    	} else {
	    		if(complement != null) {
	    			answerStatusLabel.setText("Falsch! " + complement);
	    		}else {
	    			answerStatusLabel.setText("Falsch!");
	    		}
	    	}
	    }
	    
	    private void nextQuestion() {
	    	if(currentQuestionNumber <= questionCount -1) {
	    		questionLabel.setText(questionList.get(currentQuestionNumber).getQuestion_text());
	    		
	    		updateUi();
	    	} else {
	    		restart();
	    	}
	    }
	    
	    //update Userinterface
	    private void updateUi() {
	    	scoreLabel.setText("Score " + score);
	    	questionCountLabel.setText("" + (currentQuestionNumber + 1) + "/" + questionCount); //aktuelle Frage / Gesamtanzahl
	    	
	    	double progressHBoxBarWidth = (progressHboxbar.getWidth() / questionCount);
	    	progressHboxbar.getChildren().add(new Rectangle(progressHBoxBarWidth, 20, Color.BLUE));
	    }
	    
	    private void restart() {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Glückwunsch");
	    	alert.setHeaderText("Glückwunsch zum beenden des Quizes");
	    	alert.setContentText("Neustart?");
	    	alert.showAndWait();
	    	
	    	currentQuestionNumber = 0;
	    	score = 0;
	    	nextQuestion();
	    	progressHboxbar.getChildren().clear();
	    	answerStatusLabel.setText("");
	    }
	    
	    // Methoden
	    public void setQuestions(ArrayList<Question> qList) {
	    	questionList = qList;
	    	questionCount = questionList.size();
	    	System.out.println("Anzahl der Fragen: " + questionCount);
	    	
//	    	for (Question question : questionList) {
//				System.out.println("ID " + question.getQuestion_id());
//				System.out.println("Frage " + question.getQuestion_text());
//				System.out.println("Ergänzung " + question.getQuestion_complement());
//			}  	
	    	
	    	// Erste Frage anzeigen
	    	if (questionList != null) {
				String firstQuestion = questionList.get(0).getQuestion_text();
				questionLabel.setText(firstQuestion);
			}

	    	updateUi();
	    	
	    }
}