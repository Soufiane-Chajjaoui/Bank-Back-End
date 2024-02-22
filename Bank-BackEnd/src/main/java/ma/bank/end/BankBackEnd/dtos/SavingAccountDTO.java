package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;
import ma.bank.end.BankBackEnd.entities.AccountOperation;
import ma.bank.end.BankBackEnd.enums.AccountStatus;

import java.util.Date;
import java.util.List;


@Data
public class SavingAccountDTO extends BankAccountDTO{

    private double interestRate;
}