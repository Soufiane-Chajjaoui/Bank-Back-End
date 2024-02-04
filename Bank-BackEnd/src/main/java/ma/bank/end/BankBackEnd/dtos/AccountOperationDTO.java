package ma.bank.end.BankBackEnd.dtos;

import jakarta.persistence.*;
import lombok.Data;
import ma.bank.end.BankBackEnd.entities.BankAccount;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount ;
    private ma.bank.bankingBackEnd.enums.OperationType type;
    private BankAccount bankAccount;
    private String Description ;
}
