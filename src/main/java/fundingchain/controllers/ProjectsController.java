package fundingchain.controllers;
import fundingchain.forms.FundingForm;
import fundingchain.forms.ProjectForm;
import fundingchain.services.*;
import fundingchain.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProjectsController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping(value="/projects/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model,FundingForm fundingForm) {
        Project project = projectService.findById(id);
        if (project == null) {
            notifyService.addErrorMessage("Cannot find project #" + id);
            return "redirect:/";
        }
        List<Funding> fundings = projectService.findLatest6Fundings(project);
        if (fundings.size() > 0) model.addAttribute("fundings", fundings);

        model.addAttribute("reward", projectService.findReward(project));

        model.addAttribute("project", project);
        return "projects/view";
    }

    @RequestMapping(value = "/projects/view/{id}", method = RequestMethod.POST)
    public String view(@PathVariable("id") Long id, @Valid FundingForm fundingForm, BindingResult bindingResult, Model model ){
        Project project = projectService.findById(id);
        if (project == null) {
            notifyService.addErrorMessage("Cannot find project #" + id);
            return "redirect:/";
        }
        double fundingValue = fundingForm.getValue();
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        Wallet wallet = user.getWallet();
        if (wallet.getMoney() < fundingValue)
        {
            notifyService.addErrorMessage("You don't have enough money to fund this project!");
            return "redirect:/projects/view/"+id;
        }

        wallet.setMoney(wallet.getMoney() - fundingValue);
        userService.edit(wallet);
        Funding funding = new Funding();
        funding.setFunder(user);
        funding.setFundingdate(new Date());
        funding.setProject(project);
        funding.setValue(fundingValue);
        projectService.create(funding);
        notifyService.addInfoMessage("You have successfully funded this project!");
        return "redirect:/projects/view/"+id;
    }

    @RequestMapping("/projects")
    public String view(Model model){
      List<Project> projects = projectService.findLatest6();
      if (projects == null) {
          notifyService.addErrorMessage("There are no registered projects.");
          return "redirect:/";
      }
      model.addAttribute("projects", projects);

      return "projects/index";
    }

    @RequestMapping(value = "/projects/create", method = RequestMethod.GET)
    public String create(ProjectForm projectForm){
        return "/projects/create";
    }

    @RequestMapping(value = "/projects/create", method = RequestMethod.POST)
    public String create(@Valid ProjectForm projectForm, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill ALL the information correctly!");
            return "/projects/create";
        }

        Reward reward = new Reward();
        reward.setLowervalue(projectForm.getLowerReward());
        reward.setLowerDesc(projectForm.getLowerRewardDesc());
        reward.setMidvalue(projectForm.getMidReward());
        reward.setMidDesc(projectForm.getMidRewardDesc());
        reward.setUppervalue(projectForm.getUpperReward());
        reward.setUpperDesc(projectForm.getUpperRewardDesc());

        User user = userService.findByUsername(securityService.findLoggedInUsername());

        Project project = new Project();
        project.setTile((projectForm.getTitle()));
        project.setDescription(projectForm.getDescription());
        project.setAbout(projectForm.getAbout());
        project.setActive(true);
        project.setFundingValue(projectForm.getTargetValue());
        project.setCreationdate(new Date());
        /*
        The is taken as a String from the View - Couldn't make it come as a Date() object.
        Then it's converted to Date and added by 86399000 milliseconds (23 hours, 59 minutes, 59 seconds).
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = formatter.parse(projectForm.getDueDate());
            d.setTime(d.getTime() + 86399000);
            project.setDuedate(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        project.setReward(reward);
        project.setOwner(user);

        projectService.create(project);

        notifyService.addInfoMessage("Project created successfully!");
        return "redirect:/projects/";
    }
}
