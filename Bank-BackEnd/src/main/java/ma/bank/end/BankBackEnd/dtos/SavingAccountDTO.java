package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;
import ma.bank.bankingBackEnd.enums.AccountStatus;
import ma.bank.end.BankBackEnd.entities.AccountOperation;
import ma.bank.end.BankBackEnd.entities.Customer;

import java.util.Date;
import java.util.List;


@Data
public abstract class SavingAccountDTO {
    private Long id ;
    private double balance ;
    private Date createAt;
    private AccountStatus accountStatus;
    private Customer customer;
    private List<AccountOperation> operations;
    private double interestRate;
}