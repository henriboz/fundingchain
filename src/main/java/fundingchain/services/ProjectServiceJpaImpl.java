package fundingchain.services;

import fundingchain.models.Funding;
import fundingchain.models.Project;
import fundingchain.models.Reward;
import fundingchain.repositories.FundingRepository;
import fundingchain.repositories.ProjectRepository;
import fundingchain.repositories.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProjectServiceJpaImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private FundingRepository fundingRepo;

	@Autowired
	private RewardRepository rewardRepository;
	
	@Override
	public List<Project> findAll() {
		return this.projectRepo.findAll();
	}
	
	@Override
	public List<Project> findLatest6() {
		return this.projectRepo.findLatest6Projects(new PageRequest(0, 6));
	}

	@Override
	public Project findById(Long id) {
		return this.projectRepo.findOne(id);
	}
	
	@Override
	public Project create(Project project) {
		return this.projectRepo.save(project);
	}
	
	@Override
	public Project edit(Project project) {
		return this.projectRepo.save(project);
	}
	
	@Override
	public void deleteById(Long id) {
		this.projectRepo.delete(id);
	}

	@Override
	public List<Funding> findLatest6Fundings(Project p){
		return this.fundingRepo.findByProjectOrderByFundingdateDesc(p, new PageRequest(0, 6));
	}

	@Override
	public Reward findReward(Project p){
		return this.rewardRepository.findOne(p.getReward().getId());
		//return this.rewardRepository.findByProject(p);
	}

	@Override
	public Reward create(Reward reward) { return this.rewardRepository.save(reward);}

	@Override
	public Funding create(Funding funding){ return this.fundingRepo.save(funding);}
}