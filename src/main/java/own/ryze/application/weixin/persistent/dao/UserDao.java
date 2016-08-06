package own.ryze.application.weixin.persistent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import own.ryze.application.weixin.persistent.bean.User;

@RepositoryRestResource(path = "/users", collectionResourceRel = "/users")
public interface UserDao extends PagingAndSortingRepository<User, Long>
{
	User findByUsernameAndPassword(String username, String password);

	List<User> findAll();

}
