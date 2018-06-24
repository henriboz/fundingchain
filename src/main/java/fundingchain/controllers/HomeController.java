package fundingchain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;


@Controller
public class HomeController {
	
	@RequestMapping("/")
    public String index(Model model) {
		return "index";
		
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String go(){
        //Web3j web3j = Web3j.build(new HttpService());
        Admin web3j = Admin.build(new HttpService());
        try{

            File folder = new File("C:\\Users\\Henrique Boz\\Desktop\\PrivateEtherNetwork\\keystore\\");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                    System.out.println(listOfFiles[i].getName());
                    System.out.println(listOfFiles[i].getName().indexOf("0x6a0e9a5c78b3ad0b1ad2993e133d0156d221da4f".toLowerCase().substring(2)));
            }

            /*System.out.println("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
            NewAccountIdentifier newAccountIdentifier = web3j.personalNewAccount("passwdpasswd").sendAsync().get();
            if (newAccountIdentifier != null) {
                System.out.println("Account created: "+newAccountIdentifier.getAccountId());
                PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(newAccountIdentifier.getAccountId(), "passwdpasswd").sendAsync().get();
                System.out.println("Got account: "+personalUnlockAccount.getId());
            }*/
            //PersonalUnlockAccount personalUnlockAccount = new PersonalUnlockAccount();
            //System.out.println(personalUnlockAccount.getId());
            /*Credentials credentials = WalletUtils.loadCredentials("passwdpasswd", "C:\\Users\\Henrique Boz\\Desktop\\PrivateEtherNetwork\\keystore\\UTC--2018-06-23T17-14-43.067503300Z--fcfc2d0b000374af513c161ae1d478423cbedc0e");
            System.out.println("Credentials loaded");
            System.out.println("Sending Ether ..");
            TransactionReceipt transferReceipt = Transfer.sendFunds(
                    web3j, credentials,
                    "0x4e9480274Ca44d68573063D5D1682dB6A13Baa8a",  // you can put any address here
                    BigDecimal.valueOf(1), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
                    .sendAsync().get();
            System.out.println("Transaction complete : " + transferReceipt.getTransactionHash());*/
        }catch (Exception e){
            System.out.println(e);
        }

	    return "/test";
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String go(Model model){
        return "/test";
    }

}