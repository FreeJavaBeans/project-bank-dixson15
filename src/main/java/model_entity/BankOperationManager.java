package model_entity;

public class BankOperationManager {

    private Banker banker;
    private Account account;
    private BankTransaction bankTransaction;



    public BankOperationManager() {
        this.banker = null;
        this.account = null;
        this.bankTransaction = null;
    }

    public BankOperationManager(Banker banker) {
        this.banker = banker;
        this.account = null;
        this.bankTransaction = null;

    }

    public BankOperationManager(Account account) {
        this.banker = null;
        this.account = account;
        this.bankTransaction = null;
    }

    public BankOperationManager(Banker banker, Account account, BankTransaction bankTransaction) {
        this.banker = banker;
        this.account = account;
        this.bankTransaction = bankTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public Banker getBanker() {
        return banker;
    }

    public BankTransaction getBankTransaction() {
        return bankTransaction;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setBanker(Banker banker) {
        this.banker = banker;
    }

    public void setBankTransaction(BankTransaction bankTransaction) {
        this.bankTransaction = bankTransaction;
    }

}
