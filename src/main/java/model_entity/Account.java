package model_entity;

/**
 * base class for type of bank accounts
 */
public abstract class Account {
    /**
     * static fields
     */
    protected static final double INTEREST_RATE = .03;
    protected static double transaction_fee = 0.2;

    /**
     * Instances fields
     */
    private int id;
    private Customer customer;
    private long accountNumber;
    private double accountBalance;

    protected Account() {
    }

    /**
     *
     * @param id
     * @param customer
     * @param accountNumber
     * @param accountBalance
     */


    protected Account(int id, Customer customer, long accountNumber, double accountBalance) {
        this.id = id;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @return Customer Instance
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @return account Number
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     *
     * @return account balance
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     *
     * @param amount
     * @return true or false
     */
    public boolean withdraw(double amount){
        try{
            if((amount + this.transaction_fee) > 0 && (amount + this.transaction_fee) < this.accountBalance){
                this.accountBalance = this.accountBalance - (amount + this.transaction_fee);
                return true;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new ArithmeticException("Insufficient found");

    }
    /**
     *
     * @param amount
     * @return true or false
     */
    public boolean deposit(double amount){
        try{
            if(amount > 0){
                this.accountBalance += amount;
                return true;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new ArithmeticException("Insufficient found");
    }

    public double addAccountInterest(){

        this.accountBalance += (this.accountBalance * this.INTEREST_RATE);
        return accountBalance;
    }
    /**
     *
     * @param accountDestination
     * @param amount
     */
    public void transferMoneyTo(Account accountDestination, double amount){
        accountDestination.deposit(amount);
        this.withdraw(amount);
    }
    public String toString(){
        super.toString();
        return null;
    }

}
