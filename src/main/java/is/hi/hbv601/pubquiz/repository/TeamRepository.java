/**
 * Interface for saving quizzes to the persistance layer
 * @author Fannar Þeyr Guðmundsson
 * @date 25. feb. 2017
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import is.hi.hbv601.pubquiz.model.Team;

public interface TeamRepository	extends JpaRepository<Team, String>
  {

  	/**
  	 * Find a team by androidId
  	 * @param id the androidId registered to team
  	 * @return the team that was found
  	 */
     Team findByPhone_id(String id);


     /**
   	 * Delete a Team from the repository
   	 * @param id the phone_id of the Team to be deleted
   	 * @return id of the Team that was deleted
   	 */
   	@Transactional
   	Long deleteByPhone_id(String id);

  }
