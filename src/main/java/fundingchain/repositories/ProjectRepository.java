package fundingchain.repositories;

import fundingchain.models.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query("SELECT p FROM Project p ORDER BY p.creationdate DESC")
	List<Project> findLatest6Projects(Pageable pageable);
}