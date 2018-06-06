package fundingchain.repositories;

import fundingchain.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.query.Param;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//@Query("SELECT u FROM User u where u.username = :username")
	User findByUsername(String username);
	//User findByUsername(String username);
}