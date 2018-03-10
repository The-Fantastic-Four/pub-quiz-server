/**
 * TeamServiceInt is an interface for service management for team related
 * services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. mar. 2018
 */

package is.hi.hbv601.pubquiz.service.interfaces;

import java.nio.file.AccessDeniedException;

import is.hi.hbv601.pubquiz.model.Team;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.model.ReceivedTeam;

public interface TeamServiceInt
{

	/**
	 * Checks whether the team exists already, if it doesn't it registers the team.
	 * 
	 * @param t
	 *            The team to register.
	 * @return A more detailed team object.
	 */
	public Team registerTeam(ReceivedTeam t, Quiz q) throws AccessDeniedException;
	
	/**
	 * Checks whether the phoneId exists within given quiz.
	 * 
	 * @param phoneId The phoneId to be checked for.
	 * @param q The quiz to be checked within.
	 * @return true if phoneId exists; false if it doesn't.
	 */
	public boolean doesPhoneIdExistForQuiz(String phoneId, Quiz q);
}
