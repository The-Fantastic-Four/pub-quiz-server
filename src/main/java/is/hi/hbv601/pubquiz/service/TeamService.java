/**
 * TeamService is an implementation of the TeamServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 4. mar. 2018
 */

package is.hi.hbv601.pubquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public String registerTeam(Team t, Quiz q)
	{
		String jsonString = "";
		boolean exists = teamExists(t, q);
		if (exists)
		{
			return jsonString;
		}

		NewTeamReturn registeredTeam = createRegisteredTeam(t, q);
		saveData(registeredTeam, q);
		
		//TODO: Consider how to tackle this, do we need to send JSON back?
		//It fails the conversion now due to the fact that the NewTeamReturn contains and 
		//object as a variable. Easily fixable with a new model class but do we need it?
		//Discuss.
		
		/*try
		{
			System.out.println("Also to here.");
			jsonString = convertToJsonString(registeredTeam);
		}
		catch (JsonProcessingException e)
		{
			// TODO: Consider how to handle if the JSON string conversion fails
			e.printStackTrace();
		}*/

		return jsonString;
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

	/**
	 * Converts given NewTeamReturn object to JSON string.
	 * 
	 * @param t
	 *            The NewTeamReturn object to be converted.
	 * @return JSON String that corresponds to given object information.
	 * @throws JsonProcessingException
	 */
	private String convertToJsonString(NewTeamReturn t) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(t);
	}
	
	public boolean doesPhoneIdExistForQuiz(String phoneId, Quiz q) 
	{
		return teamRepository.countByPhoneIdAndQuiz(phoneId, q) > 0;
	}
}
