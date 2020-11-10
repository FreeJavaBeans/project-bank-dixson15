package controller;

import DAO.AccountDAO;
import model_entity.Account;

import java.util.List;

public class AccountController {

    private AccountDAO accountService;

    public int saveAccount(Account account) {

        return accountService.saveAccount(account);
    }

    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    public void updateAccount(Account account) {
        accountService.updateAccount(account);
    }

    public double getAccountBalance(Account account) {
        return accountService.getAccountBalance(account);
    }

    public void updateBalance(Account account) {
        accountService.updateBalance(account);
    }

    public String deleteAccount(Account account) {
        return accountService.deleteAccount(account);
    }
}
