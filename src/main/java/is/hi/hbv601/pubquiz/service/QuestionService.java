/**
 * QuestionService is an implementation of the QuestionServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.pubquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.FetchQuestionWrapper;
import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.repository.QuestionRepository;
import is.hi.hbv601.pubquiz.service.interfaces.QuestionServiceInt;
import javassist.NotFoundException;

@Service
public class QuestionService implements QuestionServiceInt
{

	@Autowired
	QuestionRepository questionRepository;
	
	public Question findQuestion(long id, Host host) throws NotFoundException
	{
		Question question = questionRepository.findOne(id);
		
		if (question == null)
			throw new NotFoundException("Could not find question with given id");
		if (host == null || question.getIsPrivate() && question.getHost().getId() != host.getId())
			throw new AccessDeniedException("Host does not have access to this question");
		
		return question;
	}

	public Question getQuestionFromQuiz(FetchQuestionWrapper w)
	{
		boolean valid = checkData(w);
		if (valid)
		{
			return getQuestion(w);
		}
		// TODO: Consider how to handle if the data for fetching the question is not
		// valid.
		return new Question("This request is not valid", 0, "invalid", false);
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
		Question q = new Question("Are pancakes delicious?", 4, "text", false);
		System.out.println("====================");
		System.out.println(q.getQuestion());
		System.out.println("Returning Question");
		System.out.println("====================");
		return q;
	}

	/**
	 * Add a new question to the database
	 * 
	 * @param q
	 *            the question to be added
	 * @return The question that was inserted
	 */
	@Override
	public Question saveQuestion(Question q)
	{
		return questionRepository.save(q);
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
	public List<Question> getPrivateQuestionList(Host host)
	{
		return questionRepository.findByIsPrivateTrueAndHostOrderByQuestionAsc(host);
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
	 * @return true if question exists; false if it doesn't.
	 */
	public boolean doesQuestionExist(Question question)
	{
		return questionRepository.countByQuestion(question.getQuestion()) >= 1;
	}
}
