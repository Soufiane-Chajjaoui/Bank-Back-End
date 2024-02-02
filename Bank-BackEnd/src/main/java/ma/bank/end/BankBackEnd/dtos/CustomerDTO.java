package ma.bank.end.BankBackEnd.dtos;

import lombok.Data;

@Data
// this class we implemented just by property and them getters & setters
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;

}
