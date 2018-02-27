/**
 * QuestionService is an implementation of the QuestionServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.pubquiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.FetchQuestionWrapper;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.repository.QuestionRepository;
import is.hi.hbv601.pubquiz.service.interfaces.QuestionServiceInt;

@Service
public class QuestionService implements QuestionServiceInt
{

	@Autowired
	QuestionRepository questionRepository;

	public Question getQuestionFromQuiz(FetchQuestionWrapper w)
	{
		boolean valid = checkData(w);
		if (valid)
		{
			return getQuestion(w);
		}
		// TODO: Consider how to handle if the data for fetching the question is not
		// valid.
		return new Question(0, "This request is not valid", 0, 0, "invalid", false);
	}

	/**
	 * Checks whether the data is valid.
	 * 
	 * @param data
	 *            Is the data to be checked.
	 * @return Whether the data is valid or not.
	 */
	private boolean checkData(FetchQuestionWrapper data)
	{
		boolean valid = true;
		// TODO: Check if the JSON string is correct. (Check if match and question
		// number exists).
		return valid;
	}

	/**
	 * Fetches the related question.
	 * 
	 * @param w
	 *            The data that defines what question is to be fetched.
	 * @return The question relevant to the data.
	 */
	private Question getQuestion(FetchQuestionWrapper w)
	{
		// TODO: Fetch the question and return it.
		Question q = new Question(0, "Are pancakes delicious?", 4, 10, "text", false);
		System.out.println("====================");
		System.out.println(q.getQuestion());
		System.out.println("Returning Question");
		System.out.println("====================");
		return q;
	}

	/**
	 * Add question to the repository
	 * 
	 * @param q
	 *            The question to be added
	 */
	@Override
	public void addQuestion(Question q)
	{
		questionRepository.save(q);
	}

	/**
	 * Delete question
	 * 
	 * @param id
	 *            the id of the quiz to be deleted
	 */
	@Override
	public void deleteQuestion(long id)
	{
		questionRepository.delete(id);
	}

	/**
	 * Fetches the list of all user related private questions.
	 * 
	 * @return list of all private questions for appropriate user.
	 */
	public List<Question> getPrivateQuestionList()
	{
		// TODO: When login system is in place a query can be created and should be
		// called here
		// for getting questions for related user.
		List<Question> l = new ArrayList<Question>();
		l.add(new Question(40, "Question generated in code for testing purposes", 3, 5, "text", true));
		return l;
	}

	/**
	 * Fetches the list of all publicly available questions
	 * 
	 * @return list of all public available questions
	 */
	public List<Question> getPublicQuestionList()
	{
		return questionRepository.findByIsPrivateFalseOrderByQuestionAsc();
	}

	/**
	 * Finds out whether the question that is given exists already or not.
	 * 
	 * @param question
	 *            The question that is meant to be checked for.
	 * @return true if question exists; False if it doesn't.
	 */
	public boolean doesQuestionExist(Question question)
	{
		return questionRepository.countByQuestion(question.getQuestion()) >= 1;
	}
}
