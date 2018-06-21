package fundingchain.services;

import fundingchain.models.Ledger;
import fundingchain.models.User;

import java.util.Date;
import java.util.List;

public interface LedgerService {
    List<Ledger> findAll();
    List<Ledger> findByUserFrom(User u);
    List<Ledger> findByUserTo(User u);
    List<Ledger> findByUser(User u);
    Ledger findByUserFrom(User u, Date d);
    Ledger create(Ledger l);
}
