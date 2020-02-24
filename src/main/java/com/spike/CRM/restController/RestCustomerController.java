package com.spike.CRM.restController;

import com.spike.CRM.entity.Customer;
import com.spike.CRM.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestCustomerController {
    @Autowired
    private CustomerService customerService;

    // inject redis template
    @Autowired
    private RedisTemplate<String, Object> template;


    // get customer list REST API
    @GetMapping("/list")
    public List<Customer> listCustomers() {
        template.opsForValue().setIfAbsent("name","yqr");
        System.out.println(template.opsForValue().get("name"));
        return customerService.getCustomers();
    }

    // get customer by Id REST API
    @GetMapping("/getCustomer/{studentId")
    public Customer getCustomerById(@PathVariable int studentId) {
        return customerService.getCustomers().get(studentId);
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer() {


        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd() {



        return "customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFOrmForUpdate() {


        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer() {


        return "redirect:/customer/list";
    }
}
