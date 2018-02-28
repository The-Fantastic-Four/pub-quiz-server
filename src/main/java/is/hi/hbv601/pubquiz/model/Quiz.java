/**
 * Represents a quiz
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2018
 */
package is.hi.hbv601.pubquiz.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "quiz")
public class Quiz
{

	/**
	 * Unique identifier for the quiz
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	/**
	 * Identifier used by contestants to join this quiz
	 */
	@NotNull(message = "Þessi reitur má ekki vera tómur.")
	@Size(min = 1, max = 35, message = "Lengd nafns þarf að vera á bilinu 1-35")
	private String roomName;

	@NotNull(message = "Þessi reitur má ekki vera tómur.")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startTime;

	@NotNull(message = "Þessi reitur má ekki vera tómur.")
	private int duration;

	@OneToMany(mappedBy = "quiz")
	@OrderBy("question_number ASC")
	private List<Question> questions;

	@ManyToOne
	@JoinColumn(name = "host_id")
	private Host host;

	public Quiz()
	{

	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public List<Question> getQuestions()
	{
		return questions;
	}

	public Host getHost()
	{
		return host;
	}

	public void setHost(Host host)
	{
		this.host = host;
	}
}
