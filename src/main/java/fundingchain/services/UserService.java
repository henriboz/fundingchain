package fundingchain.services;
import java.util.List;


import fundingchain.models.Funding;
import fundingchain.models.User;
import fundingchain.models.Wallet;


public interface UserService{
    List<User> findAll();
    User findUserById(Long id);
    User findUserByUsername(String username);
    User findUserByFunding(Funding f);
    List<Funding> findFundingsByUser(User user);
    User create(User user);
    Wallet create(Wallet wallet);
    User edit(User user);
    Wallet edit(Wallet wallet);
    void deleteById(Long id);
    //public boolean authenticate(String username, String password);
}