/**
 * TeamServiceInt is an interface for service management for team related
 * services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.pubquiz.service.interfaces;

import is.hi.hbv601.pubquiz.model.Quiz;
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
	public String registerTeam(Team t, Quiz q);
	
	/**
	 * Checks whether the phoneId exists within given quiz.
	 * 
	 * @param phoneId The phoneId to be checked for.
	 * @param q The quiz to be checked within.
	 * @return true if phoneId exists; false if it doesn't.
	 */
	public boolean doesPhoneIdExistForQuiz(String phoneId, Quiz q);
}
