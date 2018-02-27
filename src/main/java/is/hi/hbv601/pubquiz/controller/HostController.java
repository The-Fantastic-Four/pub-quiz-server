/**
 * User management controller
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 27. feb. 2018
 */
package is.hi.hbv601.pubquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.service.HostService;

@Controller
public class HostController
{
	@Autowired
	HostService hostService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login()
	{
		return new ModelAndView("host/login");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registrationForm()
	{
		return new ModelAndView("host/registration"); 
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registration(
			@RequestParam String email, String password, String confirmPassword, String name, ModelMap model)
	{
		Host host = new Host();
		host.setEmail(email);
		host.setPassword(password);
		host.setName(name);
		
		ModelAndView modelAndView = new ModelAndView("host/registration");
		modelAndView.addObject("email", email);
		modelAndView.addObject("name", name);
		
		Host hostExists = hostService.findHostByEmail(email);
		if (hostExists == null)
		{
			hostService.saveHost(host);
			modelAndView.addObject("successMessage", "Registration successful");
		}
		
		return modelAndView;
	}
}
