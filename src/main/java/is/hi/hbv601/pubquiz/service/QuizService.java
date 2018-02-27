/**
 * Service for getting, and deleting quizzes
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.repository.QuizRepository;
import is.hi.hbv601.pubquiz.service.interfaces.QuizServiceInt;

@Service
public class QuizService implements QuizServiceInt
{
	@Autowired
	QuizRepository quizRepository;

	/**
	 * Get a list of quizzes
	 * 
	 * @return list of quizzes
	 */
	@Override
	public List<Quiz> allQuizzesByHost(Host host)
	{
		return quizRepository.findAllByHost(host);
	}

	/**
	 * Find a quiz by id
	 * 
	 * @param id
	 *            the id of the quiz to be found
	 * @return the quiz with the given id
	 */
	@Override
	public Quiz findQuiz(long id)
	{
		return quizRepository.findOne(id);
	}

	/**
	 * Add a new quiz
	 * 
	 * @param q
	 *            the quiz to be added
	 */
	@Override
	public void addQuiz(Quiz q, Host host)
	{
		q.setHost(host);
		quizRepository.save(q);
	}

	/**
	 * Delete quiz
	 * 
	 * @param id
	 *            the id of the quiz to be deleted
	 */
	@Override
	public void deleteQuiz(long id)
	{
		quizRepository.delete(id);
	}

}
