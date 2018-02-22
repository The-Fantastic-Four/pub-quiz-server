/**
 * QuestionsInQuizService is management for question related services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 22. feb. 2018
 */

package is.hi.hbv601.pubquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.QuestionsInQuiz;
import is.hi.hbv601.pubquiz.repository.QuestionsInQuizRepository;

@Service
public class QuestionsInQuizService {
	
	@Autowired 
	QuestionsInQuizRepository questionsInQuizRepository;
	
	/**
	 * Add a link between the question for related quiz to the repository.
	 * 
	 * @param q The question to be added to the quiz
	 */
	public void addQuestion(QuestionsInQuiz q)
	{
		questionsInQuizRepository.save(q);
	}
	
	/**
	 * Deletes the link between the quiz and question
	 * @param quizId id of quiz in question
	 * @param questionId id of question
	 */
	public void deleteLink(Long quizId, Long questionId) 
	{
		questionsInQuizRepository.deleteByQuizIdAndQuestionId(quizId, questionId);
	}
}
