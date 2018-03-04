/**
 * Interface for saving teams to the persistence layer
 * 
 * @author Eiður Örn Gunnarsson | eog26@hi.is
 * @date 4. march. 2018
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import is.hi.hbv601.pubquiz.model.NewTeamReturn;

public interface TeamRepository extends JpaRepository<NewTeamReturn, Long>{
	
	/**
	 * Save a team to the repository.
	 * 
	 * @param q
	 *            The team to be saved.
	 */
	@SuppressWarnings("unchecked")
	NewTeamReturn save(NewTeamReturn t);

}
