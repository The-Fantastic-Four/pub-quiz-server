/**
 * Interface for saving quizzes to the persistance layer
 * @author Viktor Alex Brynjarsson
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import is.hi.hbv601.pubquiz.model.Quiz;

public interface QuizRepository
	extends JpaRepository<Quiz, Long>
{
	List<Quiz> findAll();
	
	Quiz findOne(Long id);
	
	@SuppressWarnings("unchecked")
	Quiz save(Quiz q);

	@Transactional
	Long deleteById(Long id);
}
