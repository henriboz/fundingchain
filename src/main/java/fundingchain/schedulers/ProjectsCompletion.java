package fundingchain.schedulers;

import fundingchain.models.*;
import fundingchain.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ProjectsCompletion {

    //Time in ms to run the process
    private static final int complete_projects_frequency = 120000;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private LedgerService ledgerService;

    EtherService etherService;


    private void sendMoneyToFunders(List<Funding> funding){
        for (Funding f:funding) {
            User user = userService.findUserByFunding(f);
            this.sendMoney(user, f.getValue());
        }
    }

    private void sendMoney(User user, double value){
        //Gets the admin user and take the money from it
        User admin = userService.findUserByUsername("admin");
        Wallet adminWallet = admin.getWallet();
        adminWallet.setMoney(adminWallet.getMoney() - value);

        //Gets the input user wallet and adds money to it
        Wallet wallet = user.getWallet();
        wallet.setMoney(wallet.getMoney() + value);

        //Creates a ledger of the transaction
        Ledger ledger = new Ledger();
        ledger.setDate(new Date());
        ledger.setToUser(user);
        ledger.setFromUser(admin);
        ledger.setValue(value);

        //Saves stuff in the DB
        try{
            userService.edit(adminWallet);
            userService.edit(wallet);
            ledgerService.create(ledger);
            etherService.transfer(admin,user, value);
        }catch (Exception e){
            System.out.println("ERROR - "+ e);
        }

    }

    @Scheduled(fixedRate = complete_projects_frequency)
    public void completeProjects(){

        Date now = new Date();
        System.out.println("Starting to complete projects now: "+now.toString());

        //Gets list of expired projects
        List<Project> projects = projectService.findExpiredProjects();
        for(Project p:projects){
            //Disables project and update the DB record
            p.setActive(false);
            try{
                projectService.edit(p);
            }catch (Exception e){
                System.out.println("ERROR - "+ e);
                return;
            }
            //Gets list of all fundings for the project
            List<Funding> fundings = projectService.findAllFundings(p);

            if (fundings.size() > 0) {
                etherService = new EtherServiceIml();
                p.setFunders(fundings);
                //If total fundings greater than the target value -> Success
                if (p.getSumFundingsValue() >= p.getFundingValue()) {
                    //Sends 99% of money to the project owner. 1% is to the house.
                    this.sendMoney(p.getOwner(), p.getSumFundingsValue());
                } else {
                    //Send everything back to the funders. ¯\_(ツ)_/¯
                    this.sendMoneyToFunders(fundings);
                }
            }
        }
    }
}
