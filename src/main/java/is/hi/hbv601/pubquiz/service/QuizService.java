/**
 * Service for getting, and deleting quizzes
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. mar. 2018
 */
package is.hi.hbv601.pubquiz.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.repository.QuizRepository;
import is.hi.hbv601.pubquiz.service.interfaces.QuizServiceInt;
import javassist.NotFoundException;

@Service
public class QuizService implements QuizServiceInt
{
	@Autowired
	QuizRepository quizRepository;

	/**
	 * Find a quiz by id
	 * 
	 * @param id
	 *            the id of the quiz to be found
	 * @param host
	 *            the host which owns the quiz
	 * @return the quiz with the given id
	 * @throws NotFoundException
	 */
	@Override
	public Quiz findQuiz(long id, Host host) throws NotFoundException
	{
		Quiz quiz = quizRepository.findOne(id);

		if (quiz == null)
			throw new NotFoundException("Quiz could not be found");
		if (host == null || quiz.getHost() == null || quiz.getHost().getId() != host.getId())
			throw new AccessDeniedException("Host did not create this quiz");

		return quizRepository.findOne(id);
	}

	/**
	 * Add a new quiz
	 * 
	 * @param q
	 *            the quiz to be added
	 */
	@Override
	public void saveQuiz(Quiz q, Host host)
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
	
	
	@Override
	public Question fetchQuestion(long quizId) throws NotFoundException {
		//TODO: Discuss whether it should throw an exception when no question is being asked.
		Quiz quiz = findQuizById(quizId);
		Question question = quiz.getQuestions().get(quiz.getCurrentQuestionNumber());
		System.out.println("*****************************");
		System.out.println(question.getQuestion());
		System.out.println("*****************************");
		return question;
	}

	@Override
	public Quiz findQuizById(long quizId) throws NotFoundException
	{
		Quiz quiz = quizRepository.findOne(quizId);

		if (quiz == null)
			throw new NotFoundException("Quiz could not be found");

		return quizRepository.findOne(quizId);
	}
	
	@Override
	public List<Quiz> findByRoomName(String roomName)
	{
		return quizRepository.findAllByRoomName(roomName);
	}
	
	@Override
	public List<Quiz> findPublished()
	{
		return quizRepository.findAllByIsPublishedTrue();
	}
	
	@Override
	public boolean timeIntersect(Quiz quiz)
	{
		List<Quiz> quizList = findByRoomName(quiz.getRoomName());
		
		//The starting time for the new quiz in calendar form.
		Calendar newStart = Calendar.getInstance();
		newStart.setTime(quiz.getStartTime());
		
		//The ending time for the new quiz in calendar form.
		Calendar newEnd = Calendar.getInstance();
		newEnd.setTime(quiz.getStartTime());
		newEnd.add(Calendar.HOUR_OF_DAY, quiz.getDuration());
		
		for(Quiz q : quizList) {
			//The starting time for an existing quiz in calendar form.
			Calendar existingStart = Calendar.getInstance();
			existingStart.setTime(q.getStartTime());
			
			//The ending time for an existing quiz in calendar form.
			Calendar existingEnd = Calendar.getInstance();
			existingEnd.setTime(q.getStartTime());
			existingEnd.add(Calendar.HOUR_OF_DAY, q.getDuration());
			
			if(existingStart.before(newEnd) && existingEnd.after(newStart))
				return true;
		}
		return false;
	}
}
