/**
 * Model object for the FetchQuestionWrapper data. The wrapper is
 * to combine quiz id, question number and phone_id.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.pubquiz.model;

public class FetchQuestionWrapper {
	private long quiz_id;
	private long question_number;
	private String phone_id;
	
	public FetchQuestionWrapper(long quiz_id, long question_number, String phone_id) {
		this.quiz_id = quiz_id;
		this.question_number = question_number;
		this.phone_id = phone_id;
	}
	
	public FetchQuestionWrapper() {
	}

	public long getQuiz_id() {
		return quiz_id;
	}

	public long getQuestion_number() {
		return question_number;
	}
	
	public String getPhone_id() {
		return phone_id;
	}
	
}
