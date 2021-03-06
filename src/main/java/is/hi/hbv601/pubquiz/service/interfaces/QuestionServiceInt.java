/**
 * QuestionServiceInt is an interface for service management for question
 * related services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.pubquiz.service.interfaces;

import is.hi.hbv601.pubquiz.model.FetchQuestionWrapper;
import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Question;
import javassist.NotFoundException;

public interface QuestionServiceInt
{
	/**
	 * Finds a question by it's database id
	 * @param id the id of the question
	 * @return a question if found, null otherwise
	 */
	public Question findQuestion(long id, Host host) throws NotFoundException;
	
	/**
	 * Checks if the data is valid and if so fetches the related question.
	 * 
	 * @param w
	 *            Is the data to be checked.
	 * @return The question relevant to the data.
	 */
	public Question getQuestionFromQuiz(FetchQuestionWrapper w);

	/**
	 * Add a new question to the database
	 * 
	 * @param q
	 *            the question to be added
	 * @return The question that was inserted
	 */
	public Question saveQuestion(Question q);

	/**
	 * Delete question from database
	 * 
	 * @param id
	 *            id of the question to be deleted
	 */
	public void deleteQuestion(long id);
}
