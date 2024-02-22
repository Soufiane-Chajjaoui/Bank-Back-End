package ma.bank.end.BankBackEnd.web;

import lombok.AllArgsConstructor;
import ma.bank.end.BankBackEnd.dtos.*;
import ma.bank.end.BankBackEnd.entities.AccountOperation;
import ma.bank.end.BankBackEnd.exceptions.BalanceNotSufficientException;
import ma.bank.end.BankBackEnd.exceptions.EntityNotFoundException;
import ma.bank.end.BankBackEnd.services.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ApiV1/BankAccounts")
@AllArgsConstructor
@CrossOrigin("*")
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
        return bankAccountService.saveCurrentBankAccount(currentAccountDTO.getBalance() , currentAccountDTO.getOverDraft() , currentAccountDTO.getAccountStatus() , currentAccountDTO.getCustomer().getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Long id){
        bankAccountService.deleteBankAccount(id);
        return  ResponseEntity.ok(true);
    }


    @GetMapping("/AccountOperations")
    public ResponseEntity<List<AccountOperationDTO>> AccountOperations(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.accountHistory(id));
    }

    @GetMapping("/AccountHistory")
    public ResponseEntity<AccountHistoryDTO> getAccountHistory(@RequestParam Long id,
                                                                @RequestParam(name = "page" , defaultValue = "0") int page,
                                                               @RequestParam(name = "size" , defaultValue = "5")int size){
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAccountHistory(id , page , size));
    }

    @PostMapping("/OperationDebit")
    public  ResponseEntity OperationDebit(@RequestBody DebitDTO debitDTO) throws EntityNotFoundException , BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountID() , debitDTO.getAmount() , debitDTO.getDescription() );
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/OperationCredit")
    public  ResponseEntity OperationCredit(@RequestBody DebitDTO debitDTO) throws EntityNotFoundException {
        bankAccountService.credit(debitDTO.getAccountID() , debitDTO.getAmount() , debitDTO.getDescription() );
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/OperationTransfer")
    public  ResponseEntity<?> OperationTransfer(@RequestBody TransferDTO transferDTO) throws BalanceNotSufficientException {
        bankAccountService.transfer(transferDTO.getAccountID() , transferDTO.getDestination() , transferDTO.getAmount());
        return  new ResponseEntity(HttpStatus.CREATED);
    }

}
