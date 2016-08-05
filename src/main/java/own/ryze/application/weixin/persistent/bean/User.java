package own.ryze.application.weixin.persistent.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(length = 11)
	private String mobile;
	
}
