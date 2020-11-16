package DAO;

import db_connection.PostgresConnection;
import model_entity.BankUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.Authenticable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class AuthenticableDAO implements Authenticable {

    private static final Connection connection = PostgresConnection.getConnection();
    private static final Logger logger = LogManager.getLogger(BankerDAO.class);
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(BankUser.class.getName());
    private static PreparedStatement preparedStatement;

    @Override
    public boolean register(BankUser bankUser) {

        try{
            String sql = "INSERT INTO register(uid,firstname,lastname,username,password)"
                    + "VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,bankUser.getEmp_id());
            preparedStatement.setString(2,bankUser.getFirstName());
            preparedStatement.setString(3,bankUser.getLastName());
            preparedStatement.setString(4,bankUser.getUserName());
            preparedStatement.setString(5,bankUser.getPassword());

            int row = preparedStatement.executeUpdate();
            if(row > 0){
                System.out.println("Successfully register" + "ID#:" + bankUser.getEmp_id());
                logger.info("user registration success!");
            }

        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            logger.error("Failed to register user!");
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
       return false;
    }

    @Override
    public boolean login(String username, String password) {
        try{
            String sql = " SELECT uid ,firstname, lastname FROM register"
                    + " WHERE username = ? AND password = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                System.out.println("------------------------------------------------");
                System.out.print("| "+"CUSTOMER ID#|\tFirst name |\t Last name "+ "    |\n");
                System.out.println("------------------------------------------------");

                long id = resultSet.getLong("uid");
                String first = resultSet.getString("firstname");
                String last = resultSet.getString("lastname");


                System.out.print("\t"+ " "+id +" " +"\t\t" + first +" " +"\t\t" + last + "\t\t\n");
                System.out.println("|                                              |");
                System.out.println("|----------------------------------------------|");
                System.out.println();
                logger.info("logged in successfully!");
                return true;

            }
            else
                System.out.println("Wrong username or password!");
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            logger.error("Failed to register user!");
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return false;
    }

    @Override
    public void logout() {
        System.out.println("log out successfully");
        System.exit(1);
    }
}
