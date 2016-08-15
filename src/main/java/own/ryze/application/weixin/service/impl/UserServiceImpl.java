package own.ryze.application.weixin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import own.ryze.application.weixin.persistent.bean.User;
import own.ryze.application.weixin.persistent.dao.UserDao;
import own.ryze.application.weixin.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(String mobile, String password)
	{
		return userDao.findByMobileAndPassword(mobile, password);
	}
	
	@Override
	public User getByMobile(String mobile)
	{
		return userDao.findByMobile(mobile);
	}

	@Override
	public List<User> getAll()
	{
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User create(User user)
	{
		Date date = new Date();
		user.setCreatetime(date);
		user.setUpdatetime(date);
		
		return userDao.save(user);
	}
	
	@Override
	@Transactional
	public User moidfy(User user)
	{
		user.setUpdatetime(new Date());
		
		return userDao.save(user);
	}


	@Override
	@Transactional
	public void remove(String mobile)
	{
		userDao.delete(mobile);
	}

}
