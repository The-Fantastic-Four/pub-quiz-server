/**
 * Interface for saving questions within quiz to the persistence layer.
 * @author Eiður Örn Gunnarsson
 * @date 22. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import is.hi.hbv601.pubquiz.model.QuestionsInQuiz;

public interface QuestionsInQuizRepository
	extends JpaRepository<QuestionsInQuiz, Long>
{
	/**
	 * Save a question to the repository
	 * @param q The question to be saved
	 */
	@SuppressWarnings("unchecked")
	QuestionsInQuiz save(QuestionsInQuiz q);
	
	/**
	 * Deletes a row that links a question and a quiz together
	 * 
	 * @param quizId id of the quiz in question
	 * @param questionId id of the question in question
	 * @return id of the row that was deleted
	 */
	@Transactional
	Long deleteByQuizIdAndQuestionId(Long quizId, Long questionId);

}
