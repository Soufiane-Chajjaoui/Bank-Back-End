package ma.bank.end.BankBackEnd.services;

import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveCurrentBankAccount(double initialBalance , double overDraft, Long customerId) throws CustomerNotFoundException;
    BankAccount saveSavingBankAccount(double initialBalance , double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> lisCustomers();
    BankAccount getBankAccount(Long id);
    void debit(String accountId , double amount , String Description);
    void credit(String accountId,double amount , String description);
    void transfer(String accountIdSource , String accountIdDescription , double amount);
}
