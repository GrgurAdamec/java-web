package hr.tvz.adamec.njamapp.controller;

import hr.tvz.adamec.njamapp.model.Customer;
import hr.tvz.adamec.njamapp.model.CustomerCommand;
import hr.tvz.adamec.njamapp.model.CustomerDTO;
import hr.tvz.adamec.njamapp.services.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.finALlCustomers();
    }

    @GetMapping("/byID/{id}")
    public Optional<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.findByID(id);
    }

    @PostMapping("/createNew")
    @Validated
    public Optional<CustomerDTO> createNewCustomer(@Valid @RequestBody CustomerCommand customerCommand) {
        return customerService.saveCustomer(customerCommand);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
