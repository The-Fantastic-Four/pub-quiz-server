/**
 * AnswerServiceInt is an interface for service management for
 * answer related services.
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */
package is.hi.hbv601.pubquiz.service.interfaces;

import is.hi.hbv601.pubquiz.model.ReceivedAnswer;

public interface AnswerServiceInt {
	
	/**
	 * Checks whether the data is valid and if so saves it.
	 * 
	 * @param data The data to be checked.
	 * @return True if successful; false if unsuccessful.
	 */
	public boolean saveAnswer(ReceivedAnswer data);

}
