package ma.bank.end.BankBackEnd.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.bank.bankingBackEnd.enums.OperationType;
import ma.bank.end.BankBackEnd.entities.*;
import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
import ma.bank.end.BankBackEnd.exceptions.BankAccountNotFoundException;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import ma.bank.end.BankBackEnd.repositories.AccountOperationRepo;
import ma.bank.end.BankBackEnd.repositories.BankAccountRepo;
import ma.bank.end.BankBackEnd.repositories.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not Found");
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(new Random().nextLong());
        currentAccount.setCreateAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount saveBankAccount = bankAccountRepo.save(currentAccount);
        return  saveBankAccount;
    }



    @Override
    public BankAccount saveSavingBankAccount(double initialBalance,double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not Found");
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(new Random().nextLong());
        savingAccount.setCreateAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount saveBankAccount = bankAccountRepo.save(savingAccount);
        return  saveBankAccount;
    }

    @Override
    public List<Customer> lisCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public BankAccount getBankAccount(Long id) {
        return bankAccountRepo.findById(id).orElse(null);
    }

    @Override
    public void debit(Long accountId, double amount, String description) throws BalanceNotSufficientException , BankAccountNotFoundException {
        BankAccount bankAccount= bankAccountRepo.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
        if(bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepo.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepo.save(bankAccount);
    }

    @Override
    public void credit(Long accountId, double amount, String description) throws  BankAccountNotFoundException{
        BankAccount bankAccount=bankAccountRepo.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepo.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepo.save(bankAccount);
    }

    @Override
    public void transfer(Long accountIdSource, Long accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }

    @Override
    public List<BankAccount> listofBankAccount(){
        return  bankAccountRepo.findAll();
    }
}
