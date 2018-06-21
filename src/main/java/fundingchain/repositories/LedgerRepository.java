package fundingchain.repositories;

import fundingchain.models.Ledger;
import fundingchain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger,Long> {

    List<Ledger> findLedgerByFromUser(User u);

    List<Ledger> findLedgersByToUser(User u);

    List<Ledger> findLedgersByToUserOrFromUserOrderByDateDesc(User toUser, User fromUser);

    Ledger findLedgerByFromUserAndDate(User u, Date d);
}
