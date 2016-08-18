package own.ryze.application.weixin.persistent.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import own.ryze.application.weixin.validator.annotation.Mobile;
import own.ryze.application.weixin.validator.group.Group;

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
	@NotNull(message = "{id.null}", groups = { Group.Modify.class })
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "{username.blank}", groups = { Group.Create.class, Group.Modify.class})
	@ApiModelProperty("用户名")
	private String username;
	@Column(nullable = false)
	@NotBlank(message = "{password.blank}", groups = { Group.Create.class, Group.Modify.class, Group.Login.class })
	@ApiModelProperty("密码")
	private String password;
	@Column(length = 11,nullable = false,updatable = false)
	@Mobile(groups = {Group.Create.class, Group.Login.class })
	@ApiModelProperty("手机号")
	private String mobile;
	@Column(nullable = false, updatable = false)
	@ApiModelProperty("创建时间")
	@CreationTimestamp
	private Date createtime;
	@Column(nullable = false)
	@ApiModelProperty("更新时间")
	@UpdateTimestamp
	private Date updatetime;

}
