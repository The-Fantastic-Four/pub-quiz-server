/**
 * Interface for saving questions to the persistance layer
 * @author Viktor Alex Brynjarsson
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import is.hi.hbv601.pubquiz.model.Question;

public interface QuestionRepository
	extends JpaRepository<Question, Long>
{
	Question findByQuizIdAndQuestionNumber(long id, long questionNumber);
	
	/**
	 * Save a question to the repository
	 * @param q The question to be saved
	 */
	@SuppressWarnings("unchecked")
	Question save(Question q);

	/**
	 * Delete a question from the repository
	 * @param id the id of the question to be deleted
	 * @return id of the question that was deleted
	 */
	@Transactional
	Long deleteById(Long id);
}
