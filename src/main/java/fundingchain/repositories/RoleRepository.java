package fundingchain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fundingchain.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}