package ma.bank.end.BankBackEnd.web;

import lombok.AllArgsConstructor;
import ma.bank.end.BankBackEnd.dtos.BankAccountDTO;
import ma.bank.end.BankBackEnd.dtos.SavingAccountDTO;
import ma.bank.end.BankBackEnd.services.BankAccountService;
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
    public BankAccountDTO getBankAccount(@PathVariable Long id) {

            return  bankAccountService.getBankAccount(id)  ;
    }
    @PostMapping
    public SavingAccountDTO AddSavingAccountDTO(@RequestBody SavingAccountDTO savingAccountDTO){
        return bankAccountService.saveSavingBankAccount(savingAccountDTO.getBalance() , savingAccountDTO.getInterestRate() , savingAccountDTO.getCustomer().getId());
    }






}
