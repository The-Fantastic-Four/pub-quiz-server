/**
 * RESTResponseController reacts to JSON requests.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. mar. 2018
 */

package is.hi.hbv601.pubquiz.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import is.hi.hbv601.pubquiz.model.FetchQuestionWrapper;
import is.hi.hbv601.pubquiz.model.NewTeamReturn;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.model.ReceivedAnswer;
import is.hi.hbv601.pubquiz.model.Team;
import is.hi.hbv601.pubquiz.service.interfaces.AnswerServiceInt;
import is.hi.hbv601.pubquiz.service.interfaces.QuestionServiceInt;
import is.hi.hbv601.pubquiz.service.interfaces.QuizServiceInt;
import is.hi.hbv601.pubquiz.service.interfaces.TeamServiceInt;
import javassist.NotFoundException;

@RestController
public class RESTResponseController
{

	@Autowired
	AnswerServiceInt answerService;

	@Autowired
	QuestionServiceInt questionService;
	
	@Autowired
	QuizServiceInt quizService;

	@Autowired
	TeamServiceInt teamService;

	/**
	 * This function registers the answer from the team for x answer in y quiz.
	 * 
	 * @param jsonString
	 *            The JSON string received.
	 * @return HTTP status of 200 if successful, 400 if the request was invalid.
	 */
	@RequestMapping(value = "/api/answer", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<HttpStatus> saveAnswer(@RequestBody ReceivedAnswer jsonString)
	{
		boolean result = answerService.saveAnswer(jsonString);
		if (result)
		{
			new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * This function fetches the latest question if the data checks out.
	 * 
	 * @param jsonString
	 *            The JSON string received.
	 * @return Question related to data given.
	 * @throws NotFoundException 
	 * @throws AccessDeniedException 
	 */
	@RequestMapping(value = "/api/question", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Question fetchQuestion(@RequestBody FetchQuestionWrapper jsonString) throws NotFoundException, AccessDeniedException
	{
		//TODO: Discuss whether we want to send quizId via JSON or just have it in the URL and send over the phoneId for validation.
		Quiz quiz = quizService.findQuizById(jsonString.getQuiz_id());
		if(teamService.doesPhoneIdExistForQuiz(jsonString.getPhone_id(), quiz))
			return quizService.fetchQuestion(jsonString.getQuiz_id());
		throw new AccessDeniedException("You do not have access to this quiz's questions.");
	}

	/**
	 * This function registers the team.
	 * 
	 * @param jsonString
	 *            The JSON string received.
	 * @return A more detailed team object.
	 * @throws AccessDeniedException 
	 */
	@RequestMapping(value = "/api/register_team", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody NewTeamReturn registerTeam(@RequestBody Team jsonString) throws AccessDeniedException
	{
		List<Quiz> quizzes = quizService.findByRoomName(jsonString.getRoom_name());
		NewTeamReturn resultString = teamService.registerTeam(jsonString, activeQuiz(quizzes));
		return resultString;
	}
	
	/**
	 * Checks what quiz is currently active from the given list of quizzes.
	 * 
	 * @param quizzes The list to be checked.
	 * @return The quiz that is active.
	 */
	private Quiz activeQuiz(List<Quiz> quizzes) {
		//TODO: Check which quiz with the given room name is currently active.
		return quizzes.get(0);
	}

}