package own.ryze.application.weixin.persistent.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7072319710631668814L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "{user.username.blank}")
	private String username;
	@Column(nullable = false)
	@NotBlank(message = "{user.password.blank}")
	private String password;
	@Column(length = 11)
	private String mobile;
	
}
