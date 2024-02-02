package ma.bank.end.BankBackEnd.services;

import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
import ma.bank.end.BankBackEnd.exceptions.BankAccountNotFoundException;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveCurrentBankAccount(double initialBalance , double overDraft, Long customerId) throws CustomerNotFoundException;
    BankAccount saveSavingBankAccount(double initialBalance , double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> lisCustomers();
    BankAccount getBankAccount(Long id);
    void debit(Long accountId , double amount , String Description) throws BalanceNotSufficientException, BankAccountNotFoundException;
    void credit(Long accountId,double amount , String description) throws  BankAccountNotFoundException;
    void transfer(Long accountIdSource , Long accountIdDestination , double amount) throws BankAccountNotFoundException, BalanceNotSufficientException ;

    List<BankAccount> listofBankAccount();

    CustomerDTO getCustomer(Long id) throws CustomerNotFoundException;
}
