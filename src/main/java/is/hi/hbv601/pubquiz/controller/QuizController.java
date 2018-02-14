/**
 * QuizController handles requests concerning quizzes.
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2018
 */
package is.hi.hbv601.pubquiz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.repository.QuestionRepository;
import is.hi.hbv601.pubquiz.service.QuestionService;
import is.hi.hbv601.pubquiz.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController
{
	@Autowired
	QuizService quizService;
	
	@Autowired
	QuestionService questionService;
	
	/**
	 * Show a list of quizzes
	 * @param model 
	 * @return the list of quizzes view
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listQuizzes(Model model) {
    	model.addAttribute("quizzes", quizService.allQuizzes());
    	
    	return new ModelAndView("quiz/index");
    }
	
    /**
     * Save a new quiz
     * @param quiz the quiz to be saved
     * @param errors errors from the model validation
     * @param model
     * @return list of quizzes if successful otherwise show the form again
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView saveQuiz(
    		@Valid @ModelAttribute("quiz") Quiz quiz, 
    		BindingResult errors,
    		Model model) {
    	
    	if (!errors.hasErrors())
    	{
    		quizService.addQuiz(quiz);
        	
        	return new ModelAndView("redirect:/quiz");
    	}
    	
    	return new ModelAndView("quiz/form");
    }

    /**
     * Show the form for creating new quizzes
     * @param model
     * @return form for creating new quizzes
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newQuiz(Model model) {
    	model.addAttribute("quiz", new Quiz());
    	
    	return new ModelAndView("quiz/form");
    }

    /**
     * Delete a quiz
     * @param quizId the id of the quiz to be deleted
     * @param model
     * @return list of quizzes
     */
    @RequestMapping(value = "/{quizId}", method = RequestMethod.DELETE)
    public ModelAndView deleteQuiz(
    		@PathVariable(value = "quizId") long quizId,
    		Model model) {
    	
    	quizService.deleteQuiz(quizId);
    	
    	return new ModelAndView("redirect:/quiz");
    }

    /**
     * Add an empty question to a quiz
     * @param quizId id of the quiz to which the question should be added
     * @param model
     * @return list of quizzes
     */
    @RequestMapping(value = "/{quizId}/addQuestion", method = RequestMethod.POST)
    public ModelAndView quizAddQuestion(
    		@PathVariable(value = "quizId") long quizId,
    		Model model) {
    	
    	Quiz quiz = quizService.findQuiz(quizId);
    	
    	Question q = new Question();
    	q.setQuestion("How much wood could a woodchuck chuck if a wood chuck could chuck wood?");
    	q.setQuestion_number(0);
    	q.setQuestion_type("text");
    	q.setQuiz(quiz);
    	
    	questionService.addQuestion(q);

    	return new ModelAndView("redirect:/quiz");
    }
}
