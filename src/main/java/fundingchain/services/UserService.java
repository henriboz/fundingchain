package fundingchain.services;
import java.util.List;


import fundingchain.models.User;


public interface UserService{
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
    //public boolean authenticate(String username, String password);
}