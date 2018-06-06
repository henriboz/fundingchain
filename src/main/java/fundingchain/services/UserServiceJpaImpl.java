package fundingchain.services;

import fundingchain.models.Role;
import fundingchain.models.User;
import fundingchain.repositories.RoleRepository;
import fundingchain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserServiceJpaImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<User> findAll() {
		return this.userRepo.findAll();
	}
	
	public User findById(Long id) {
		return this.userRepo.findOne(id);
	}
	
	public User findByUsername(String username){
		return this.userRepo.findByUsername(username);
	}
	
	
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findOne(2L));
		user.setRoles(roles);
		return this.userRepo.save(user);
	}
	
	public User edit(User user) {
		return this.userRepo.save(user);
	}
	
	
	public void deleteById(Long id) {
		this.userRepo.delete(id);
	}
	
	
	/*public boolean authenticate(String username, String password){
		System.out.println("-------------User Service Authenticate");
		User user = this.userRepo.findByUsername(username);
		if (user != null){
			if (user.getPasswordHash().equals(password)) {
				return true;
			}
			else return false;
		}
		else return false;		
	}*/
	
	/*@Override
	public UserDetails loadUserByUsername(String username){
		User user = this.userRepo.findByUnsername(username);
        //if (user != null) {
            //throw new UsernameNotFoundException(username);
        	return new MyUserDetails(user);
        //}
        
	}*/

}