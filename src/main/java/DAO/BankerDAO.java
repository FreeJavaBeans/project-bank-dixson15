package DAO;

import ExceptionHandler.IllegalObjectException;
import db_connection.PostgresConnection;
import model_entity.Banker;
import model_entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.BankerRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class BankerDAO implements BankerRepository {

    private static final Connection connection = PostgresConnection.getConnection();
    private static final Logger logger = LogManager.getLogger(BankerDAO.class);
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(BankerDAO.class.getName());
    private static PreparedStatement preparedStatement;

    @Override
    public String addEmployee(Banker employee) {

        try{
            String sql = "INSERT INTO banker(emp_id, firstname,lastname,emp_type)" +
                    "VALUES(?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,employee.getEmp_id());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setString(4, employee.getType());

            int updatedRow = preparedStatement.executeUpdate();

            if(updatedRow > 0)
                return "Employee successfully added with ID#: " + employee.getEmp_id();
        }
        catch(SQLException exception){
            exception.fillInStackTrace();
            logger.error("Failed to save");
            LOGGER.log(Level.SEVERE, null, exception);

        }
        throw new IllegalObjectException("Unsupported object type");
    }

    @Override
    public List<Banker> getAllEmployee() {

        List<Banker> bankerList = new ArrayList<>();

        try{
            String sql = "SELECT * FROM banker";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("-------------------------------------------------");
            System.out.print("|"+"ID# \tFirst name \t Last Name \t\t Role"+ "\t\t|\n");
            System.out.println("-------------------------------------------------");
            while(resultSet.next()){
                int id = resultSet.getInt("emp_id");
                String first = resultSet.getString("firstname");
                String last = resultSet.getString("firstname");
                String empType = resultSet.getString("emp_type");
                System.out.print(id + "\t\t" + first + "\t\t" + last + "\t\t" + empType);
                System.out.println();

                Banker banker = new Banker(id,first,last,empType);
                bankerList.add(banker);
                return bankerList;
            }
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            logger.error("Error occured", sqlException);
        }
        throw new IllegalObjectException("Error: Object not supported");
    }

    @Override
    public Employee getEmployeeByID(int emp_id) {

        try{
            String sql = "SELECT emp_id,firstname,lastname,emp_type FROM banker"
                    + " WHERE emp_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,emp_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("-------------------------------------------------");
            System.out.print("|"+"ID# \tFirst name \t Last Name \t\t Role"+ "\t\t|\n");
            System.out.println("-------------------------------------------------");

            while(resultSet.next()){

                String first = resultSet.getString("firstname");
                String last = resultSet.getString("lastname");
                String empType = resultSet.getString("emp_type");
                System.out.print(emp_id + "\t\t" + first + "\t\t" + last + "\t\t" + empType);
                System.out.println();

                Banker banker = new Banker(emp_id,first,last,empType);

               return banker;
            }
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            logger.error("Error occur", sqlException);
        }
        throw new IllegalObjectException("Error: Object not supported");
    }

    @Override
    public String updateEmployee(Banker nonNullEmployee) {

        String message = "The Banker to be updated should not be null";
        nonNullEmployee = Objects.requireNonNull(nonNullEmployee, message);
        String sql = "UPDATE banker "
                + "SET "
                + "firstname = ?, "
                + "lastname = ?, "
                + "emp_type = ? "
                + "WHERE "
                + "emp_id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nonNullEmployee.getFirstName());
            preparedStatement.setString(2,nonNullEmployee.getLastName());
            preparedStatement.setString(3,nonNullEmployee.getType());
            preparedStatement.setInt(4,nonNullEmployee.getEmp_id());

            int updatedRow = preparedStatement.executeUpdate();

            if(updatedRow > 0)
                logger.info("Employee with id# " + nonNullEmployee.getEmp_id() + " is successfully updated");
            return "SUCCESS!";
        }
        catch(SQLException sqlException){
            sqlException.fillInStackTrace();
            logger.error("Failed to update");
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        throw new IllegalObjectException("Error: Object not supported");
    }
}
