package ma.bank.end.BankBackEnd.repositories;

import ma.bank.end.BankBackEnd.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount , Long> {

    @Query("SELECT BA FROM BankAccount BA WHERE BA.deleted = false")
    List<BankAccount> findAllNotDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount b SET b.deleted = true WHERE b.id = :id")
    void softDelete(Long id);

    @Query("SELECT BA FROM BankAccount BA WHERE BA.deleted = false AND BA.id = :id")
    Optional<BankAccount> findByIdNotDeleted(Long id);

}
