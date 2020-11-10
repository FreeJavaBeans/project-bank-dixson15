package service;

import DAO.AccountDAO;
import model_entity.Account;
import java.util.List;


public class AccountService {

    private AccountDAO accountDAO;

    public int saveAccount(Account account) {

        return accountDAO.saveAccount(account);
    }

    public List<Account> getAccounts() {
        return accountDAO.getAccounts();
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public double getAccountBalance(Account account) {
        return accountDAO.getAccountBalance(account);
    }

    public void updateBalance(Account account) {
        accountDAO.updateBalance(account);
    }

    public String deleteAccount(Account account) {
        return accountDAO.deleteAccount(account);
    }
}
