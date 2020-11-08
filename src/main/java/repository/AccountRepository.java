package repository;

import model_entity.Account;

import java.util.List;

public interface AccountRepository {

    int saveAccount(Account account);

    List<Account> getAccounts();

    void updateAccount(Account account);

    double getAccountBalance(Account account);

    void updateBalance(Account account);
    String deleteAccount(Account account);

}
