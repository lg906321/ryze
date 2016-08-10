package own.ryze.application.weixin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import own.ryze.application.weixin.common.PortReturn;
import own.ryze.application.weixin.enums.Return;
import own.ryze.application.weixin.persistent.bean.User;
import own.ryze.application.weixin.service.UserService;
import own.ryze.application.weixin.validator.group.Group;

@RestController
@RequestMapping("users")
@Api(value = "用户管理", description = "用户管理")
public class UserController
{
	@Autowired
	private UserService userService;

	@ApiOperation(value = "登录", notes = "手机号密码登录<br/>" + "参数:<br/>" + "mobile - 手机号<br/>" + "password - 密码<br/>")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public PortReturn<User> login(
			@RequestBody @Validated(Group.Login.class) @ApiParam(value = "输入手机号、密码", required = true) User user,
			BindingResult br)
	{
		User data = userService.login(user.getMobile(), user.getPassword());

		return PortReturn.returnJSON(data, Return.SUCCESS);
	}

	@ApiOperation(value = "用户列表", notes = "用户列表")
	@RequestMapping(method = RequestMethod.GET)
	public PortReturn<List<User>> all()
	{
		List<User> datalist = userService.getAll();

		return PortReturn.returnJSON(datalist, Return.SUCCESS);

	}

	@ApiOperation(value = "创建用户", notes = "创建用户<br/>" + "username - 用户名<br/>" + "password - 密码<br/>"
			+ "mobile - 手机号<br/>")
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public PortReturn<User> create(
			@RequestBody @Validated(Group.Create.class) @ApiParam(value = "用户实体", required = true) User user,
			BindingResult br)
	{
		User data = userService.create(user);

		return PortReturn.returnJSON(data, Return.SUCCESS);
	}

	@ApiOperation(value = "更新用户", notes = "根据主键更新用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public PortReturn<User> modify(
			@RequestBody @Validated(Group.Modify.class) @ApiParam(value = "用户实体", required = true) User user,
			BindingResult br,@PathVariable Long id)
	{
		User data = userService.moidfy(user);

		return PortReturn.returnJSON(data, Return.SUCCESS);
	}

	@ApiOperation(value = "删除用户", notes = "根据手机号删除用户")
	@RequestMapping(value = "/{mobile}", method = RequestMethod.DELETE)
	public PortReturn<Object> remove(@PathVariable @ApiParam(value = "手机号", required = true) String mobile)
	{
		userService.remove(mobile);

		return PortReturn.returnJSON(Return.SUCCESS);
	}

}
