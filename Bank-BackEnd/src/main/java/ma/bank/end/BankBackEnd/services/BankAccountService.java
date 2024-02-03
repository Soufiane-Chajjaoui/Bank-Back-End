package ma.bank.end.BankBackEnd.services;

import ma.bank.end.BankBackEnd.dtos.BankAccountDTO;
import ma.bank.end.BankBackEnd.dtos.CurrentAccountDTO;
import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
import ma.bank.end.BankBackEnd.dtos.SavingAccountDTO;
import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
import ma.bank.end.BankBackEnd.exceptions.BankAccountNotFoundException;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import java.util.List;

public interface BankAccountService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerID) ;

    BankAccountDTO getBankAccount(Long id) throws BankAccountNotFoundException ;
    CurrentAccountDTO saveCurrentBankAccount(double initialBalance , double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingBankAccount(double initialBalance , double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> lisCustomers();
    void debit(Long accountId , double amount , String Description) throws BalanceNotSufficientException, BankAccountNotFoundException;
    void credit(Long accountId,double amount , String description) throws  BankAccountNotFoundException;
    void transfer(Long accountIdSource , Long accountIdDestination , double amount) throws BankAccountNotFoundException, BalanceNotSufficientException ;

    List<BankAccount> listofBankAccount();

    CustomerDTO getCustomer(Long id) throws CustomerNotFoundException;
}
