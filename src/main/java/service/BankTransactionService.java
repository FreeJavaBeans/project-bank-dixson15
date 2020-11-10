package service;

import DAO.BankTransactionDAO;
import model_entity.BankTransaction;

import java.util.List;

public class BankTransactionService {

    private BankTransactionDAO bankTransactionDAO;

    public BankTransactionService() {
        this.bankTransactionDAO = new BankTransactionDAO();
    }

    public void saveWithdrawTransaction(BankTransaction bankTransaction){

        bankTransactionDAO.saveWithdrawTransaction(bankTransaction);
    }

    public void saveDepositTransaction(BankTransaction bankTransaction){
        bankTransactionDAO.saveDepositTransaction(bankTransaction);
    }

    public void saveTransferFundTransaction(BankTransaction bankTransaction){

        bankTransactionDAO.saveDepositTransaction(bankTransaction);
    }
    public List<BankTransaction> getAllTransaction(int customer_id){

        return bankTransactionDAO.getAllTransaction(customer_id);
    }
}
