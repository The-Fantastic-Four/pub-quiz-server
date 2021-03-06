/**
 * Interface for a service for getting, and deleting quizzes
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.service.interfaces;

import java.util.List;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Question;
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
	
	/**
	 * Fetches the active question of the quiz that is supplied.
	 * 
	 * @param quizId The id of the quiz to be found
	 * @return
	 * @throws NotFoundException 
	 */
	public Question fetchQuestion(long quizId) throws NotFoundException;
	
	/**
	 * Find quiz by id alone.
	 * 
	 * @param quizId The id of the quiz to be found
	 * @return
	 * @throws NotFoundException
	 */
	public Quiz findQuizById(long quizId) throws NotFoundException;
	
	/**
	 * Fetches all quizzes containing the given room name.
	 * 
	 * @param roomName The name to be looked for.
	 * @return List of all quizzes containing the given room name.
	 */
	public List<Quiz> findByRoomName(String roomName);
	
	/**
	 * Fetches all quizzes that have been published.
	 * 
	 * @return List of all published quizzes.
	 */
	public List<Quiz> findPublished();
	
	/**
	 * Checks whether there is an already active quiz with the given room name.
	 * 
	 * @param quiz The new quiz that is being created.
	 * @return true if there is a time intersect; false if not.
	 */
	public boolean timeIntersect(Quiz quiz);
}
