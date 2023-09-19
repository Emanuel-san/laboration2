package lab2.webshop.exceptions;

/**
 * Thrown at som occasions when queries to DB return null.
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
