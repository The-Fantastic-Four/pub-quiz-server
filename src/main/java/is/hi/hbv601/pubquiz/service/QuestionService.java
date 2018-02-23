/**
 * QuestionService is an implementation of the QuestionServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.pubquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.FetchQuestionWrapper;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.repository.QuestionRepository;
import is.hi.hbv601.pubquiz.service.interfaces.QuestionServiceInt;

@Service
public class QuestionService implements QuestionServiceInt{
	
	@Autowired
	QuestionRepository questionRepository;
	
	public Question getQuestionFromQuiz(FetchQuestionWrapper w) {
		return questionRepository.findByQuizIdAndQuestionNumber(w.getQuiz_id(), w.getQuestion_number());
	}

	/**
	 * Add question to the repository
	 * @param q The question to be added
	 */
	@Override
	public void addQuestion(Question q)
	{
		questionRepository.save(q);
	}

	/**
	 * Delete question
	 * @param id the id of the quiz to be deleted
	 */
	@Override
	public void deleteQuestion(long id)
	{
		questionRepository.delete(id);
	}
}
