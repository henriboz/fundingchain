package fundingchain.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserEditForm {

    @Size(min=2, max=100, message = "Full name should be in the range [2...100]")
    private String fullname;

    @Size(min=5, max=100, message = "Email should be in the range [5..100]")
    @Email (message = "Invalid email.")
    private String email;

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

}
