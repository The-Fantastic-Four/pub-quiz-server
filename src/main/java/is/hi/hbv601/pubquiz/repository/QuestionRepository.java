/**
 * Interface for saving questions to the persistance layer
 * @author Viktor Alex Brynjarsson
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import is.hi.hbv601.pubquiz.model.Question;

public interface QuestionRepository
	extends JpaRepository<Question, Long>
{
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
	
	/**
	 * Fetches all questions that have been made public.
	 * @return A list of all publicly available questions.
	 */
	List<Question> findByIsPrivateFalseOrderByQuestionAsc();
	
	/**
	 * Searches all publicly available questions for given string.
	 * @param question Is the string that's being used to search for within the question
	 * @return List of all questions that contain the search string within.
	 */
	List<Question> findByQuestionContainingIgnoreCaseAndIsPrivateFalse(String question);
	
	/**
	 * Searches if the exact same question exists.
	 * @param question The question that is being searched for.
	 * @return The same question if it exists.
	 */
	long countByQuestion(String question);
}
