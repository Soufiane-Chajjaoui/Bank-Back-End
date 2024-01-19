package ma.bank.end.BankBackEnd.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.repositories.AccountOperationRepo;
import ma.bank.end.BankBackEnd.repositories.BankAccountRepo;
import ma.bank.end.BankBackEnd.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ImpBankServiceAccount implements BankAccountService{

    private BankAccountRepo bankAccountRepo;
    private AccountOperationRepo accountOperationRepo;
    private CustomerRepo customerRepo;
    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Has been saved");
        return customerRepo.save(customer);
    }

    @Override
    public BankAccount savebaBankAccount(double initialBalance, String type, Long customerId) {
        return null;
    }

    @Override
    public List<Customer> lisCustomers() {
        return null;
    }

    @Override
    public BankAccount getBankAccount(Long id) {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String Description) {

    }

    @Override
    public void credit(String accountId, double amount, String description) {

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDescription, double amount) {

    }
}
