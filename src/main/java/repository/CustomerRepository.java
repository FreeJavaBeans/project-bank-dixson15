package repository;

import model_entity.Customer;
import model_entity.Person;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    /**
     *
     * @param customer
     * @return person id
     */
    String saveCustomer(Customer customer);

    /**
     *
     * @param customer_id
     * @return Customer object
     */
    void getCustomer(int customer_id);

    /**
     *
     * @param customer_id
     * @param customer
     * @return updated customer id
     */
    Optional<Customer> updateCustomer(int customer_id, Customer customer);

    /**
     *
     * @param customer_id
     * @return Customer id
     */
    String deleteCustomer(int customer_id);
}
