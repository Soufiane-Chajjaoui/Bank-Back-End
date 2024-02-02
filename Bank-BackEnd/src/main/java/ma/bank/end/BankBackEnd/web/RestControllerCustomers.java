package ma.bank.end.BankBackEnd.web;

import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import ma.bank.end.BankBackEnd.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cutomers")
public class RestControllerCustomers {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<CustomerDTO> getCustomers(){
        return bankAccountService.lisCustomers();
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomerByID(@RequestParam Long id)  {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getCustomer(id));

        }catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no Content");
        }
    }
}
