/**
 * Service for managing hosts
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 27. feb. 2018
 */
package is.hi.hbv601.pubquiz.service.interfaces;

import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.Host;

@Service("userService")
public interface HostServiceInt
{
	public Host findHostByEmail(String email);
	
	public void saveHost(Host host);
}
