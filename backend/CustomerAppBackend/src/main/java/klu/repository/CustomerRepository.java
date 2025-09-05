package klu.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klu.entity.CustomerEntity;





@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    void deleteByName(String name);
}

