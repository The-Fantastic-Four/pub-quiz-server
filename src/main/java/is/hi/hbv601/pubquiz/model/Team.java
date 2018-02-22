/**
 * Model object for the team data that is to be returned.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.pubquiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "team")
public class Team {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	@NotNull(message = "Þessi reitur má ekki vera tómur.")
	private String team_name;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@NotNull(message = "Þessi reitur má ekki vera tómur.")
	private String phone_id;
	
	public Team() {
	}
	
	public Team(long id, String team_name, Quiz quiz, String phone_id) {
		this.id = id;
		this.team_name = team_name;
		this.quiz = quiz;
		this.phone_id = phone_id;
	}

	public long getId() {
		return id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public String getPhone_id() {
		return phone_id;
	}	
	
}
