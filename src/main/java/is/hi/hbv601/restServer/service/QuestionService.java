/**
 * QuestionService is an implementation of the QuestionServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.restServer.service;

import org.springframework.stereotype.Service;

import is.hi.hbv601.restServer.model.FetchQuestionWrapper;
import is.hi.hbv601.restServer.model.Question;
import is.hi.hbv601.restServer.service.interfaces.QuestionServiceInt;

@Service
public class QuestionService implements QuestionServiceInt{
	public Question getQuestionFromQuiz(FetchQuestionWrapper w) {
		boolean valid = checkData(w);
		if(valid) {
			return getQuestion(w);
		}	
		// TODO: Consider how to handle if the data for fetching the question is not valid.
		return new Question(0, "This request is not valid", 0, 0, "invalid");
	}
	
	/**
	 * Checks whether the data is valid.
	 * 
	 * @param data Is the data to be checked.
	 * @return Whether the data is valid or not.
	 */
	private boolean checkData(FetchQuestionWrapper data) {
		boolean valid = true;
		//TODO: Check if the JSON string is correct. (Check if match and question number exists).
		return valid;
	}
	
	/**
	 * Fetches the related question.
	 * 
	 * @param w The data that defines what question is to be fetched.
	 * @return The question relevant to the data.
	 */
	private Question getQuestion(FetchQuestionWrapper w) {
		// TODO: Fetch the question and return it.
		Question q = new Question(0, "Are pancakes delicious?", 4, 10, "text");
		System.out.println("====================");
		System.out.println(q.getQuestion());
		System.out.println("Returning Question");
		System.out.println("====================");
		return q;
	}
}
