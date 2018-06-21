package fundingchain.services;

import fundingchain.models.Funding;
import fundingchain.models.Project;
import fundingchain.models.Reward;
import fundingchain.models.User;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    List<Project> findAllActive();
    List<Project> findLatest6();
    List<Project> findLatest6Active();
    List<Project> findExpiredProjects();
    List<Project> findByOwner(User u);
    Project findById(Long id);
    Project findByFunding(Funding f);
    Project create(Project project);
    Project edit(Project project);
    void deleteById(Long id);
    List<Funding> findAllFundings(Project p);
    List<Funding> findLatest6Fundings(Project p);
    Funding findByFunderAndDate(User u, Date d);
    Reward findReward(Project p);
    Reward create(Reward reward);
    Funding create(Funding funding);

}