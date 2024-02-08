package ma.bank.end.BankBackEnd.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
// this class we implemented just by property and them getters & setters
public class CustomerDTO {
    private Long id;
    private String name;
    @NotNull
    @Email
    private String email;

}
