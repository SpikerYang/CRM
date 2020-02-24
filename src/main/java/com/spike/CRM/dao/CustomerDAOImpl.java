package com.spike.CRM.dao;

import com.spike.CRM.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query and sort by Last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //save the customer if not exist, or update
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        return sessionFactory.getCurrentSession().get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query and sort by Last name
        Query<Customer> theQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);


        theQuery.executeUpdate();
    }
}
