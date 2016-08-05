package own.ryze.application.weixin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import own.ryze.application.weixin.common.PortReturn;
import own.ryze.application.weixin.enums.Return;
import own.ryze.application.weixin.persistent.bean.User;
import own.ryze.application.weixin.service.UserService;

@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login(@RequestBody User user)
	{
		User data = userService.login(user.getUsername(), user.getPassword());
		
		PortReturn.put("data", data);
		
		return PortReturn.returnJSON(Return.SUCCESS);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String all()
	{
		List<User> datalist = userService.getAll();
		
		PortReturn.put("data", datalist);
		
		return PortReturn.returnJSON(Return.SUCCESS);
	}
}
