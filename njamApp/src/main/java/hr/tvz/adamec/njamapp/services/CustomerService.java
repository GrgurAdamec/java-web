package hr.tvz.adamec.njamapp.services;

import hr.tvz.adamec.njamapp.model.Customer;
import hr.tvz.adamec.njamapp.model.CustomerCommand;
import hr.tvz.adamec.njamapp.model.CustomerDTO;
import hr.tvz.adamec.njamapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public List<CustomerDTO> finALlCustomers() {
        return customerRepository.findAllCustomers().stream().map(this::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> findByID(Long id) {
        return customerRepository.findById(id).map(this::convertCustomerToCustomerDTO);
    }

    @Override
    public Optional<CustomerDTO> saveCustomer(CustomerCommand customerCommand) {
        return customerRepository.saveCustomer(convertCustomerCommandToCustomer(customerCommand)).map(this::convertCustomerToCustomerDTO);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomer(id);
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());

        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setEmail(customerDTO.getEmail());

        return customer;
    }

    private Customer convertCustomerCommandToCustomer(CustomerCommand customerCommand) {
        Customer customer = new Customer();

        customer.setId(customerCommand.getId());
        customer.setName(customerCommand.getName());
        customer.setSurname(customerCommand.getSurname());
        customer.setEmail(customerCommand.getEmail());
        customer.setDateOfBirth(customerCommand.getDateOfBirth());

        return customer;
    }
}
