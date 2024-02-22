package ma.bank.end.BankBackEnd.repositories;

import ma.bank.end.BankBackEnd.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer , Long> {
    List<Customer> findByNameContains(String name);
}
