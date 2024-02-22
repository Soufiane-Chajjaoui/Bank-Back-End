package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;
import ma.bank.end.BankBackEnd.entities.AccountOperation;
import ma.bank.end.BankBackEnd.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Data
public class BankAccountDTO {
    private Long id ;
    private double balance ;
    private AccountStatus accountStatus;
    private CustomerDTO customer;
    private List<AccountOperation> operations;
    private String type;
    private Date createAt;
    private Boolean delete = false;
}
