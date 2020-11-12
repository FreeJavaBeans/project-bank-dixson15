package model_entity;

import java.util.UUID;

public class BankTransaction {

    private Account account;
    private int transaction_id;
    private double transactionAmount;
    private static double newBalance;
    protected static double transaction_fee = 0.2;

    public BankTransaction() {
        transaction_id = generateUniqueId();
        account = null;
        transactionAmount = .0;
    }

    public BankTransaction(Account account) {
        transaction_id = generateUniqueId();
        this.account = account;
        transactionAmount = .0;
        //newBalance = .0;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public static void setNewBalance(double newBalance) {
        BankTransaction.newBalance = newBalance;
    }

    /**
     * getters
     * @return
     */
    public int getTransactionId(){
        return transaction_id;
    }

    public Account getAccount() {
        return account;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public static double getNewBalance() {
        return newBalance;
    }

    public  int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public double withdraw(Account accountType, double amount) {

        transactionAmount = amount;

        if (accountType instanceof CheckingAccount) {
            account = accountType;

            try {
                if ((transactionAmount + transaction_fee) > 0 && (transactionAmount + transaction_fee) < account.getAccountBalance())
                    newBalance = account.getAccountBalance() - (transactionAmount + transaction_fee);
                //System.out.println("Transaction ID#: " + getTransactionId());
                return newBalance;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if (accountType instanceof SavingAccount){
            account = accountType;
            try {
                if ((transactionAmount + transaction_fee) > 0 && (transactionAmount + transaction_fee) < account.getAccountBalance())
                    newBalance =  account.getAccountBalance()  - (transactionAmount + transaction_fee);
                return newBalance;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        throw new ArithmeticException();
    }

    public  boolean deposit(Account accountType,double amount){

        transactionAmount = amount;

        if(accountType instanceof CheckingAccount){
            account = accountType;

            try{
                if(transactionAmount > 0){
                    newBalance = account.getAccountBalance() + transactionAmount;
                    System.out.println("Checking Balance:$" + newBalance);
                    return true;
                }
                throw new ArithmeticException("Wrong amount for deposit");

            }catch (Exception exception){
                exception.printStackTrace();
            }

        }
        else if(accountType instanceof SavingAccount) {
            account = accountType;
            try {
                if (amount > 0) {
                    newBalance = account.getAccountBalance() + transactionAmount;
//                    System.out.println("Transaction ID#: " + transaction_id);
                    System.out.println("Saving Balance:$" + newBalance);
                    return true;
                }
                throw new ArithmeticException("Wrong amount for deposit");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Instance not supported");
    }

    public double transferFund(Account destination, double amount){

        transactionAmount = amount;
        double sourceBalance;
        double destinationBalance;

        try{
            if(transactionAmount <= 0)
                System.out.println("Wrong amount!");
            else if(!this.account.equals(destination) && this.account.getAccountBalance() >= transactionAmount){
                sourceBalance = this.account.getAccountBalance() - this.transactionAmount;
                destinationBalance = destination.getAccountBalance() + this.transactionAmount;
                System.out.println("Account source remaining balance: $" + sourceBalance + "\n"
                                                                         + "Destination balance: $ " + destinationBalance);
                return destinationBalance;
            }
            else{
                System.out.println("Transaction error.Please verify your inputs.");
                throw new IllegalArgumentException();
            }

        }
        catch (ArithmeticException arithmeticException){
            arithmeticException.fillInStackTrace();
        }
        throw new IllegalArgumentException("unsupported input");
    }

}
