/**
 * QuizController handles requests concerning quizzes.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 22. feb. 2018
 */
package is.hi.hbv601.pubquiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.model.QuestionsInQuiz;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.service.HostService;
import is.hi.hbv601.pubquiz.service.QuestionService;
import is.hi.hbv601.pubquiz.service.QuestionsInQuizService;
import is.hi.hbv601.pubquiz.service.QuizService;
import javassist.NotFoundException;

@Controller
@RequestMapping("/quiz")
public class QuizController
{
	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	@Autowired
	QuestionsInQuizService questionsInQuizService;

	@Autowired
	HostService hostService;

	/**
	 * Show a list of quizzes
	 * 
	 * @param model
	 * @return the list of quizzes view
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView listQuizzes(Model model, Authentication authentication)
	{
		Host host = hostService.findHostByEmail(authentication.getName());
		model.addAttribute("quizzes", host.getQuizzes());

		return new ModelAndView("quiz/index");
	}

	/**
	 * Save a new quiz
	 * 
	 * @param quiz
	 *            the quiz to be saved
	 * @param errors
	 *            errors from the model validation
	 * @param model
	 * @return list of quizzes if successful otherwise show the form again
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView saveQuiz(@Valid @ModelAttribute("quiz") Quiz quiz, BindingResult errors, Model model,
			Authentication authentication)
	{
		Host host = hostService.findHostByEmail(authentication.getName());

		if (!errors.hasErrors())
		{
			quizService.addQuiz(quiz, host);

			return new ModelAndView("redirect:/quiz");
		}

		return new ModelAndView("quiz/form");
	}

	/**
	 * Show the form for creating new quizzes
	 * 
	 * @param model
	 * @return form for creating new quizzes
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newQuiz(Model model)
	{
		model.addAttribute("quiz", new Quiz());

		return new ModelAndView("quiz/form");
	}

	/**
	 * View a quiz
	 * 
	 * @param quizId
	 *            the id of the quiz to be viewed
	 * @param model
	 * @return the quiz
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{quizId}", method = RequestMethod.GET)
	public ModelAndView showQuiz(@PathVariable(value = "quizId") long quizId, Model model,
			Authentication authentication) throws NotFoundException
	{
		Host host = hostService.findHostByEmail(authentication.getName());
		Quiz quiz = quizService.findQuiz(quizId, host);

		model.addAttribute("quiz", quiz);

		return new ModelAndView("quiz/show");
	}

	/**
	 * Delete a quiz
	 * 
	 * @param quizId
	 *            the id of the quiz to be deleted
	 * @param model
	 * @return list of quizzes
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{quizId}", method = RequestMethod.DELETE)
	public ModelAndView deleteQuiz(@PathVariable(value = "quizId") long quizId, Model model,
			Authentication authentication) throws NotFoundException
	{
		Host host = hostService.findHostByEmail(authentication.getName());
		
		// The findQuiz function throws exceptions if the host does not own the quiz
		@SuppressWarnings("unused")
		Quiz quiz = quizService.findQuiz(quizId, host);

		quizService.deleteQuiz(quizId);

		return new ModelAndView("redirect:/quiz");
	}

	/**
	 * Show form for adding a new question
	 * 
	 * @param quizId
	 *            id of the quiz to which the question should be added
	 * @param model
	 * @return add question form
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{quizId}/addQuestion", method = RequestMethod.GET)
	public ModelAndView quizAddQuestionForm(@PathVariable(value = "quizId") long quizId, Model model,
			Authentication authentication) throws NotFoundException
	{
		Host host = hostService.findHostByEmail(authentication.getName());
		Quiz quiz = quizService.findQuiz(quizId, host);

		Question question = new Question();
		question.setQuiz(quiz);

		model.addAttribute("newQuestion", question);
		model.addAttribute("quiz", quiz);

		return new ModelAndView("question/form");
	}

	/**
	 * Add an empty question to a quiz
	 * 
	 * @param quizId
	 *            id of the quiz to which the question should be added
	 * @param model
	 * @return list of quizzes
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{quizId}/addQuestion", method = RequestMethod.POST)
	public ModelAndView quizAddQuestion(@PathVariable(value = "quizId") long quizId,
			@Valid @ModelAttribute("newQuestion") Question question, BindingResult errors, Model model,
			Authentication authentication) throws NotFoundException
	{
		Host host = hostService.findHostByEmail(authentication.getName());
		Quiz quiz = quizService.findQuiz(quizId, host);

		if (!errors.hasErrors())
		{
			if (!questionService.doesQuestionExist(question))
			{
				questionService.addQuestion(question);
				// TODO: Alert user if question already exists, maybe offer to use it?
			}
			QuestionsInQuiz link = new QuestionsInQuiz(quiz, question);
			questionsInQuizService.addQuestion(link);
			return new ModelAndView("redirect:/quiz/" + quizId);
		}

		return new ModelAndView("question/form");
	}

	/**
	 * Show form for adding a question that has already been created.
	 * 
	 * @param quizId
	 *            id of the quiz to which the question should be added
	 * @param model
	 * @return add created question form
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{quizId}/addCreatedQuestion", method = RequestMethod.GET)
	public ModelAndView quizAddCreatedQuestionForm(@PathVariable(value = "quizId") long quizId, Model model,
			Authentication authentication) throws NotFoundException
	{
		Host host = hostService.findHostByEmail(authentication.getName());
		Quiz quiz = quizService.findQuiz(quizId, host);

		Question question = new Question();
		List<Question> publicQuestionList = questionService.getPublicQuestionList();
		List<Question> privateQuestionList = questionService.getPrivateQuestionList();
		question.setQuiz(quiz);

		model.addAttribute("newQuestion", question);
		model.addAttribute("quiz", quiz);
		model.addAttribute("publicList", publicQuestionList);
		model.addAttribute("privateList", privateQuestionList);

		return new ModelAndView("question/formForCreatedQuestion");
	}

	/**
	 * Delete a quiz
	 * 
	 * @param quizId
	 *            the id of the quiz to be deleted
	 * @param model
	 * @return list of quizzes
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{quizId}/question/{questionId}", method = RequestMethod.DELETE)
	public ModelAndView deleteQuestion(@PathVariable(value = "quizId") long quizId,
			@PathVariable(value = "questionId") long questionId, Model model, Authentication authentication)
			throws NotFoundException
	{
		Host host = hostService.findHostByEmail(authentication.getName());

		// The findQuiz function throws exceptions if the host does not own the quiz
		@SuppressWarnings("unused")
		Quiz quiz = quizService.findQuiz(quizId, host);

		// TODO: maybe check if question is part of quiz

		questionsInQuizService.deleteLink(quizId, questionId);

		// TODO: If this question is in use, this may be dangerous
		questionService.deleteQuestion(questionId);

		return new ModelAndView("redirect:/quiz/" + quizId);
	}
}
