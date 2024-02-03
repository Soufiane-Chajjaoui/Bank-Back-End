package ma.bank.end.BankBackEnd.entities;
 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.bank.bankingBackEnd.enums.AccountStatus;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE" , length = 4 , discriminatorType = DiscriminatorType.STRING)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double balance ;
    private Date createAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount" ,cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountOperation> operations;
}