/**
 * AnswerSerivce is an implementation of the AnswerServiceInt
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 15. feb. 2018
 */

package is.hi.hbv601.pubquiz.service;

import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.ReceivedAnswer;
import is.hi.hbv601.pubquiz.service.interfaces.AnswerServiceInt;

@Service
public class AnswerService implements AnswerServiceInt
{
	public boolean saveAnswer(ReceivedAnswer data)
	{
		boolean valid = true;
		// TODO: Check if the JSON string is correct.
		if (valid)
		{
			saveData(data);
			return true;
		}
		return false;
	}

	/**
	 * Saves the data.
	 * 
	 * @param data
	 *            The data to be saved.
	 */
	private void saveData(ReceivedAnswer data)
	{
		// TODO: Save into database.
		System.out.println("====================");
		System.out.println(data.getAnswer());
		System.out.println("Saving to DB");
		System.out.println("====================");
	}
}
