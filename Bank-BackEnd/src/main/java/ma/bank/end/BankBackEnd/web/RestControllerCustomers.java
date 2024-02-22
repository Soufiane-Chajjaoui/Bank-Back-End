package ma.bank.end.BankBackEnd.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.bank.end.BankBackEnd.Errors.ApiError;
import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
import ma.bank.end.BankBackEnd.entities.BankAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.exceptions.CustomerNotFoundException;
import ma.bank.end.BankBackEnd.exceptions.EntityExistedException;
import ma.bank.end.BankBackEnd.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ApiV1/customers")
@AllArgsConstructor
public class RestControllerCustomers {

    private BankAccountService bankAccountService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> getCustomers(){

        return bankAccountService.lisCustomers();
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> getCustomerByID(@RequestParam Long id)  {

            return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getCustomer(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){

         return new ResponseEntity<>(bankAccountService.saveCustomer(customerDTO) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO updateCustomer(
            @PathVariable Long id ,
            @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable Long id){
        System.out.println(id);
        bankAccountService.deleteCustomer(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> searchCustomers(@RequestParam(defaultValue = "") String keyword){
        return bankAccountService.getCustomerByName(keyword) ;
    }
}
