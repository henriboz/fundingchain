package fundingchain.services;

import fundingchain.models.Ledger;
import fundingchain.models.User;
import fundingchain.repositories.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Primary
public class LedgerServiceImpl implements LedgerService{

    @Autowired
    LedgerRepository ledgerRepository;

    @Override
    public List<Ledger> findAll() {
        return this.ledgerRepository.findAll();
    }

    @Override
    public List<Ledger> findByUserFrom(User u) {
        return this.ledgerRepository.findLedgerByFromUser(u);
    }

    @Override
    public List<Ledger> findByUserTo(User u) {
        return this.ledgerRepository.findLedgersByToUser(u);
    }

    @Override
    public List<Ledger> findByUser(User u) {
        return this.ledgerRepository.findLedgersByToUserOrFromUserOrderByDateDesc(u,u);
    }

    @Override
    public Ledger findByUserFrom(User u, Date d) {
        return this.ledgerRepository.findLedgerByFromUserAndDate(u, d);
    }

    @Override
    public Ledger create(Ledger l) {
        return this.ledgerRepository.save(l);
    }
}
