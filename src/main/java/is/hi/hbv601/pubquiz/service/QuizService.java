/**
 * Service for getting, and deleting quizzes
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2017
 */
package is.hi.hbv601.pubquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.repository.QuizRepository;
import is.hi.hbv601.pubquiz.service.interfaces.QuizServiceInt;

@Service
public class QuizService implements QuizServiceInt
{
	@Autowired
	QuizRepository quizRepository;

	@Override
	public List<Quiz> allQuizzes()
	{
		return quizRepository.findAll();
	}

	@Override
	public Quiz findQuiz(long id)
	{
		return quizRepository.findOne(id);
	}

	@Override
	public void addQuiz(Quiz q)
	{
		quizRepository.save(q);
	}

	@Override
	public void deleteQuiz(long id)
	{
		quizRepository.delete(id);
	}

}
