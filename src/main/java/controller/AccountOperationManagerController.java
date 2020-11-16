package controller;

import model_entity.Account;
import model_entity.BankTransaction;
import model_entity.Banker;
import service.AccountOperationManagerService;

import java.util.List;

public class AccountOperationManagerController {

    private AccountOperationManagerService accountOperationManagerService;

    public AccountOperationManagerController() {
        this.accountOperationManagerService = new AccountOperationManagerService();
    }

    public String createCustomerAccount(Banker banker, Account account, int creditScore){
        return accountOperationManagerService.createCustomerAccount(banker,account,creditScore);
    }
    public boolean approveAccount(Account account){
        return accountOperationManagerService.approveAccount(account);
    }
    public Account viewCustomerAccount(int account_id){
        return accountOperationManagerService.viewCustomerAccount(account_id);
    }
    public List<BankTransaction> viewAllTransactionLog(){
        return accountOperationManagerService.viewAllTransactionLog();
    }

    public void getAccountsforReview(){
     accountOperationManagerService.getAccountsforReview();
    }
}
