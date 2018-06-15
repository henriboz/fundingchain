package fundingchain.repositories;

import fundingchain.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import fundingchain.models.Funding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long>{

    /*@Query("SELECT f FROM Funding f ORDER BY f.funding_date DESC")
    List<Funding> findLatest6Fundings(Project project);*/

    List<Funding> findByProjectOrderByFundingdateDesc(Project project, Pageable pageable);
}