package controller;

import DAO.AccountOperationManagerDAO;
import model_entity.Account;
import model_entity.BankTransaction;
import model_entity.Banker;
import service.AccountOperationManagerService;

import java.util.List;

public class AccountOperationManagerController {

    private AccountOperationManagerService accountOperationManagerDAO;

    public AccountOperationManagerController() {
        this.accountOperationManagerDAO = new AccountOperationManagerService();
    }

    public String createCustomerAccount(Banker banker, Account account, int creditScore){
        return accountOperationManagerDAO.createCustomerAccount(banker,account,creditScore);
    }
    public boolean approveAccount(Account account){
        return accountOperationManagerDAO.approveAccount(account);
    }
    public Account viewCustomerAccount(int account_id){
        return accountOperationManagerDAO.viewCustomerAccount(account_id);
    }
    public List<BankTransaction> viewAllTransactionLog(){
        return accountOperationManagerDAO.viewAllTransactionLog();
    }
}
