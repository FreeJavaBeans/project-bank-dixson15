package model_entity;

class CheckingAccount extends Account{
    public CheckingAccount(int id, Customer customer, long accountNumber, double accountBalance) {
        super(id, customer, accountNumber, accountBalance);
    }
}
