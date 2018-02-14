/**
 * Model object for the team data that is to be returned.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.pubquiz.model;

public class NewTeamReturn {
	private final long id;
	private final String team_name;
	private final long quiz_id;
	private final String phone_id;
	
	public NewTeamReturn(long id, String team_name, long quiz_id, String phone_id) {
		this.id = id;
		this.team_name = team_name;
		this.quiz_id = quiz_id;
		this.phone_id = phone_id;
	}

	public long getId() {
		return id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public long getQuiz_id() {
		return quiz_id;
	}

	public String getPhone_id() {
		return phone_id;
	}	
	
}
