package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Customer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository implements ICustomerRepository {
    private List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();

        customers.add(new Customer(1L, "Jean", "Jeanerson", "jean@jean.com", LocalDate.of(2022, 1, 1)));
        customers.add(new Customer(2L, "Peter", "Peterson", "peterson@pet.com", LocalDate.of(2021, 1, 1)));
        customers.add(new Customer(3L, "Mark", "Markerson", "mark@mark.com", LocalDate.of(2020, 1, 1)));
    }

    @Override
    public List<Customer>  findAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Customer> saveCustomer(Customer customer) {
        customers.add(customer);
        return Optional.of(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customers = customers.stream().filter(customer -> !customer.getId().equals(id)).collect(Collectors.toList());
    }
}
