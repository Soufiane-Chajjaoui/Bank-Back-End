package ma.bank.end.BankBackEnd.services;

import ma.bank.end.BankBackEnd.dtos.*;
import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import ma.bank.end.BankBackEnd.exceptions.EntityNotFoundException;

import java.util.List;

public interface BankAccountService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerID) ;

    BankAccountDTO getBankAccount(Long id) throws EntityNotFoundException;
    CurrentAccountDTO saveCurrentBankAccount(double initialBalance , double overDraft, Long customerId) throws EntityNotFoundException;
    SavingAccountDTO saveSavingBankAccount(double initialBalance , double interestRate, Long customerId) throws EntityNotFoundException;
    List<CustomerDTO> lisCustomers();
    void debit(Long accountId , double amount , String Description) throws BalanceNotSufficientException, EntityNotFoundException;
    void credit(Long accountId,double amount , String description) throws  EntityNotFoundException;
    void transfer(Long accountIdSource , Long accountIdDestination , double amount) throws EntityNotFoundException, BalanceNotSufficientException ;

    List<BankAccountDTO> listofBankAccount();

    List<AccountOperationDTO> accountHistory(Long AccountID) throws EntityNotFoundException ;
    CustomerDTO getCustomer(Long id) throws EntityNotFoundException;
}
