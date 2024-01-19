package ma.bank.end.BankBackEnd.repositories;

import ma.bank.end.BankBackEnd.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount , Long> {
}
