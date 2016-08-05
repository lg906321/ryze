package own.ryze.application.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import own.ryze.application.weixin.persistent.bean.User;
import own.ryze.application.weixin.persistent.dao.UserDao;
import own.ryze.application.weixin.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(String username, String password)
	{
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> getAll()
	{
		return userDao.findAll();
	}

	@Override
	public User create(User user)
	{
		return userDao.save(user);
	}

}
