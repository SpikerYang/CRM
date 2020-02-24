package com.spike.CRM.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spike.CRM.entity.Customer;

public class TestJackson {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
