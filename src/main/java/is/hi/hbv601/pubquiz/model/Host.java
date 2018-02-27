/**
 * Represents a quiz's host
 * 
 * @author Viktor Alex Brynjarsson vab18@hi.is
 * @date 27. feb. 2018
 */
package is.hi.hbv601.pubquiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "\"host\"")
public class Host
{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	private String name;

	@Email
	private String email;

	@Transient
	private String password;

	private int active;

	private String role;

	public Host()
	{

	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getActive()
	{
		return active;
	}

	public void setActive(int active)
	{
		this.active = active;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
