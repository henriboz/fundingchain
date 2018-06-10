package fundingchain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fundingchain.models.Funding;

public interface FundingRepository extends JpaRepository<Funding, Long>{
}