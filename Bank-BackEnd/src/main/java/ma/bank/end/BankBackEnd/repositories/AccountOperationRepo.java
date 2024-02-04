package ma.bank.end.BankBackEnd.repositories;

import ma.bank.end.BankBackEnd.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountOperationRepo extends JpaRepository<AccountOperation, Long> {
        List<AccountOperation> findByBankAccountId(Long id);
}
