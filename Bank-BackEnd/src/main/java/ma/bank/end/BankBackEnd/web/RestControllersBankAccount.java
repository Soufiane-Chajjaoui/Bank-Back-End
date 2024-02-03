package ma.bank.end.BankBackEnd.web;

import lombok.AllArgsConstructor;
import ma.bank.end.BankBackEnd.dtos.BankAccountDTO;
import ma.bank.end.BankBackEnd.exceptions.BankAccountNotFoundException;
import ma.bank.end.BankBackEnd.services.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BankAccounts")
@AllArgsConstructor
public class RestControllersBankAccount {

    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccountDTO> getBankAccounts(){
        return bankAccountService.listofBankAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBankAccount(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(bankAccountService.getBankAccount(id) , HttpStatus.FOUND);
        }catch (BankAccountNotFoundException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }






}
