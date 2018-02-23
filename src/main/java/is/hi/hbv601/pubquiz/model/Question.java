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

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private long questionNumber;

    private long totalQuestions;

    private String questionType;

    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	public Question() {
	}

	public Question(long id, String question, long questionNumber, long totalQuestions, String questionType) {
		this.id = id;
		this.question = question;
		this.questionNumber = questionNumber;
		this.totalQuestions = totalQuestions;
		this.questionType = questionType;
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

	public long getQuestionNumber()
	{
		return questionNumber;
	}

	public void setQuestionNumber(long questionNumber)
	{
		this.questionNumber = questionNumber;
	}

	public long getTotalQuestions()
	{
		return totalQuestions;
	}

	public void setTotalQuestions(long totalQuestions)
	{
		this.totalQuestions = totalQuestions;
	}

	public String getQuestionType()
	{
		return questionType;
	}

	public void setQuestionType(String questionType)
	{
		this.questionType = questionType;
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
