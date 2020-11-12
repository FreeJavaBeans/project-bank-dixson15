package repository;

import model_entity.Account;
import model_entity.BankTransaction;
import model_entity.Banker;

import java.util.List;

public interface AccountOperationManagerRepository {

    String createCustomerAccount(Banker banker,Account account, int creditScore);
    boolean approveAccount(Account account);
    Account viewCustomerAccount(int account_id);
    List<BankTransaction> viewAllTransactionLog();
}
