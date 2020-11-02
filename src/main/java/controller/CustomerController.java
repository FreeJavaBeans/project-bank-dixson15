package controller;

import DAO.CustomerDAO;
import model_entity.Customer;
import service.CustomerService;

import java.util.Optional;

public class CustomerController {

    public static CustomerService customerService;

    public CustomerController() {
       this.customerService = new CustomerService();
    }

    public String saveCustomer(Customer customer){

        return customerService.saveCustomer(customer);
    }

    public void getCustomer(int customer_id){
        customerService.getCustomer(customer_id);
    }

    public Optional<Customer> updateCustomer(int customer_id, Customer customer){
        return customerService.updateCustomer(customer_id,customer);
    }

    public String deleteCustomer(int customer_id){
        return customerService.deleteCustomer(customer_id);
    }
}
