package ma.bank.end.BankBackEnd.web;

import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cutomers")
public class RestControllerCustomers {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<Customer> getCustomers(){
        return bankAccountService.lisCustomers();
    }
}
