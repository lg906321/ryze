package own.ryze.application.weixin.service;

import java.util.List;

import own.ryze.application.weixin.persistent.bean.User;

public interface UserService
{
	User login(String mobile,String password);
	
	User getByMobile(String mobile);
	
	List<User> getAll();
	
	User create(User user);
	
	User moidfy(User user);
	
	void remove(String mobile);
}
