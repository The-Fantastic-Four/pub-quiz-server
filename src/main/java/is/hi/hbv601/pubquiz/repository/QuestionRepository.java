/**
 * Interface for saving questions to the persistance layer
 * @author Viktor Alex Brynjarsson
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

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
}
