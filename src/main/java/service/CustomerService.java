package service;

import DAO.CustomerDAO;
import model_entity.Customer;

import java.util.Optional;

public class CustomerService {

    private static CustomerDAO customerDAO;


    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }


    public String saveCustomer(Customer customer){

        return customerDAO.saveCustomer(customer);
    }

    public void getCustomer(int customer_id){
        customerDAO.getCustomer(customer_id);
    }

    public Optional<Customer> updateCustomer(int customer_id, Customer customer){
        return customerDAO.updateCustomer(customer);
    }

    public String deleteCustomer(int customer_id){
        return customerDAO.deleteCustomer(customer_id);
    }
}
