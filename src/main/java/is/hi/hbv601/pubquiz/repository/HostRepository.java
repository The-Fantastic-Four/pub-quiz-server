/**
 * Repository for quiz hosts
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 27. feb. 2018
 */
package is.hi.hbv601.pubquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import is.hi.hbv601.pubquiz.model.Host;

public interface HostRepository extends JpaRepository<Host, Long>
{
	Host findByEmail(String email);
	
	@SuppressWarnings("unchecked")
	Host save(Host host);
}
