/**
 * Model object for the Question data.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */
package is.hi.hbv601.restServer.model;

public class Question {
	private final long id;
    private final String question;
    private final long question_number;
    private long total_questions;
    private String question_type;
    
	public Question(long id, String question, long question_number, long total_questions, String question_type) {
		this.id = id;
		this.question = question;
		this.question_number = question_number;
		this.total_questions = total_questions;
		this.question_type = question_type;
	}

	public long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public long getQuestion_number() {
		return question_number;
	}

	public long getTotal_questions() {
		return total_questions;
	}

	public String getQuestion_type() {
		return question_type;
	}
}
