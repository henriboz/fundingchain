package fundingchain.services;

import fundingchain.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;

public class EtherServiceIml implements EtherService {

    private Admin web3j;

    //@Value("${ether.keyStore.directory}")
    private String keyStore = "C:\\Users\\Henrique Boz\\Desktop\\PrivateEtherNetwork\\keystore\\";

    private boolean connect(){
        this.web3j = Admin.build(new HttpService());
        try{
            System.out.println("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
            return true;
        }catch (Exception e){
            System.out.println("Failed to connect to Ethereum: "+ e);
            return false;
        }
    }

    private String findKeyFile(String publicKey){
        File folder = new File(keyStore);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].getName().indexOf(publicKey.toLowerCase().substring(2)) == 37){
                return keyStore + listOfFiles[i].getName();
            }
        }
        return null;
    }

    @Override
    public String create(String password) {
        if (connect()){
           try{
               NewAccountIdentifier newAccountIdentifier = web3j.personalNewAccount(password).sendAsync().get();
               return newAccountIdentifier.getAccountId();
           }catch (Exception e){
               System.out.print("Failed to create the Ethereum account: "+e);
               return null;
           }
        }
        return null;
    }

    @Override
    public boolean transfer(User from, User to, double value) {
        if (!connect()) return false;
        try{
            Credentials credentials = WalletUtils.loadCredentials(from.getPassword(), findKeyFile(from.getWallet().getPublicKey()));
            System.out.println("Credentials loaded");
            System.out.println("Sending Ether ..");
            Transfer.sendFunds(
                    web3j, credentials,
                    to.getWallet().getPublicKey(),
                    BigDecimal.valueOf(value), Convert.Unit.ETHER)
                    .sendAsync();
            System.out.println("Transaction complete : ");
            System.out.println("From: "+from.getUsername()+" - "+from.getWallet().getPublicKey());
            System.out.println("To: "+to.getUsername() + " - "+to.getWallet().getPublicKey());
            System.out.println("Value: "+ String.valueOf(value));
            return true;
        }catch (Exception e){
            System.out.println("Error - "+ e);
            return false;
        }
    }
}
