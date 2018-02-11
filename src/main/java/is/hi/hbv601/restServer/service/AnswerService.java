/**
 * AnswerSerivce is an implementation of the AnswerServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */

package is.hi.hbv601.restServer.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import is.hi.hbv601.restServer.model.Answer;
import is.hi.hbv601.restServer.service.interfaces.AnswerServiceInt;

@Service
public class AnswerService implements AnswerServiceInt{
	public ResponseEntity<HttpStatus> checkData(Answer data) {
		boolean valid = true;
		//TODO: Check if the JSON string is correct. 
		if(valid) {
			saveData(data);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Saves the data.
	 * 
	 * @param data The data to be saved.
	 */
	private void saveData(Answer data) {
		//TODO: Save into database.
		System.out.println("====================");
		System.out.println(data.getAnswer());
		System.out.println("Saving to DB");
		System.out.println("====================");
	}
}
