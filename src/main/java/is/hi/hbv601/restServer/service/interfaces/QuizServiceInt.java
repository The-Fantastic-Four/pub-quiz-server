/**
 * Interface for a service for getting, and deleting quizzes
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2017
 */
package is.hi.hbv601.restServer.service.interfaces;

import java.util.List;

import is.hi.hbv601.restServer.model.Quiz;

public interface QuizServiceInt
{
	/**
	 * Fetches all quizzes in the database
	 * @return list of all the quizzes
	 */
	public List<Quiz> allQuizzes();
	
	/**
	 * Finds quiz by it's id
	 * @param id id of the quiz to be found
	 * @return
	 */
	public Quiz findQuiz(long id);
	
	/**
	 * Add a new quiz to the database
	 * @param q the quiz to be added
	 */
	public void addQuiz(Quiz q);
	
	/**
	 * Delete quiz from database
	 * @param id id of the quiz to be deleted
	 */
	public void deleteQuiz(long id);
}
