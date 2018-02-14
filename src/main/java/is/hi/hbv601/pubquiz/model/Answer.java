/**
 * Model object for the Answer data.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */
package is.hi.hbv601.pubquiz.model;

public class Answer {
	private long id;
	private long team_id;
	private long question_id;
	private String answer;
	
	public Answer(long id, long team_id, long question_id, String answer) {
		this.id = id;
		this.team_id = team_id;
		this.question_id = question_id;
		this.answer = answer;
	}
	
	public Answer() {
	}

	public long getId() {
		return id;
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
