package own.ryze.application.weixin.persistent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import own.ryze.application.weixin.persistent.bean.User;

public interface UserDao extends JpaRepository<User, Long>
{
	User findByUsernameAndPassword(String username, String password);

	List<User> findAll();

}
