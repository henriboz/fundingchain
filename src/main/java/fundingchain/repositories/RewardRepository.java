package fundingchain.repositories;

import fundingchain.models.Project;
import fundingchain.models.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Long> {
}
