package fundingchain.repositories;

import fundingchain.models.Funding;
import fundingchain.models.Project;
import fundingchain.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query("SELECT p FROM Project p ORDER BY p.creationdate DESC")
	List<Project> findLatest6Projects(Pageable pageable);

	List<Project> findProjectsByDuedateBeforeAndActiveTrue(Date dueDate);

	@Query("SELECT p FROM Project p where p.active='1' ORDER BY p.duedate ASC")
	List<Project> findLatest6ActiveProjects(Pageable pageable);

	List<Project> findProjectByActiveTrueOrderByDuedateAsc();

	List<Project> findProjectsByOwnerOrderByCreationdateDesc(User owner);

	Project findProjectByFundings(Funding f);
}