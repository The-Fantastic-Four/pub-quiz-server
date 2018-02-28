/**
 * Index page
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 27. feb. 2018
 */
package is.hi.hbv601.pubquiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController
{
	// Index page for the whole site, shows links to other pages
	@RequestMapping("")
	public String indexPage()
	{
		return "welcome/index";
	}
}
