/**
 * TeamService is an implementation of the TeamServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. mar. 2018
 */

package is.hi.hbv601.pubquiz.service;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.NewTeamReturn;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.model.Team;
import is.hi.hbv601.pubquiz.repository.QuizRepository;
import is.hi.hbv601.pubquiz.repository.TeamRepository;
import is.hi.hbv601.pubquiz.service.interfaces.TeamServiceInt;

@Service
public class TeamService implements TeamServiceInt
{
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired 
	QuizRepository quizRepository;
	
	public NewTeamReturn registerTeam(Team t, Quiz q) throws AccessDeniedException
	{
		boolean exists = teamExists(t, q);
		if (exists)
		{
			throw new AccessDeniedException("This team already exists for this quiz.");
		}

		NewTeamReturn registeredTeam = createRegisteredTeam(t, q);
		saveData(registeredTeam, q);
		
		return registeredTeam;
	}

	/**
	 * Checks whether given team exists for given room.
	 * 
	 * @param t
	 *            The team to be checked for.
	 * @return true if team exists; false if team doesn't exist.
	 */
	private boolean teamExists(Team t, Quiz q)
	{
		for(NewTeamReturn team : q.getTeams()) {
			if(t.getTeam_name().equals(team.getTeam_name()))
				return true;
		}
		return false;
	}

	/**
	 * Saves the data.
	 * 
	 * @param data
	 *            The data to be saved.
	 */
	private void saveData(NewTeamReturn t, Quiz q)
	{
		teamRepository.save(t);
		q.addTeam(t);
		quizRepository.save(q);
	}

	/**
	 * Creates a model for the team that's to be registered for the quiz.
	 * 
	 * @param t
	 *            Team to be registered.
	 * @return More detailed model for given team.
	 */
	private NewTeamReturn createRegisteredTeam(Team t, Quiz q)
	{
		return new NewTeamReturn(t.getTeam_name(), q, t.getPhone_id());
	}

	
	public boolean doesPhoneIdExistForQuiz(String phoneId, Quiz q) 
	{
		return teamRepository.countByPhoneIdAndQuiz(phoneId, q) > 0;
	}
}
