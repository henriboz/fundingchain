package fundingchain.services;

import fundingchain.models.Project;
import fundingchain.repositories.ProjectRepository;
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
	
	@Override
	public List<Project> findAll() {
		return this.projectRepo.findAll();
	}
	
	@Override
	public List<Project> findLatest5() {
		return this.projectRepo.findLatest5Posts(new PageRequest(0, 5));
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

}