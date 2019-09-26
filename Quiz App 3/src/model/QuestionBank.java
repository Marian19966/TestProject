package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionBank {

	// Fragen aus der Datenbank laden
	// Fragen Objekte erstellen
	// Frage Objekte in eine Liste laden
	// Liste zurückgeben
	
	// Database Stuff
	private ArrayList<Question> questionsList = new ArrayList<>();
	
	public void loadCategoryQuestions(Statement statement, String categoryTable) {
		String QUERY_DATA_FROM_TABLE = "SELECT * FROM " + categoryTable; // SQL Anfrage
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(QUERY_DATA_FROM_TABLE);
			
			while(resultSet.next()) {
				int question_id = resultSet.getInt(1);
				String question_text = resultSet.getString(2);
				
				boolean question_answer;
				if(resultSet.getInt(3) == 1) {
					question_answer = true;
				} else {
					question_answer = false;
				}
				
				String question_complement = resultSet.getString(4);
				
				Question question = new Question(question_id, question_text, question_answer, question_complement);
				
				questionsList.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Question> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(ArrayList<Question> questionsList) {
		this.questionsList = questionsList;
	}
	
	
	
	
}
