package fundingchain.models;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=30, unique=true)
	private String username;
	
	@Column(length=60)
	private String password;
	
	@Column(length=100)
	private String fullName;

	@Column(length = 100)
	private String email;
	
	@OneToMany(mappedBy = "owner")
	private Set<Project> projects = new HashSet<Project>();
	
	@OneToMany(mappedBy = "funder")
	private List<Funding> fundings = new ArrayList<Funding>();
	
	@ManyToMany()
	private List<Role> roles = new ArrayList<Role>();

	@OneToOne
	private Wallet wallet;

	@Column(nullable = false, columnDefinition="TINYINT")
	private boolean hasPicture;

	public Long getId() {
		return id; 
		}
    public void setId(Long id) { 
    	this.id = id; 
    	}
    public String getUsername() { 
    	return username; 
    	}
    public void setUsername(String username) { 
    	this.username = username; 
    	}
    public String getPassword() { 
    	return password; 
    	}
    public void setPassword(String password) { 
    	this.password = password; 
    	}
    public String getFullName() { 
    	return fullName; 
    	}
    public void setFullName(String fullName) { 
    	this.fullName = fullName; 
    	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Project> getProjects() {
    	return projects; 
    	}
    public void setProjects(Set<Project> projects) { 
    	this.projects = projects; 
    }
    public List<Funding> getFundings(){
    	return this.fundings;
    }
    public void setFundings(List<Funding> fundings){
    	this.fundings = fundings;
    }
    public void addFunding(Funding funding){
    	this.fundings.add(funding);
    }
    public List<Role> getRoles(){
    	return this.roles;
    }
    public void setRoles(List<Role> roles){
    	this.roles = roles;
    }
    public void addRole(Role role){
    	this.roles.add(role);
    }
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public boolean isHasPicture() {
		return hasPicture;
	}

	public void setHasPicture(boolean hasPicture) {
		this.hasPicture = hasPicture;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' +
				", passwordHash='" + password + '\'' +
				", fullName='" + fullName + '\'' + '}';
	}
}