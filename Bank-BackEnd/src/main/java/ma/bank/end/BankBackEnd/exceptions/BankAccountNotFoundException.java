package ma.bank.end.BankBackEnd.exceptions;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(String messageExcepttion) {
        super(messageExcepttion);
    }
}
