/**
 * RESTResponseController reacts to JSON requests.
 *
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 15. feb. 2018
 */

package is.hi.hbv601.pubquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import is.hi.hbv601.pubquiz.model.FetchQuestionWrapper;
import is.hi.hbv601.pubquiz.model.Question;
import is.hi.hbv601.pubquiz.model.ReceivedAnswer;
import is.hi.hbv601.pubquiz.model.Team;
import is.hi.hbv601.pubquiz.service.interfaces.AnswerServiceInt;
import is.hi.hbv601.pubquiz.service.interfaces.QuestionServiceInt;
import is.hi.hbv601.pubquiz.service.interfaces.TeamServiceInt;
import is.hi.hbv601.pubquiz.service.TeamService;
import is.hi.hbv601.pubquiz.model.Quiz;
import is.hi.hbv601.pubquiz.service.interfaces.QuizServiceInt;

@RestController
public class RESTResponseController {

	@Autowired
	AnswerServiceInt answerService;

	@Autowired
	QuestionServiceInt questionService;

	@Autowired
	TeamServiceInt teamService;

	@Autowired
	QuizServiceInt quizService;




    /**
     * This function registers the answer from the team for x answer in y quiz.
     *
     * @param jsonString The JSON string received.
     * @return HTTP status of 200 if successful, 400 if the request was invalid.
     */
    @RequestMapping(value = "/api/answer", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<HttpStatus> saveAnswer(@RequestBody ReceivedAnswer jsonString) {
    	System.out.println(jsonString);
        boolean result = answerService.saveAnswer(jsonString);
        if(result) {
        	new ResponseEntity<>(HttpStatus.OK);
        }
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * This function fetches the latest question if the data checks out.
     *
     * @param jsonString The JSON string received.
     * @return Question related to data given.
     */
    @RequestMapping(value = "/api/question", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Question fetchQuestion(@RequestBody FetchQuestionWrapper jsonString) {
    	System.out.println(jsonString);
        return questionService.getQuestionFromQuiz(jsonString);
    }

    /**
     * This function registers the team.
     *
     * @param jsonString The JSON string received.
     * @return HTTP status of 201 if successful and a relevant JSON object, 403 if the team already exists.
     */
    @RequestMapping(value = "/api/register_team", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<?> registerTeam(@RequestBody Team jsonString) {
    	System.out.println(jsonString);
    	String response = teamService.registerTeam(jsonString);
			if(response) {
				new ResponseEntity<>(HttpStatus.OK);
			}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

		/**
     * This function registers the team.
     *
     * @param jsonString The JSON string received.
     * @return Team that was in quiz
     */
    @RequestMapping(value = "/api/phone", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<?> registerTeam(@RequestBody Team jsonString) {
    	Team resultteam = teamService.findTeamByAndroidId(jsonString);
			return resultteam;
    }


		/**
		* This function deletes team from quiz
		* @param jsonString The JSON string received.
		*/
		@RequestMapping(value = "/api/remove_team", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<?> registerTeam(@RequestBody Team jsonString) {
				Team team = TeamService.findTeamByAndroidId(jsonString);
				team.deleteTeam(team.getPhone_id);
				if (resultString.isEmpty()) {
					new ResponseEntity<>(HttpStatus.OK);
				}
	    	return new ResponseEntity<String>(resultString, HttpStatus.BAD_REQUEST);
		}
}
