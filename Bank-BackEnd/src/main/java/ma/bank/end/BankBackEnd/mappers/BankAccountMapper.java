package ma.bank.end.BankBackEnd.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import ma.bank.end.BankBackEnd.dtos.CurrentAccountDTO;
import ma.bank.end.BankBackEnd.dtos.CustomerDTO;
import ma.bank.end.BankBackEnd.dtos.SavingAccountDTO;
import ma.bank.end.BankBackEnd.entities.CurrentAccount;
import ma.bank.end.BankBackEnd.entities.Customer;
import ma.bank.end.BankBackEnd.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {

    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer , customerDTO);

        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO , customer);
        return customer;
    }
    public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount , savingAccountDTO);
        savingAccountDTO.setCustomer(fromCustomer(savingAccount.getCustomer()));
        return savingAccountDTO;
    }
    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO , savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingAccountDTO.getCustomer()));
        return savingAccount;
    }

    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount , currentAccountDTO);
        currentAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));
        return currentAccountDTO;
    }
    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO){
        CurrentAccount curreAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO , curreAccount);
        curreAccount.setCustomer(fromCustomerDTO(currentAccountDTO.getCustomer()));
        return curreAccount;
    }
}
