/**
 * QuizController handles requests concerning quizzes.
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 13. feb. 2018
 */
package is.hi.hbv601.restServer.controller;

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

import is.hi.hbv601.restServer.model.Question;
import is.hi.hbv601.restServer.model.Quiz;
import is.hi.hbv601.restServer.repository.QuestionRepository;
import is.hi.hbv601.restServer.service.QuestionService;
import is.hi.hbv601.restServer.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController
{
	@Autowired
	QuizService quizService;
	
	@Autowired
	QuestionService questionService;
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listQuizzes(Model model) {
    	model.addAttribute("quizzes", quizService.allQuizzes());
    	
    	return new ModelAndView("quiz/index");
    }
	
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newQuiz(Model model) {
    	model.addAttribute("quiz", new Quiz());
    	
    	return new ModelAndView("quiz/form");
    }

    @RequestMapping(value = "/{quizId}", method = RequestMethod.DELETE)
    public ModelAndView deleteQuiz(
    		@PathVariable(value = "quizId") long quizId,
    		Model model) {
    	
    	quizService.deleteQuiz(quizId);
    	
    	return new ModelAndView("redirect:/quiz");
    }

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
