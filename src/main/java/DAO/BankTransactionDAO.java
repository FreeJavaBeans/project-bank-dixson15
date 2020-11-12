package DAO;

import db_connection.PostgresConnection;
import jdk.nashorn.internal.runtime.logging.Logger;
import model_entity.Account;
import model_entity.BankTransaction;
import model_entity.CheckingAccount;
import repository.BankTransactionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BankTransactionDAO implements BankTransactionRepository {

    private static final Connection connection = PostgresConnection.getConnection();
    private static PreparedStatement preparedStatement;
    //private static final Logger LOGGER = Logger.getLogger(BankTransactionDAO.class.getName());
    private static String sqlQuery;


    @Override
    public void saveDepositTransaction(BankTransaction bankTransaction) {

        //bankTransaction = new BankTransaction();
        try{

            sqlQuery = "INSERT INTO transaction_history(transaction_id,last_update,customer_id,transaction_amount,trans_description)" +
                    "VALUES (?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,bankTransaction.getTransactionId());
            preparedStatement.setDate(2,getCurrentDate());
            preparedStatement.setInt(3,bankTransaction.getAccount().getCustomer().getEmp_id());
            preparedStatement.setDouble(4,bankTransaction.getTransactionAmount());
            preparedStatement.setString(5,"DEPOSIT");

            int row = preparedStatement.executeUpdate();

            if(row > 0){
                System.out.println("Deposit Transaction recorded successfully");
                System.out.println("Transaction ID: " + bankTransaction.getTransactionId());
                System.out.println("Account ID: " + bankTransaction.getAccount().getId());
                System.out.println("Customer ID: " + bankTransaction.getAccount().getCustomer().getEmp_id());
                System.out.println("Deposit amount: $" + bankTransaction.getTransactionAmount());
                System.out.println("Balance: $" + BankTransaction.getNewBalance());
            }
            else
                System.out.println("unable to save transaction");
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            ///LOGGER.log(Level.INFO, null, sqlException);
        }
//        System.out.println("unable to save transaction");
    }

    @Override
    public void saveWithdrawTransaction(BankTransaction bankTransaction) {

        try{

            sqlQuery = "INSERT INTO transaction_history(transaction_id,last_update,customer_id,transaction_amount,trans_description)" +
                    "VALUES (?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,bankTransaction.getTransactionId());
            preparedStatement.setDate(2,getCurrentDate());
            preparedStatement.setInt(3,bankTransaction.getAccount().getCustomer().getEmp_id());
            preparedStatement.setDouble(4,bankTransaction.getTransactionAmount());
            preparedStatement.setString(5,"WITHDRAW");

            int row = preparedStatement.executeUpdate();

            if(row > 0){
                System.out.println("Withdraw Transaction recorded successfully");
                System.out.println("Transaction ID: " + bankTransaction.getTransactionId());
                System.out.println("Account ID: " + bankTransaction.getAccount().getId());
                System.out.println("Customer ID: " + bankTransaction.getAccount().getCustomer().getEmp_id());
                System.out.println("Withdraw amount: $" + bankTransaction.getTransactionAmount());
                System.out.println("Balance: $" + BankTransaction.getNewBalance());
            }
            else
                System.out.println("unable to save transaction");
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            //LOGGER.log(Level.INFO, null, sqlException);
        }

    }

    @Override
    public void saveTransferFundTransaction(BankTransaction bankTransaction) {

        sqlQuery = "INSERT INTO transaction_history (transaction_id,last_update,customer_id,transaction_amount,trans_description)"
                + "VALUES(?,?,?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1,bankTransaction.getTransactionId());
            preparedStatement.setDate(2,getCurrentDate());
            preparedStatement.setInt(3,bankTransaction.getAccount().getCustomer().getEmp_id());
            preparedStatement.setDouble(4,bankTransaction.getTransactionAmount());
            preparedStatement.setString(5,"TRANSFER FUND");

            int updatedRow = preparedStatement.executeUpdate();

            if(updatedRow > 0)
                System.out.println("Transfer fund Transaction recorded successfully");
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
        }

    }

    @Override
    public List<BankTransaction> getAllTransaction(int transaction_id) {

        List<BankTransaction> bankTransactionList = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();

            sqlQuery = "SELECT * FROM transaction_history";
                  //  + " WHERE transaction_id = ?"; //+ transaction_id;
            /**
             *
             */
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            BankTransaction bankTransaction = new BankTransaction();

            while(resultSet.next()){
                Account account = new CheckingAccount(resultSet.getInt("customer_id"));
                bankTransaction.setTransaction_id(resultSet.getInt("transaction_id"));
                bankTransaction.setAccount(account);


                bankTransaction.setTransactionAmount(resultSet.getDouble("transaction_amount"));

                bankTransactionList.add(bankTransaction);


//                bankTransactionList.forEach(e ->
//                    System.out.println( "TRSCT ID#:" + e.getTransactionId() + "\n" + "TRSCT AMOUNT: $" +e.getTransactionAmount()));

                for (BankTransaction ba: bankTransactionList
                     ) {
                    System.out.println("ID#: " +ba.getTransactionId() + "\n" + "Amount: $" + ba.getTransactionAmount());
                }
                System.out.println();
            }

        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
        }
        return null;
    }

    protected  java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
}
