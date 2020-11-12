package DAO;

import ExceptionHandler.IllegalObjectException;
import db_connection.PostgresConnection;
import model_entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.AccountOperationManagerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;

public class AccountOperationManagerDAO implements AccountOperationManagerRepository {

    private static final Connection connection = PostgresConnection.getConnection();
    private static final Logger logger = LogManager.getLogger(BankerDAO.class);
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(BankerDAO.class.getName());
    private static PreparedStatement preparedStatement;
    private static final String [] ACCOUNT_STATUS = {"PENDING","APPROVED","REJECTED"};

    private AccountDAO accountDAO;
    private BankTransactionDAO bankTransactionDAO;

    public AccountOperationManagerDAO() {
        this.accountDAO = new AccountDAO();
        this.bankTransactionDAO = new BankTransactionDAO();
    }

    @Override
    public String createCustomerAccount(Banker banker, Account account, int creditScore){

        try{
            String sql = "INSERT INTO account_review(acc_id,customer_id,account_number,account_balance,credit_score,account_status,emp_id,c_date)" +
            "VALUES (?,?,?,?,?,?,?,?)";

            if(banker != null && account != null){
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1,account.getId());
                preparedStatement.setInt(2,account.getCustomer().getEmp_id());
                preparedStatement.setLong(3,account.getAccountNumber());
                preparedStatement.setDouble(4,account.getAccountBalance());
                preparedStatement.setInt(5,creditScore);
                preparedStatement.setString(6,ACCOUNT_STATUS[0]);
                preparedStatement.setInt(7,banker.getEmp_id());
                preparedStatement.setDate(8,bankTransactionDAO.getCurrentDate());

                int row = preparedStatement.executeUpdate();
                if(row > 0)
                    return "Account  with ID#: " + account.getId() + "Successfully applied" + "\n" + "STATUS: " + ACCOUNT_STATUS[0];
            }
            return "Unable to create account";
        }
        catch (Exception exception){
            LOGGER.log(Level.SEVERE, null, exception);
        }
        throw new IllegalObjectException("Object not supported");
    }

    @Override
    public boolean approveAccount(Account account) {

        String SQL = "UPDATE account_review "
                + "SET account_status = ? "
                + "WHERE account_number = ?";

        try {
            if(!account.equals(null) && getReviewAccount(account) >= 500){
                preparedStatement = connection.prepareStatement(SQL) ;

                preparedStatement.setString(1, ACCOUNT_STATUS[1]);
                preparedStatement.setLong(2, account.getAccountNumber());

                int affectedrows = preparedStatement.executeUpdate();
                if(affectedrows > 0){
                    accountDAO.saveAccount(account);
                    System.out.println("Account successfully approved!" + "\n"+ "AccountID#: " + account.getId());
                    return true;
                }
            }
            else{
                preparedStatement = connection.prepareStatement(SQL) ;
                preparedStatement.setString(1, ACCOUNT_STATUS[2]);
                preparedStatement.setLong(2, account.getAccountNumber());
                preparedStatement.executeUpdate();
                System.out.println("Account rejected" + "\n"+ "AccountID#: " + account.getId());
                return false;
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOGGER.log(Level.SEVERE, null, ex);
        }
        throw new IllegalObjectException("Object not supported");
    }

    @Override
    public Account viewCustomerAccount(int account_id) {
        if(account_id == 1)
            return accountDAO.getAccounts().get(account_id);
        return null;
    }

    @Override
    public List<BankTransaction> viewAllTransactionLog() {
        return bankTransactionDAO.getAllTransaction(8);
    }

    @Override
    public void getAccountsforReview() {
        try{
            String sql = "SELECT * FROM account_review";
            preparedStatement = connection.prepareStatement(sql);

            //execute query
            ResultSet resultSet = preparedStatement.executeQuery();


            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.print("|"+"ID# | CUSTOMER ID | \tACCOUNT # | \tBALANCE | \tSCORE |\tSTATUS | \tEMP ID# | \tDATE"+ "\t|\n");
            System.out.println("----------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("acc_id");
                int custId = resultSet.getInt("customer_id");
                long acctN = resultSet.getLong("account_number");
                double acctB = resultSet.getDouble("account_balance");
                int credit = resultSet.getInt("credit_score");
                String status = resultSet.getString("account_status");
                int emp_id = resultSet.getInt("emp_id");
                //LocalDate localDate = (LocalDate) resultSet.getObject("c_date");
                String date = resultSet.getString("c_date");




//                Customer customer = new Customer(id,first,last);
//                Account cAccount = new CheckingAccount(ch_id,customer,acctN,acctB);
                //accountList.add(cAccount);
                System.out.print(id + "\t\t" + custId + "\t\t" + acctN + "\t\t" + acctB + "\t\t\t" + credit+ "\t\t"
                        + status + "\t\t" + emp_id +  "\t\t" + date + "\t\n");
                System.out.println("----------------------------------------------------------------------------------------------");
                //return accountList;

            }

        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);

        }
    }

    public int getReviewAccount(Account account){
        try{

            String sql = "SELECT credit_score FROM account_review"
                        + " WHERE account_number = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getAccountNumber());
            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                int score = resultSet.getInt("credit_score");
                return score;
            }
        }catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        throw new IllegalObjectException("Object not supported");

    }
}
