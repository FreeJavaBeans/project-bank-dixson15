package DAO;

import db_connection.PostgresConnection;
import model_entity.Customer;
import repository.CustomerRepository;
import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class CustomerDAO implements CustomerRepository {

    /**
     * instance fields
     */
    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private static final Connection connection = PostgresConnection.getConnection();
    //private static Statement statement;
    private static PreparedStatement preparedStatement;

    /**
     * @param customer
     * @return
     */
    @Override
    public String saveCustomer(Customer customer) {

        try{
            String sql = "insert into customer(customer_id,first_name, last_name)" +
                " VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,customer.getEmp_id());
            preparedStatement.setString(2,customer.getFirstName());
            preparedStatement.setString(3,customer.getLastName());

            int row = preparedStatement.executeUpdate();

            if(row > 0){

                return "Row successfully updated!!!";
            }
            else{
                connection.rollback();
                return "Failed to update row";
            }

        }
        catch (SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return "Error -- Duplicated id: " + customer.getEmp_id();
    }

    @Override
    public void getCustomer(int customer_id) {

        String sql = "select * from customer where customer_id = ?";
        //prepare a sql statement based on the id
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, customer_id);

            //execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("customer_id"),resultSet.getString("first_name"),
                        resultSet.getString("last_name"));

                System.out.println("CUSTOMER ID#: " + customer.getEmp_id() + "\n" + "CUSTOMER FIRSTNAME: "
                                                    + customer.getFirstName() + "\n" + "CUSTOMER LAST NAME: "
                                                    + customer.getLastName());
                LOGGER.log(Level.INFO, "Found {0} in database", customer);
            }
            else
                System.out.println("No customer found with ID: " + customer_id);
            connection.close();

        }catch (SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
    }

    @Override
    public Optional<Customer> updateCustomer(Customer customer) {

        try {
            String sql = "UPDATE customer "
                    + "SET first_name = ?, "
                    + "last_name = ? "
                    + "WHERE customer_id = ?";

             preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getEmp_id());

            int updatedRows = preparedStatement.executeUpdate();

            if(updatedRows > 0)
                System.out.println("Account successfully update with ID#: " + customer.getEmp_id());
            else
                System.out.println("Unable to update account!");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return null;
    }

    @Override
    public String deleteCustomer(int customer_id) {

        String SQL = "DELETE FROM customer WHERE customer_id = ?";

        int rows = 0;

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, customer_id);

            rows = preparedStatement.executeUpdate();

            if(rows > 0){
                connection.close();
                return "______________________________________\n"+ "Customer successfully deleted with Id#: "
                                                                 + customer_id;
            }
            else
                return "Could not found Id#: "+ customer_id;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return null;
    }
}
