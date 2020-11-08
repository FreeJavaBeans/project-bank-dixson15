package model_entity;

public class SavingAccount extends Account{
    /**
     * @param id
     * @param customer
     * @param accountNumber
     * @param accountBalance
     */
    public SavingAccount(int id, Customer customer, long accountNumber, double accountBalance) {
        super(id, customer, accountNumber, accountBalance);
    }

    public SavingAccount() {

    }
}
