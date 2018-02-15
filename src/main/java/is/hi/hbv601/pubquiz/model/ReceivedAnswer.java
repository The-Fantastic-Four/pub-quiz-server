/**
 * Model object for the received answer data.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 15. feb. 2018
 */
package is.hi.hbv601.pubquiz.model;

public class ReceivedAnswer {
	private long team_id;
	private long question_id;
	private String answer;
	
	public ReceivedAnswer(long team_id, long question_id, String answer) {
		this.team_id = team_id;
		this.question_id = question_id;
		this.answer = answer;
	}
	
	public ReceivedAnswer() {
	}
	
	public long getTeam_id() {
		return team_id;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public String getAnswer() {
		return answer;
	}

}
