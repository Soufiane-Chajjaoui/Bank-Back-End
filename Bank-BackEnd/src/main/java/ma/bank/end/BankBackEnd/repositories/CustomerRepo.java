package ma.bank.end.BankBackEnd.repositories;

import ma.bank.end.BankBackEnd.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer , Long> {}
