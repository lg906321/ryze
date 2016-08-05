package own.ryze.application.weixin.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import own.ryze.application.weixin.common.PortReturn;
import own.ryze.application.weixin.constant.WebConstant;
import own.ryze.application.weixin.enums.Return;
import own.ryze.application.weixin.persistent.bean.User;
import own.ryze.application.weixin.service.UserService;

@RestController
@RequestMapping("users")
@Api("用户管理")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "登录",notes = "用户名密码登录",response = User.class)
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public Map<String,Object> login(@RequestBody @Valid @ApiParam(value = "输入用户名、密码",required = true)User user,BindingResult br)
	{
		User data = userService.login(user.getUsername(), user.getPassword());
		
		PortReturn.put(WebConstant.DATA, data);
		
		return PortReturn.returnJSON(Return.SUCCESS);
	}
	
	@ApiOperation(value = "用户列表",notes = "用户列表",response = User.class)
	@RequestMapping(method = RequestMethod.GET)
	public Map<String,Object> all()
	{
		List<User> datalist = userService.getAll();
		
		PortReturn.put(WebConstant.DATA, datalist);
		
		return PortReturn.returnJSON(Return.SUCCESS);
	}
	
	@ApiOperation(value = "创建用户",notes = "创建用户",response = User.class)
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String,Object> create(@RequestBody @ApiParam(value = "用户视图",required = true)User user)
	{
		User data = userService.create(user);
		
		PortReturn.put(WebConstant.DATA,data);
		
		return PortReturn.returnJSON(Return.SUCCESS);
	}
	
}
