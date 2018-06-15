package fundingchain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping("/")
    public String index(Model model) {
		/*List<Project> latest5Projects = projectService.findLatest5();
		model.addAttribute("latest5Projects", latest5Projects);

		List<Project> latest3Projects = latest5Projects.stream().limit(3).collect(Collectors.toList());
		model.addAttribute("latest3Projects", latest3Projects);
		
		//A1uthentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //String name = auth.getName(); //get logged in username
		//System.out.println(securityService.findLoggedInUsername());*/
		return "index";
		
    }
}