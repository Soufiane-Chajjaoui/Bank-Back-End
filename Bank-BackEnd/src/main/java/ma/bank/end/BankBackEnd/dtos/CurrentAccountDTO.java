package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;
import ma.bank.end.BankBackEnd.enums.AccountStatus;
import ma.bank.end.BankBackEnd.entities.AccountOperation;

import java.util.Date;
import java.util.List;


@Data
public class CurrentAccountDTO extends BankAccountDTO{
    private double overDraft;
}