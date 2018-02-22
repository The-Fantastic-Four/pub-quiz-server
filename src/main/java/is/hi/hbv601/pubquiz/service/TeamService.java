/**
 * TeamService is an implementation of the TeamServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.pubquiz.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.model.Team;
import is.hi.hbv601.pubquiz.model.TeamRegistration;
import is.hi.hbv601.pubquiz.service.interfaces.TeamServiceInt;

@Service
public class TeamService implements TeamServiceInt{
	public String registerTeam(TeamRegistration t){
		String jsonString = "";
		boolean exists = teamExists(t);
		if(exists) {
			return jsonString;
		}
		
		Team registeredTeam = createRegisteredTeam(t);
		saveData(registeredTeam);
		
		
		try {
			jsonString = convertToJsonString(registeredTeam);
		} catch (JsonProcessingException e) {
			// TODO: Consider how to handle if the JSON string conversion fails
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	/**
	 * Checks whether given team exists for given room.
	 * 
	 * @param t The team to be checked for.
	 * @return true if team exists; false if team doesn't exist.
	 */
	private boolean teamExists(TeamRegistration t){
		//TODO: Check if team exists.
		return false;
	}
	
	/**
	 * Saves the data.
	 * 
	 * @param data The data to be saved.
	 */
	private void saveData(Team t) {
		//TODO: Save into database.
		System.out.println("====================");
		System.out.println(t.getTeam_name());
		System.out.println("Registering team in DB");
		System.out.println("====================");
	}
	
	/**
	 * Creates a model for the team that's to be registered for the quiz.
	 * 
	 * @param t Team to be registered.
	 * @return More detailed model for given team.
	 */
	private Team createRegisteredTeam(TeamRegistration t) {
		//TODO: Get required data from database and fill in.
		//return new Team(103, "Tveir á kantinum", 60, "eede877b-7741-4dec-a6a4-3b7d9b06bc5c"); 
		return new Team(103, "Tveir á kantinum", new Quiz(), "eede877b-7741-4dec-a6a4-3b7d9b06bc5c");
	}
	
	/**
	 * Converts given NewTeamReturn object to JSON string.
	 * 
	 * @param t The NewTeamReturn object to be converted.
	 * @return JSON String that corresponds to given object information.
	 * @throws JsonProcessingException
	 */
	private String convertToJsonString(Team t) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(t);
	}
}
