/**
 * RESTResponseController reacts to JSON requests.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 11. feb. 2018
 */

package is.hi.hbv601.restServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import is.hi.hbv601.restServer.model.Answer;
import is.hi.hbv601.restServer.model.FetchQuestionWrapper;
import is.hi.hbv601.restServer.model.Question;
import is.hi.hbv601.restServer.model.Team;
import is.hi.hbv601.restServer.service.interfaces.AnswerServiceInt;
import is.hi.hbv601.restServer.service.interfaces.QuestionServiceInt;
import is.hi.hbv601.restServer.service.interfaces.TeamServiceInt;

@RestController
public class RESTResponseController {
	
	@Autowired
	AnswerServiceInt answerService;
	
	@Autowired
	QuestionServiceInt questionService;
	
	@Autowired
	TeamServiceInt teamService;

    /**
     * This function registers the answer from the team for x answer in y quiz.
     * 
     * @param jsonString The JSON string received.
     * @return HTTP status of 200 if successful, 400 if the request was invalid.
     */
    @RequestMapping(value = "/answer", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<HttpStatus> saveAnswer(@RequestBody Answer jsonString) {
        return answerService.checkData(jsonString);
    }
    
    /**
     * This function fetches the latest question if the data checks out.
     * 
     * @param jsonString The JSON string received.
     * @return Question related to data given.
     */
    @RequestMapping(value = "/question", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Question fetchQuestion(@RequestBody FetchQuestionWrapper jsonString) {
        return questionService.getQuestionFromQuiz(jsonString);
    }
    
    /**
     * This function registers the team.
     * 
     * @param jsonString The JSON string received.
     * @return HTTP status of 201 if successful and a relevant JSON object, 403 if the team already exists.
     */
    @RequestMapping(value = "/register_team", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<?> registerTeam(@RequestBody Team jsonString) {
        return teamService.registerTeam(jsonString);
    }
    
}