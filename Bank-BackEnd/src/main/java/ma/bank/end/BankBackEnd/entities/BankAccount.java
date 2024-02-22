package ma.bank.end.BankBackEnd.entities;
 import com.fasterxml.jackson.annotation.JsonProperty;
 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 import ma.bank.end.BankBackEnd.enums.AccountStatus;
 import org.hibernate.annotations.CreationTimestamp;

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

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @ManyToOne
    private Customer customer;

    private Boolean deleted = false ;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "createAt" , nullable = false , updatable = false)
    private Date createAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "bankAccount" ,cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountOperation> operations;
}