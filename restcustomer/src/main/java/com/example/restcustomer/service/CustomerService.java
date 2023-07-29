package com.example.restcustomer.service;
import  com.example.restcustomer.entity.Customer;
import java.util.List;
public interface CustomerService {
    public List<Customer> findAll();
    public Customer findById(int theId);
    public void save(Customer theCustomer);
    public void deleteById(int theId);
    public List<Customer> searchByName(String theName);
}
