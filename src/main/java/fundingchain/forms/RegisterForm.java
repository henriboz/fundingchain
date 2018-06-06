package fundingchain.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {
    @Size(min=2, max=30, message = "Full name size should be in the range [2...100]")
    private String username;
    
    @Size(min=2, max=100, message = "Full name should be in the range [2...100]")
    private String fullname;
    
    @NotNull
    @Size(min=1, max=50)
    private String password;
    
    @NotNull
    @Size(min=1, max=50)
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