package controller;

import model_entity.BankTransaction;
import service.BankTransactionService;

import java.util.List;

public class BankTransactionController {

    private BankTransactionService bankTransactionService;

    public BankTransactionController() {
        this.bankTransactionService = new BankTransactionService();
    }

    public void saveDepositTransaction(BankTransaction bankTransaction){
        bankTransactionService.saveDepositTransaction(bankTransaction);
    }
    public void saveWithdrawTransaction(BankTransaction bankTransaction){

        bankTransactionService.saveWithdrawTransaction(bankTransaction);
    }
    public void saveTransferFundTransaction(BankTransaction bankTransaction){
        this.bankTransactionService.saveTransferFundTransaction(bankTransaction);
    }
    public List<BankTransaction> getAllTransaction(int customer_id){

        return bankTransactionService.getAllTransaction(customer_id);
    }
}
