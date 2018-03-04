/**
 * Interface for saving teams to the persistence layer
 * 
 * @author Eiður Örn Gunnarsson | eog26@hi.is
 * @date 4. march. 2018
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import is.hi.hbv601.pubquiz.model.NewTeamReturn;
import is.hi.hbv601.pubquiz.model.Quiz;

public interface TeamRepository extends JpaRepository<NewTeamReturn, Long>{
	
	/**
	 * Save a team to the repository.
	 * 
	 * @param q
	 *            The team to be saved.
	 */
	@SuppressWarnings("unchecked")
	NewTeamReturn save(NewTeamReturn t);

	/**
	 * Searches if the exact same phoneId exists.
	 * 
	 * @param phoneId
	 *            The phone id that is being searched for.
	 * @return Counter for how many of given phone id exist.
	 */
	long countByPhoneIdAndQuiz(String phoneId, Quiz q);
}
