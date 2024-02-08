package ma.bank.end.BankBackEnd.web;

import lombok.AllArgsConstructor;
import ma.bank.end.BankBackEnd.dtos.BankAccountDTO;
import ma.bank.end.BankBackEnd.dtos.CurrentAccountDTO;
import ma.bank.end.BankBackEnd.dtos.SavingAccountDTO;
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
    public BankAccountDTO getBankAccount(@PathVariable Long id) {

            return  bankAccountService.getBankAccount(id)  ;
    }
    @PostMapping("SaveSavingAccount")
    public SavingAccountDTO AddSavingAccountDTO(@RequestBody SavingAccountDTO savingAccountDTO){
        return bankAccountService.saveSavingBankAccount(savingAccountDTO.getBalance() , savingAccountDTO.getInterestRate() , savingAccountDTO.getCustomer().getId());
    }

    @PostMapping("/SaveCurrentAccount")
    public CurrentAccountDTO AddCurrentAccountDTO(@RequestBody CurrentAccountDTO currentAccountDTO){
        return bankAccountService.saveCurrentBankAccount(currentAccountDTO.getBalance() , currentAccountDTO.getOverDraft() , currentAccountDTO.getCustomer().getId());
    }


    @GetMapping("/AccountOperations")
    public ResponseEntity<?> AccountOperations(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.accountHistory(id));
    }

//    @PostMapping("/SaveAccountOperationDebit")
//    public ResponseEntity<?> SaveDebit(){
//        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.debit());
//    }



}
