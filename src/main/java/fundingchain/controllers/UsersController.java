package fundingchain.controllers;
import fundingchain.services.*;
import fundingchain.forms.*;
import fundingchain.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private NotificationService notifyService;

	/*@RequestMapping("/users/login")
	public String login(LoginForm loginForm) {
		return "users/login";
	}*/
	
	@RequestMapping(value = "/users/register", method = RequestMethod.GET)
    public String registration(RegisterForm registerForm) {
        return "users/register";
    }
	
	 @RequestMapping(value = "/users/register", method = RequestMethod.POST)
	 public String registration(@Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {

	     if (bindingResult.hasErrors()) {
	    	 notifyService.addErrorMessage("Please fill the form correctly!");
	    	 return "users/register";
	     }

	     Wallet wallet = new Wallet();
	     wallet.setMoney(0.0);
	     userService.create(wallet);

	     User user = new User();
	     user.setUsername(registerForm.getUsername());
	     user.setPassword(registerForm.getPassword());
	     user.setFullName(registerForm.getFullname());
	     user.setWallet(wallet);
	     userService.create(user);

	     securityService.autologin(user.getUsername(), user.getPassword());
	     notifyService.addInfoMessage("User Registered correctly!");
	     return "index";
	 }

	 @RequestMapping("/users/login")
	 public String login(LoginForm loginForm) {
		 return "users/login";
	 }

	 @RequestMapping(value="/users/logout", method = RequestMethod.POST)
	 public String logout (HttpServletRequest request, HttpServletResponse response) {
		 notifyService.addInfoMessage("User logged out!");
	     securityService.logOut(request, response);

		 return "redirect:/index";
	 }
}
