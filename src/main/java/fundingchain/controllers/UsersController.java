package fundingchain.controllers;
import fundingchain.services.*;
import fundingchain.forms.*;
import fundingchain.models.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
public class UsersController {

	@Value("${file.upload.directory}")
	private String UPLOAD_FOLDER;

	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private NotificationService notifyService;

    @Autowired
	private ProjectService projectService;

    @Autowired LedgerService ledgerService;

	private EtherService etherService;

	@RequestMapping(value = "/users/register", method = RequestMethod.GET)
    public String registration(RegisterForm registerForm) {
        return "users/register";
    }
	
	 @RequestMapping(value = "/users/register", method = RequestMethod.POST)
	 public String registration(@Valid RegisterForm registerForm, BindingResult bindingResult) {

	     if (bindingResult.hasErrors()) {
	    	 notifyService.addErrorMessage("Please fill the form correctly!");
	    	 return "users/register";
	     }

	     //Verifies first if username is unique. Don't fall into an exception
		 User existing_user = userService.findUserByUsername(registerForm.getUsername());
	     if(existing_user != null){
			 notifyService.addErrorMessage("The selected username is already in use. Please try another one.");
			 return "users/register";
		 }

	     User admin = userService.findUserByUsername("admin");
	     Wallet adminWallet = admin.getWallet();

	     Wallet wallet = new Wallet();
	     wallet.setMoney(100);
	     adminWallet.setMoney(adminWallet.getMoney() - 100);

		 User user = new User();
		 user.setUsername(registerForm.getUsername());
		 user.setPassword(registerForm.getPassword());
		 user.setFullName(registerForm.getFullname());
		 user.setEmail(registerForm.getEmail());
		 user.setWallet(wallet);
		 user.setHasPicture(false);

		 Ledger ledger = new Ledger();
		 ledger.setValue(100);
		 ledger.setFromUser(admin);
		 ledger.setToUser(user);
		 ledger.setDate(new Date());

		 etherService = new EtherServiceIml();

	     try{

	     	 //Created user at the DB
			 userService.create(wallet);
			 user = userService.create(user);
			 userService.edit(adminWallet);
			 ledgerService.create(ledger);

			 //Creates user at Ethereum with encrypted key
			 String publiKey = etherService.create(user.getPassword());
			 wallet.setPublicKey(publiKey);

			 userService.edit(wallet);
			 etherService.transfer(admin,user,100);
			 notifyService.addInfoMessage("User Registered correctly!");

		 }catch (Exception e){
			 System.out.println("ERROR - "+ e);
			 notifyService.addErrorMessage("There was an error creating your user. Please try again.");
		 }
	     //securityService.autologin(user.getUsername(), user.getPassword());
	     return "redirect:/";
	 }

	 @RequestMapping("/users/login")
	 public String login(LoginForm loginForm) {
		 return "/users/login";
	 }

	 @RequestMapping(value="/users/logout", method = RequestMethod.POST)
	 public String logout (HttpServletRequest request, HttpServletResponse response) {
		 notifyService.addInfoMessage("User logged out!");
	     securityService.logOut(request, response);
		 return "redirect:/";
	 }

	@RequestMapping("/users/{username}/fundedprojects")
	public String viewFundedProjects(@PathVariable("username") String username, Model model){
		//System.out.println(username);
		User user = userService.findUserByUsername(securityService.findLoggedInUsername());
		if (!user.getUsername().equals(username))
		{
			notifyService.addErrorMessage("You can't access other users!");
			return "redirect:/";
		}
		List<Funding> fundings = userService.findFundingsByUser(user);
		if (fundings.size() > 0) {
			for (Funding f : fundings) {
				Project p = projectService.findByFunding(f);
				f.setProject(p);
			}
			model.addAttribute("fundings", fundings);
		}
		//model.addAttribute("user", user);

		return "/users/fundedprojects";
	}

	@RequestMapping("/users/{username}/wallet")
	public String viewWallet(@PathVariable("username") String username, Model model){
		User user = userService.findUserByUsername(securityService.findLoggedInUsername());
		if (!user.getUsername().equals(username))
		{
			notifyService.addErrorMessage("You can't access other users!");
			return "redirect:/";
		}

		Wallet wallet = user.getWallet();
		model.addAttribute("wallet", wallet);

		List<Ledger> ledgers = ledgerService.findByUser(user);
		model.addAttribute("ledgers", ledgers);

		return "/users/wallet";
	}

	@RequestMapping("/users/{username}")
	public String view(@PathVariable("username") String username, Model model){

		User user = userService.findUserByUsername(username);
		if(user == null){
			notifyService.addErrorMessage("Could not find the requested user!");
			return "redirect:/";
		}
		List<Project> projects = projectService.findByOwner(user);
		if (projects.size() > 0) {
			model.addAttribute("projects", projects);
		}

		model.addAttribute("user", user);
		return "/users/index";
	}

	@RequestMapping(value = "/users/{username}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("username") String username,@Valid UserEditForm userEditForm, Model model) {
		//System.out.println(username);
		User user = userService.findUserByUsername(securityService.findLoggedInUsername());
		if (!user.getUsername().equals(username))
		{
			notifyService.addErrorMessage("You can't edit other users!");
			return "redirect:/";
		}
		model.addAttribute("user", user);
		return "/users/edit";
	}

	@RequestMapping(value = "/users/{username}/edit", method = RequestMethod.POST)
	public String edit(@Valid UserEditForm userEditForm, BindingResult bindingResult,
					   @RequestParam("file") MultipartFile file,@PathVariable("username") String username){

		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "redirect:/users/"+username+"/edit/";
		}
		//System.out.println(username);
		User user = userService.findUserByUsername(securityService.findLoggedInUsername());
		if (!user.getUsername().equals(username))
		{
			notifyService.addErrorMessage("You can't edit other users!");
			return "redirect:/users/"+user.getUsername();
		}
		user.setEmail(userEditForm.getEmail());
		user.setFullName(userEditForm.getFullname());

		try{
			if(!file.isEmpty()){
				//Saves file to directory
				byte[] bytes = file.getBytes();
				Path path = Paths.get( UPLOAD_FOLDER + "users//" +  user.getUsername()+".png");
				Files.write(path, bytes);
				user.setHasPicture(true);
			}
			userService.edit(user.getWallet());
			userService.edit(user);
		}catch (Exception e){
			notifyService.addErrorMessage("There was a problem saving your profile. Please try again.");
			System.out.println(e);
		}

		return "redirect:/users/"+user.getUsername();
	}
}
