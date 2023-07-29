package com.example.restcustomer.service;
import com.example.restcustomer.dao.CustomerRepository;
import com.example.restcustomer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
        customerRepository = theCustomerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Customer findById(int theId) {
        Optional<Customer> result = customerRepository.findById(theId);

        Customer theCustomer = null;
        if(result.isPresent()) {
            theCustomer = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theCustomer;
    }

    @Override
    public void save(Customer theEmployee) {
        customerRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }

    @Override
    public List<Customer> searchByName(String theName) {
        List<Customer> results = null;
        if (theName != null && (theName.trim().length() > 0)) {
            results = customerRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
        }
        else {
            results = findAll();
        }

        return results;
    }
}