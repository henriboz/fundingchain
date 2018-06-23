package fundingchain.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {
    @Size(min=2, max=30, message = "Username size should be in the range [2...30]")
    @NotNull (message = "Please provide a username to register")
    private String username;
    
    @Size(min=2, max=100, message = "Full name should be in the range [2...100]")
    @NotNull(message = "Please provide your full name")
    private String fullname;

    @Size(min=5, max=100, message = "Email should be in the range [5..100]")
    @Email (message = "Invalid email.")
    @NotNull (message = "An email is required to register.")
    private String email;
    
    @NotNull (message = "Please provide a password.")
    @Size(min=6, max=30, message = "Your password should be in the range [6..30]")
    private String password;
    
    @NotNull (message = "Please confirm your password")
    @Size(min=6, max=30)
    private String confirmpassword;
    
    private void checkPassword() {
        if(this.password == null || this.confirmpassword == null){
            return;
        }else if(!this.password.equals(confirmpassword)){
            this.confirmpassword = null;
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname(){
    	return fullname;
    }
    
    public void setFullname(String fullname){
    	this.fullname = fullname;
    }
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }
    public String getConfirmpassword(){
    	return this.confirmpassword;
    }
    
    public void setConfirmpassword(String confirmpassword){
    	this.confirmpassword = confirmpassword;
    	checkPassword();
    }
}