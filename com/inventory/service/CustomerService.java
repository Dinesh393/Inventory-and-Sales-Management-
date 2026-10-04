package com.inventory.service;

import com.inventory.model.Customer;
import com.inventory.repository.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    public void addCustomer(Customer customer) throws SQLException {

        if (customer == null) return;

        if (customer.getCustomerName() == null) return;

        if (customer.getCustomerName().isBlank()) return;

        CustomerRepository repository = new CustomerRepository();

        Customer existingCustomer =
                repository.findByName(customer.getCustomerName());

        if (existingCustomer != null) return;

        repository.addCustomer(customer);
    }


    public List<Customer> findAllCustomers() throws SQLException {

        CustomerRepository repository = new CustomerRepository();

        return repository.findAllCustomers();
    }


    public Customer findById(int customerId) throws SQLException {

        if (customerId <= 0) return null;

        CustomerRepository repository = new CustomerRepository();

        return repository.findById(customerId);
    }


    public Customer findByName(String customerName) throws SQLException {

        if (customerName == null) return null;

        if (customerName.isBlank()) return null;

        CustomerRepository repository = new CustomerRepository();

        return repository.findByName(customerName);
    }


    public void updateCustomer(Customer customer) throws SQLException {

        if (customer == null) return;

        if (customer.getCustomerId() <= 0) return;

        if (customer.getCustomerName() == null) return;

        if (customer.getCustomerName().isBlank()) return;

        CustomerRepository repository = new CustomerRepository();

        Customer existingCustomer =
                repository.findById(customer.getCustomerId());

        if (existingCustomer == null) return;

        Customer customerWithSameName =
                repository.findByName(customer.getCustomerName());

        if (customerWithSameName != null &&
            customerWithSameName.getCustomerId() != customer.getCustomerId()) {
            return;
        }

        repository.updateCustomer(customer);
    }


    public void deleteCustomer(int customerId) throws SQLException {

        if (customerId <= 0) return;

        CustomerRepository repository = new CustomerRepository();

        Customer existingCustomer =
                repository.findById(customerId);

        if (existingCustomer == null) return;

        repository.deleteCustomer(customerId);
    }
}