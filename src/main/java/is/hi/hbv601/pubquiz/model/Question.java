/**
 * Model object for the Question data.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */
package is.hi.hbv601.pubquiz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "question")
public class Question {

	/**
	 * Unique identifier for the quiz
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	@NotNull(message = "Þessi reitur má ekki vera tómur.")
    private String question;

    private long question_number;

    private long total_questions;

    private String question_type;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	public Question() {
	}

	public Question(long id, String question, long question_number, long total_questions, String question_type) {
		this.id = id;
		this.question = question;
		this.question_number = question_number;
		this.total_questions = total_questions;
		this.question_type = question_type;
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

	public long getTotal_questions()
	{
		return total_questions;
	}

	public void setTotal_questions(long total_questions)
	{
		this.total_questions = total_questions;
	}

	public String getQuestion_type()
	{
		return question_type;
	}

	public void setQuestion_type(String question_type)
	{
		this.question_type = question_type;
	}

	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}
}
