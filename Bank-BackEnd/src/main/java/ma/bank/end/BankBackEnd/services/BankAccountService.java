package ma.bank.end.BankBackEnd.services;

import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount savebaBankAccount(double initialBalance , String type , Long customerId);
    List<Customer> lisCustomers();
    BankAccount getBankAccount(Long id);
    void debit(String accountId , double amount , String Description);
    void credit(String accountId,double amount , String description);
    void transfer(String accountIdSource , String accountIdDescription , double amount);
}
