package ma.bank.end.BankBackEnd.RunnerFirst;

import ma.bank.bankingBackEnd.enums.AccountStatus;
import ma.bank.bankingBackEnd.enums.OperationType;
import ma.bank.end.BankBackEnd.entities.AccountOperation;
import ma.bank.end.BankBackEnd.entities.CurrentAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.entities.SavingAccount;
import ma.bank.end.BankBackEnd.repositories.AccountOperationRepo;
import ma.bank.end.BankBackEnd.repositories.BankAccountRepo;
import ma.bank.end.BankBackEnd.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Stream;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountOperationRepo accountOperationRepo;

    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Override
    public void run(String... args) throws Exception {
        Stream.of("Soufian" , "Mahdi" , "Ahmed").forEach(client -> {
            Customer customer = new Customer();
            customer.setEmail(client + "@gmail.com");
            customer.setName(client);
            customerRepo.save(customer);

            CurrentAccount currentAccount = new CurrentAccount();
             currentAccount.setAccountStatus(AccountStatus.SUSPENDED);
            currentAccount.setBalance(200.0);
            currentAccount.setOverDraft(Math.random());
            currentAccount.setCustomer(customer);
            currentAccount.setCreateAt(new Date());
            bankAccountRepo.save(currentAccount);

            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setAccountStatus(AccountStatus.SUSPENDED);
            savingAccount.setBalance(200.0);
            savingAccount.setInterestRate(290);
            savingAccount.setCustomer(customer);
            savingAccount.setCreateAt(new Date());
            bankAccountRepo.save(savingAccount);
        });
        bankAccountRepo.findAll().forEach(acc -> {

            for (int i = 0 ; i < 10 ; i++){
                AccountOperation accountOperation = new AccountOperation();
                accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                accountOperation.setBankAccount(acc);
                accountOperation.setOperationDate(new Date());
                accountOperation.setAmount(2000.0);
                accountOperationRepo.save(accountOperation);
            }
        });
    }
}
