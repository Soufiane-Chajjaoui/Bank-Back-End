//package ma.bank.end.BankBackEnd.RunnerFirst;
//
//import ma.bank.bankingBackEnd.enums.AccountStatus;
//import ma.bank.bankingBackEnd.enums.OperationType;
//import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
//import ma.bank.end.BankBackEnd.entities.*;
//import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
//import ma.bank.end.BankBackEnd.exceptions.BankAccountNotFoundException;
//import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
//import ma.bank.end.BankBackEnd.repositories.AccountOperationRepo;
//import ma.bank.end.BankBackEnd.repositories.BankAccountRepo;
//import ma.bank.end.BankBackEnd.repositories.CustomerRepo;
//import ma.bank.end.BankBackEnd.services.ImpBankServiceAccount;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Stream;
//
//@Component
//@Transactional
//public class MyCommandLineRunner implements CommandLineRunner {
//
//
//
//
//    @Autowired
//    private ImpBankServiceAccount bankService;
//
//
////    public void run(String... args) throws Exception {
////        Stream.of("Soufian" , "Mahdi" , "Ahmed").forEach(client -> {
////            Customer customer = new Customer();
////            customer.setEmail(client + "@gmail.com");
////            customer.setName(client);
////            customerRepo.save(customer);
////
////            CurrentAccount currentAccount = new CurrentAccount();
////             currentAccount.setAccountStatus(AccountStatus.SUSPENDED);
////            currentAccount.setBalance(200.0);
////            currentAccount.setOverDraft(Math.random());
////            currentAccount.setCustomer(customer);
////            currentAccount.setCreateAt(new Date());
////            bankAccountRepo.save(currentAccount);
////
////            SavingAccount savingAccount = new SavingAccount();
////            savingAccount.setAccountStatus(AccountStatus.SUSPENDED);
////            savingAccount.setBalance(200.0);
////            savingAccount.setInterestRate(290);
////            savingAccount.setCustomer(customer);
////            savingAccount.setCreateAt(new Date());
////            bankAccountRepo.save(savingAccount);
////        });
////        bankAccountRepo.findAll().forEach(acc -> {
////
////            for (int i = 0 ; i < 10 ; i++){
////                AccountOperation accountOperation = new AccountOperation();
////                accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
////                accountOperation.setBankAccount(acc);
////                accountOperation.setOperationDate(new Date());
////                accountOperation.setAmount(2000.0);
////                accountOperationRepo.save(accountOperation);
////            }
////        });
////    }
//
//
////    @Override
////    public void run(String... args) throws Exception {
////        Long id = 2L ;
////        BankAccount bankAccount =
////                bankAccountRepo.findById(id).orElse(null);
////        if (bankAccount != null){
////            System.out.println(bankAccount.getBalance());
////            System.out.println(bankAccount.getCustomer().getName());
////            bankAccount.getOperations().forEach(op -> {
////                System.out.println( op.getType() + " " + op.getOperationDate());
////            });
////            if (bankAccount instanceof SavingAccount){
////                System.out.println("Rate =>" +((SavingAccount) bankAccount).getInterestRate());
////            }else {
////                System.out.println("Over Draft =>" + ((CurrentAccount) bankAccount).getOverDraft());
////            }
////        }
////    }
//
//    @Override
//    public void run(String... args) {
//        String[] customers = {"Soufian" , "Mahdi" , "Mohamed" , "Ahmed"};
//        Arrays.stream(customers).forEach(c -> {
//            CustomerDTO customer = new CustomerDTO();
//            customer.setEmail(c + "@gmail.com");
//            customer.setName(c);
//            bankService.saveCustomer(customer);
//        });
//        bankService.lisCustomers().forEach(customer->{
//            try {
//                bankService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
//                bankService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
//                bankService.listofBankAccount().forEach(account -> {
//                    for (int i = 0 ; i < 10 ; i++){
//                        try {
//                            bankService.credit(account.getId() , 10000+Math.random()*120000 , "Credit");
//                            bankService.debit(account.getId() , 1000+Math.random()*9000 , "Debit" );
//                        } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                });
//            } catch (CustomerNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}
