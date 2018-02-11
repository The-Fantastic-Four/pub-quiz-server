/**
 * TeamServiceInt is an interface for service management for
 * team related services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.restServer.service.interfaces;

import org.springframework.http.ResponseEntity;

import is.hi.hbv601.restServer.model.Team;

public interface TeamServiceInt {
	
	/**
	 * Checks whether the team exists already, if it doesn't it registers the team.
	 * 
	 * @param t The team to register.
	 * @return HTTP status of 201 if successful and a relevant JSON object; HTTP status 
	 * 403 if the team already exists.
	 */
	public ResponseEntity<?> registerTeam(Team t);
}
