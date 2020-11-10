package repository;
import model_entity.BankTransaction;

import java.util.List;

public interface BankTransactionRepository {

    void saveDepositTransaction(BankTransaction bankTransaction);
    void saveWithdrawTransaction(BankTransaction bankTransaction);
    void saveTransferFundTransaction(BankTransaction bankTransaction);
    List<BankTransaction> getAllTransaction(int customer_id);
}
