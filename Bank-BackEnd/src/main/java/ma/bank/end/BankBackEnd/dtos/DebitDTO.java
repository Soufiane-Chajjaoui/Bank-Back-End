package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;

@Data
public class DebitDTO {
    private Long accountID ;
    private int amount;
    private String description;
}
