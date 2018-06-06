package fundingchain.controllers;
import fundingchain.services.*;
import fundingchain.models.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ProjectsController {
    @Autowired
    private ProjectService projectService;


    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/projects/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Project project = projectService.findById(id);
        if (project == null) {
            notifyService.addErrorMessage("Cannot find project #" + id);
            return "redirect:/";
        }
        model.addAttribute("project", project);
        return "projects/view";
    }

    @RequestMapping("/projects")
    public String view(Model model){
      List<Project> projects = projectService.findAll();
      if (projects == null) {
          notifyService.addErrorMessage("There are no registered projects.");
          return "redirect:/";
      }
      model.addAttribute("projects", projects);
      
      List<Project> latest5Projects = projectService.findLatest5();
      model.addAttribute("latest5Projects", latest5Projects);

      return "projects/index";
    }
}
