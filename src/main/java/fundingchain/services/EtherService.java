package fundingchain.services;

import fundingchain.models.User;

public interface EtherService {
    String create(String password);
    boolean transfer(User from, User to, double value);
}
