package hr.tvz.adamec.njamapp.services;

import hr.tvz.adamec.njamapp.model.Customer;
import hr.tvz.adamec.njamapp.model.CustomerCommand;
import hr.tvz.adamec.njamapp.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<CustomerDTO> finALlCustomers();
    Optional<CustomerDTO> findByID(Long id);
    Optional<CustomerDTO> saveCustomer(CustomerCommand customerCommand);
    void deleteCustomer(Long id);
}
