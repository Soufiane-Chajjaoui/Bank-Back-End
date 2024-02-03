package ma.bank.end.BankBackEnd.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.bank.bankingBackEnd.enums.OperationType;
import ma.bank.end.BankBackEnd.dtos.BankAccountDTO;
import ma.bank.end.BankBackEnd.dtos.CurrentAccountDTO;
import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
import ma.bank.end.BankBackEnd.dtos.SavingAccountDTO;
import ma.bank.end.BankBackEnd.entities.*;
import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
import ma.bank.end.BankBackEnd.exceptions.BankAccountNotFoundException;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import ma.bank.end.BankBackEnd.mappers.BankAccountMapper;
import ma.bank.end.BankBackEnd.repositories.AccountOperationRepo;
import ma.bank.end.BankBackEnd.repositories.BankAccountRepo;
import ma.bank.end.BankBackEnd.repositories.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ImpBankServiceAccount implements BankAccountService{

    private BankAccountRepo bankAccountRepo;
    private AccountOperationRepo accountOperationRepo;
    private CustomerRepo customerRepo;
    private BankAccountMapper dtoMapper;
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Has been saved");

        Customer Savedcustomer = customerRepo.save(dtoMapper.fromCustomerDTO(customerDTO));
        return dtoMapper.fromCustomer(Savedcustomer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Has been update");
        Customer Savedcustomer = customerRepo.save(dtoMapper.fromCustomerDTO(customerDTO));
        return dtoMapper.fromCustomer(Savedcustomer);
    }

    @Override
    public void deleteCustomer(Long customerID){

        customerRepo.deleteById(customerID);
    }
    @Override
    public CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
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
        return  dtoMapper.fromCurrentAccount(saveBankAccount);
    }



    @Override
    public SavingAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
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
        return  dtoMapper.fromSavingAccount(saveBankAccount);
    }

    @Override
    public List<CustomerDTO> lisCustomers() {
        List<Customer> customers = customerRepo.findAll() ;

        return customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO getBankAccount(Long id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepo.findById(id).orElseThrow(()-> new BankAccountNotFoundException("Account Not FOund"));
        if (bankAccount instanceof SavingAccount)
            return dtoMapper.fromSavingAccount((SavingAccount) bankAccount);
        return dtoMapper.fromCurrentAccount((CurrentAccount) bankAccount) ;
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
    public List<BankAccountDTO> listofBankAccount(){
        return  bankAccountRepo.findAll().stream().map(account->{
            if (account instanceof SavingAccount)
                return  dtoMapper.fromSavingAccount((SavingAccount) account);
            return dtoMapper.fromCurrentAccount((CurrentAccount) account);
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        return  dtoMapper.fromCustomer(customer);
    }

}
