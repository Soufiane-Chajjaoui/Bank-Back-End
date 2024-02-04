package ma.bank.end.BankBackEnd.web;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
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
        }catch (EntityExistedException e){
            ApiError apiError = new ApiError(HttpStatus.NO_CONTENT , e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO customerDTO){

        return new ResponseEntity<>(bankAccountService.saveCustomer(customerDTO) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(
            @PathVariable Long id ,
            @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        System.out.println(id);
        bankAccountService.deleteCustomer(id);
    }
}
