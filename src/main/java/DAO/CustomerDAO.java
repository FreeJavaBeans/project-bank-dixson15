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


    @Override
    public String saveCustomer(Customer customer) {

        try{
            String sql = "insert into customer(first_name, last_name, customer_id)" +
                " VALUES(?,?,?)";

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,customer.getFirstName());
            preparedStatement.setString(2,customer.getLastName());
            preparedStatement.setInt(3,customer.getCustomer_id());

            int row = preparedStatement.executeUpdate();

            if(row > 0){
                connection.commit();
                connection.close();
                return "Row successfully updated!!!";
            }
            else{
                connection.rollback();
                connection.close();
                return "Failed to update row";
            }
        }
        catch (SQLException sqlException){
            sqlException.fillInStackTrace();
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return "Error -- Duplicated id: " + customer.getCustomer_id();
    }

    @Override
    public void getCustomer(int customer_id) {

        String sql = "select * from customer where customer_id = ?";
        //prepare a sql statement based on the id
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, customer_id);

            //execute query
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer(rs.getString("first_name"),
                        rs.getString("last_name"), rs.getInt("customer_id"));

                System.out.println("CUSTOMER ID#: " + customer.getCustomer_id() + "\n" + "CUSTOMER FIRSTNAME: "
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
    public Optional<Customer> updateCustomer(int customer_id, Customer customer) {

        String sql = "UPDATE customer "
                + "SET last_name = ? "
                + "WHERE customer_id = ?";

        int rows = 0;

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, customer.getLastName());
            preparedStatement.setInt(2, customer.getCustomer_id());

            rows = preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            LOGGER.log(Level.SEVERE, null, sqlException);
        }
        return  null;
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
