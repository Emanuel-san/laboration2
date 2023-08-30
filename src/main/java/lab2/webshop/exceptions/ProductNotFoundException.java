package lab2.webshop.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productId){
        super("No product found with ID: " + productId);
    }
}
