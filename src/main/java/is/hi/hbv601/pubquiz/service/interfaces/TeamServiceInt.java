/**
 * TeamServiceInt is an interface for service management for team related
 * services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.pubquiz.service.interfaces;

import is.hi.hbv601.pubquiz.model.Team;

public interface TeamServiceInt
{

	/**
	 * Checks whether the team exists already, if it doesn't it registers the team.
	 * 
	 * @param t
	 *            The team to register.
	 * @return Relevant JSON string if successful; Empty JSON string if it fails.
	 */
	public String registerTeam(Team t);
}
