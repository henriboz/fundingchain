package fundingchain.services;
import java.util.List;


import fundingchain.models.User;
import fundingchain.models.Wallet;


public interface UserService{
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User create(User user);
    Wallet create(Wallet wallet);
    User edit(User user);
    Wallet edit(Wallet wallet);
    void deleteById(Long id);
    //public boolean authenticate(String username, String password);
}