/**
 * Model object for the QuestionsInQuiz.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 22. feb. 2018
 */

package is.hi.hbv601.pubquiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "QuestionsInQuiz")
public class QuestionsInQuiz
{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	public QuestionsInQuiz()
	{

	}

	public QuestionsInQuiz(Quiz quiz, Question question)
	{
		this.quiz = quiz;
		this.question = question;
	}

	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public long getId()
	{
		return id;
	}

}
