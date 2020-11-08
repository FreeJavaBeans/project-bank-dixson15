package model_entity;

public class CheckingAccount extends Account{
    /**
     *
     * @param id
     * @param customer
     * @param accountNumber
     * @param accountBalance
     */
    public CheckingAccount(int id, Customer customer, long accountNumber, double accountBalance) {
        super(id, customer, accountNumber, accountBalance);
    }

    public CheckingAccount() {
        super();
        //super();
    }
}
