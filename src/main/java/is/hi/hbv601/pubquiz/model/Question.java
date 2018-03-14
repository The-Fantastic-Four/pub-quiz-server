/**
 * Model object for the Question data.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */
package is.hi.hbv601.pubquiz.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "question")
public class Question
{

	/**
	 * Unique identifier for the question
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	@NotNull(message = "Þessi reitur má ekki vera tómur.")
	private String question;

	private long question_number;

	private String question_type;

	private boolean isPrivate = true;

	@ManyToMany(mappedBy = "questions")
	private Set<Quiz> quizzes;

	@ManyToOne
	@JoinColumn(name = "host_id")
	private Host host;

	public Question()
	{
	}

	public Question(String question, long question_number, String question_type, boolean isPrivate)
	{
		this.question = question;
		this.question_number = question_number;
		this.question_type = question_type;
		this.isPrivate = isPrivate;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public long getQuestion_number()
	{
		return question_number;
	}

	public void setQuestion_number(long question_number)
	{
		this.question_number = question_number;
	}

	public String getQuestion_type()
	{
		return question_type;
	}

	public void setQuestion_type(String question_type)
	{
		this.question_type = question_type;
	}

	public boolean getIsPrivate()
	{
		return this.isPrivate;
	}

	public void setIsPrivate(boolean isPrivate)
	{
		this.isPrivate = isPrivate;
	}

	public Set<Quiz> getQuizzes()
	{
		return quizzes;
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
