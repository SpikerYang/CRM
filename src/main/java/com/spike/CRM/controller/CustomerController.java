package com.spike.CRM.controller;


import com.spike.CRM.dao.CustomerDAO;
import com.spike.CRM.entity.Customer;
import com.spike.CRM.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        // add the customers to the model
        theModel.addAttribute("customers", customerService.getCustomers());

        return "list-customers";

    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Customer customer = new Customer();
        theModel.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFOrmForUpdate(@RequestParam("customerId") int theId, Model theModel) {

        // set the customer from database
        Customer theCustomer = customerService.getCustomer(theId);

        // set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);

        // send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        //delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }
}
