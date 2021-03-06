/**
 * Class which contains the main function to run the program
 * 
 * @author Eiður Örn Gunnarsson eog26@hi.is
 * @date 10. feb. 2018
 */
package is.hi.hbv601.pubquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PubQuizServerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(PubQuizServerApplication.class, args);
	}
}
