/**
 * Service for managing hosts
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 27. feb. 2018
 */
package is.hi.hbv601.pubquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import is.hi.hbv601.pubquiz.model.Host;
import is.hi.hbv601.pubquiz.repository.HostRepository;
import is.hi.hbv601.pubquiz.service.interfaces.HostServiceInt;

@Service
public class HostService implements HostServiceInt
{

	@Autowired
	private HostRepository hostRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Host findHostByEmail(String email)
	{
		return hostRepository.findByEmail(email);
	}

	@Override
	public void saveHost(Host host)
	{
		host.setPassword(bCryptPasswordEncoder.encode(host.getPassword()));
		host.setActive(1);
		host.setRole("GENERAL");

		hostRepository.save(host);
	}

}
