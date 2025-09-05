package klu.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import klu.repository.CustomerRepository;


@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void removeCustomerByName(String name) {
        repository.deleteByName(name);
    }
}

