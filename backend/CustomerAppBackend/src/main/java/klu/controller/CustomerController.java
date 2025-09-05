package klu.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klu.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String name) {
        service.removeCustomerByName(name);
        return ResponseEntity.ok("Customer removed successfully");
    }

    // 🔥 Fix: Add this method to handle GET requests
    @GetMapping
    public ResponseEntity<String> getAllCustomers() {
        return ResponseEntity.ok("List of customers");
    }
}
