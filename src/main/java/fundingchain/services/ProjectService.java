package fundingchain.services;

import fundingchain.models.Funding;
import fundingchain.models.Project;
import fundingchain.models.Reward;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    List<Project> findLatest6();
    Project findById(Long id);
    Project create(Project project);
    Project edit(Project project);
    void deleteById(Long id);
    List<Funding> findLatest6Fundings(Project p);
    Reward findReward(Project p);
    Reward create(Reward reward);
    Funding create(Funding funding);
}