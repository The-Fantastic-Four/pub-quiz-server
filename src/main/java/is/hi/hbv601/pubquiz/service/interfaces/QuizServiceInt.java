/**
 * Interface for a service for getting, and deleting quizzes
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.service.interfaces;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Quiz;
import javassist.NotFoundException;

public interface QuizServiceInt
{
	/**
	 * Finds quiz by it's id
	 * 
	 * @param id
	 *            id of the quiz to be found
	 * @param host
	 *            the host which owns the quiz
	 * @return the quiz that was found
	 */
	public Quiz findQuiz(long id, Host host) throws NotFoundException;

	/**
	 * Add a new quiz to the database
	 * 
	 * @param q
	 *            the quiz to be added
	 */
	public void saveQuiz(Quiz q, Host host);

	/**
	 * Delete quiz from database
	 * 
	 * @param id
	 *            id of the quiz to be deleted
	 */
	public void deleteQuiz(long id);
}
