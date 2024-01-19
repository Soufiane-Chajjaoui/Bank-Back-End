package ma.bank.end.BankBackEnd.services;

import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpBankServiceAccount implements BankAccountService{
    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
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
