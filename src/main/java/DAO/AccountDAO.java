package DAO;

import ExceptionHandler.IllegalObjectException;
import db_connection.PostgresConnection;
import model_entity.*;
import repository.AccountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO implements AccountRepository {

    private static final Connection connection = PostgresConnection.getConnection();
    private static final Logger LOGGER = Logger.getLogger(AccountDAO.class.getName());
    private static PreparedStatement preparedStatement;

    @Override
    public int saveAccount(Account account) {

        try{
            String sql = null;
            if(account instanceof CheckingAccount)
                sql = "insert into checking_account(c_id,customer_id,account_number,account_balance)" +
                        "VALUES (?,?,?,?)";
            else
                sql = "insert into saving_account(s_id,customer_id,account_number,account_balance)" +
                        "VALUES (?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getId());
            preparedStatement.setInt(2,account.getCustomer().getEmp_id());
            preparedStatement.setLong(3,account.getAccountNumber());
            preparedStatement.setDouble(4,account.getAccountBalance());

            int row = preparedStatement.executeUpdate();
            if(row > 0){
                System.out.println("Account created successfully");
                return row;
            }

        }
        catch(SQLException sqlException){
            System.err.println(sqlException.getErrorCode());
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return 0;
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accountList = new ArrayList<>();

        try{
            String sql = "SELECT * FROM checking_account c join customer s on  c.customer_id = s.customer_id";
            preparedStatement = connection.prepareStatement(sql);

                //execute query
            ResultSet resultSet = preparedStatement.executeQuery();


            System.out.println("-------------------------------------------------------------------------------------");
            System.out.print("|"+"ID#| First name |\tLast Name |\tCustomer ID |\tAccount Number |\tAccount Balance"+ "\t|\n");
            System.out.println("-------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String first = resultSet.getString("first_name");
                String last = resultSet.getString("last_name");
                int ch_id = resultSet.getInt("c_id");
                long acctN = resultSet.getLong("account_number");
                Double acctB = resultSet.getDouble("account_balance");

                Customer customer = new Customer(id,first,last);
                Account cAccount = new CheckingAccount(ch_id,customer,acctN,acctB);
                accountList.add(cAccount);
                System.out.print(id + "\t\t" + first + "\t\t" + last + "\t\t\t" + ch_id + "\t\t\t" + acctN + "\t\t\t" + "$"+acctB + "\t\t\n");
                System.out.println("-------------------------------------------------------------------------------------");
                //return accountList;

            }

        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);

        }
        return accountList;
    }
    @Override
    public void updateAccount(Account account) {

        try{
            String sql = null;

            if(account instanceof CheckingAccount)
                sql = "UPDATE checking_account "
                        + "SET account_number = ?,"
                        + "account_balance = ?"
                        + "WHERE customer_id = ?";
            else
                sql = "UPDATE saving_account "
                        + "SET account_number = ?,"
                        + "account_balance = ?"
                        + "WHERE s_id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1,account.getAccountNumber());
            preparedStatement.setDouble(2,account.getAccountBalance());
            preparedStatement.setInt(3,account.getId());

            int updatedRows = preparedStatement.executeUpdate();

            if(updatedRows > 0)
                System.out.println("Account successfully update with ID#: " + account.getId());
            else
                System.out.println("Unable to update account balance!");

        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        System.out.println("Unable to update account!");
    }

    @Override
    public double getAccountBalance(Account account_number) {

        String sql= null;

        try{
            if(account_number instanceof SavingAccount)

                sql = "SELECT account_balance FROM saving_account"
                    + " WHERE account_number = ?";
            else
                sql = "SELECT account_balance FROM checking_account"
                        + " WHERE account_number = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,account_number.getAccountNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("-------------");
            System.out.print("|"+"\tBalance |\n");
            System.out.println("-------------");

            if(resultSet.next()){
                double balance = resultSet.getDouble("account_balance");
                System.out.print(" $" + "\t" + balance);
                System.out.println();
                return balance;
            }
        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        throw new IllegalObjectException("Object not supported");
    }

    @Override
    public void updateBalance(Account account) {
        try{
            String sql = null;

            if(account instanceof CheckingAccount)
                sql = "UPDATE checking_account "
                        + "SET account_balance = ?"
                        + "WHERE customer_id = ?";
            else
                sql = "UPDATE saving_account "
                        + "SET account_balance = ?"
                        + "WHERE customer_id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1,account.getAccountBalance());
            preparedStatement.setInt(2,account.getId());

            int updatedRows = preparedStatement.executeUpdate();

            if(updatedRows > 0){
                System.out.println("Account Balance update with ID#: " + account.getId());
                System.out.println("Account Balance $: " + account.getAccountBalance());
            }

            else
                System.out.println("Unable to update account balance!");

        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
    }

    @Override
    public String deleteAccount(Account account) {

        try{
            String sql = null;

            if(account instanceof CheckingAccount)
                sql = " DELETE  FROM  checking_account WHERE c_id = ? ";
            else
                sql = "DELETE  FROM  saving_account WHERE s_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getId());
            int updatedRow =   preparedStatement.executeUpdate();

            if(updatedRow > 0)
                System.out.println("Account with ID#" + account.getId() + "is successfully deleted.");
            else
                System.out.println("Unable to delete Account with ID#: " + account.getId());

        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
        }
        return " something went wrong...";
    }
}
