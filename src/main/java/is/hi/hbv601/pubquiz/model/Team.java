/**
 * Model object for the Team data.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.pubquiz.model;

public class Team {
	private long id;
    private String room_name;
    private String team_name;
    private String phone_id;

    public Team(long id, String room_name, String team_name, String phone_id) {
        this.id = id;
        this.room_name = room_name;
        this.team_name = team_name;
        this.phone_id = phone_id;
    }
    
    public Team() {
    	
    }

	public long getId() {
		return id;
	}

	public String getRoom_name() {
		return room_name;
	}
	
	public String getTeam_name() {
		return team_name;
	}

	public String getPhone_id() {
		return phone_id;
	} 
    
}
