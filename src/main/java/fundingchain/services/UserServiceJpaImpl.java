package fundingchain.services;

import fundingchain.models.Role;
import fundingchain.models.User;
import fundingchain.models.Wallet;
import fundingchain.repositories.RoleRepository;
import fundingchain.repositories.UserRepository;
import fundingchain.repositories.WalletRepository;
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

	@Autowired
	private WalletRepository walletRepository;

	@Override
	public List<User> findAll() {
		return this.userRepo.findAll();
	}

	@Override
	public User findById(Long id) {
		return this.userRepo.findOne(id);
	}

	@Override
	public User findByUsername(String username){
		return this.userRepo.findByUsername(username);
	}

	@Override
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findOne(2L));
		user.setRoles(roles);
		return this.userRepo.save(user);
	}

	@Override
	public Wallet create (Wallet wallet){
		return walletRepository.save(wallet);
	}

	@Override
	public User edit(User user) {
		return this.userRepo.save(user);
	}

	@Override
	public Wallet edit(Wallet wallet){
		return walletRepository.save(wallet);
	}

	@Override
	public void deleteById(Long id) {
		this.userRepo.delete(id);
	}


}