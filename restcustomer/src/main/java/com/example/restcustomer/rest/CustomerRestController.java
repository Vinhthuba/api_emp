package com.example.restcustomer.rest;
import com.example.restcustomer.dao.CustomerRepository;
import  com.example.restcustomer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private CustomerRepository repository;
    @Autowired
    public CustomerRestController(CustomerRepository theCustomerRepository) {
        repository = theCustomerRepository;
    }

    @GetMapping("/cus")
    List<Customer> all(){
        return repository.findAll();
    }

    @PostMapping("/cus")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    @GetMapping("/cus/{id}")
    Customer one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/cus/{id}")
    Customer updateCustomer(@RequestBody Customer updateCus, @PathVariable Integer id) {
        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(updateCus.getFirstName());
                    customer.setLastName(updateCus.getLastName());
                    customer.setEmail(updateCus.getEmail());
                    return repository.save(customer);
                })
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @DeleteMapping("cus/{id}")
    void deleteCustomer(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
