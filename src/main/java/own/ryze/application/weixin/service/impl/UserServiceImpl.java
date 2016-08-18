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
	public User login(final String mobile, final String password)
	{
		return userDao.findByMobileAndPassword(mobile, password);
	}
	
	@Override
	public User getByMobile(final String mobile)
	{
		return userDao.findByMobile(mobile);
	}

	@Override
	public List<User> getAll()
	{
		return userDao.findAll();
	}

	@Override
	public User create(final User user)
	{
		return userDao.save(user);
	}
	
	@Override
	public User moidfy(final User user)
	{
		return userDao.save(user);
	}


	@Override
	public void remove(final String mobile)
	{
		userDao.delete(mobile);
	}

}
