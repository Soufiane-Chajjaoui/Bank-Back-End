package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;

@Data
public class TransferDTO {
    private Long accountID ;
    private int amount;
    private Long destination;
    private String description;
}
