/**
 * QuestionServiceInt is an interface for service management for
 * question related services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.restServer.service.interfaces;

import is.hi.hbv601.restServer.model.FetchQuestionWrapper;
import is.hi.hbv601.restServer.model.Question;

public interface QuestionServiceInt {
	/**
	 * Checks if the data is valid and if so fetches the related question.
	 * 
	 * @param w Is the data to be checked.
	 * @return The question relevant to the data.
	 */
	public Question getQuestionFromQuiz(FetchQuestionWrapper w);
}
