/**
 * Interface for saving quizzes to the persistance layer
 * 
 * @author Viktor Alex Brynjarsson
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>
{
	/**
	 * Find all quizzes in the repository
	 * 
	 * @return a list of quizzes
	 */
	List<Quiz> findAllByHost(Host host);

	/**
	 * Find a quiz by id
	 * 
	 * @param id
	 *            the id of the quiz to be found
	 * @return the quiz that was found
	 */
	Quiz findOne(Long id);

	/**
	 * Save quiz to the repository
	 * 
	 * @param q
	 *            the quiz to be saved
	 */
	@SuppressWarnings("unchecked")
	Quiz save(Quiz q);

	/**
	 * Delete a quiz from the repository
	 * 
	 * @param id
	 *            the id of the quiz to be deleted
	 * @return id of the quiz that was deleted
	 */
	@Transactional
	Long deleteById(Long id);
	
	/**
	 * Finds all quizzes with the given room name.
	 * @param roomName Is the name to be looked for.
	 * @return List of all quizzes that have given room name.
	 */
	List<Quiz> findAllByRoomName(String roomName);
}
