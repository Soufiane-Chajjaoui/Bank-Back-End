package ma.bank.end.BankBackEnd.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String messageExcepttion) {
        super(messageExcepttion);
    }
}
