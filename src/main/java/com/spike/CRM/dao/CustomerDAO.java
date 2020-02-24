package com.spike.CRM.dao;

import com.spike.CRM.entity.Customer;

import java.util.List;


public interface CustomerDAO {
    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);
}
