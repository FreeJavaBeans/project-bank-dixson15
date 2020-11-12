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
                    return "Account  with ID#: " + account.getId() + "\n" + "STATUS: " + ACCOUNT_STATUS[0];
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
