package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {
    List<Customer> findAllCustomers();
    Optional<Customer> findById(Long id);
    Optional<Customer> saveCustomer(Customer customer);
    void deleteCustomer(Long id);
}
