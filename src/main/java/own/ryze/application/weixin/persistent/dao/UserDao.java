package own.ryze.application.weixin.persistent.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import own.ryze.application.weixin.persistent.bean.User;

@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<User, Long>
{
	@Cacheable(key = "#p0")
	User findByMobileAndPassword(String mobile, String password);
	
	@Cacheable(key = "#p0")
	User findByMobile(String mobile);

	List<User> findAll();

	@SuppressWarnings("unchecked")
	@CachePut(key = "#p0.mobile")
	User save(User user);
	
	@CacheEvict(key = "#p0")
	@Modifying
	@Query("DELETE FROM User WHERE mobile = :mobile")
	void delete(@Param(value = "mobile") String mobile);
}
