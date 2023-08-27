package lab2.webshop.exception;

import com.mongodb.MongoWriteException;
import lab2.webshop.openapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class WebshopExceptionHandler {

    private static final String NOT_FOUND = "not found";
    private static final String DUPLICATE_ID = "duplicate id";
    private static final String MODEL_VALIDATION_FAILED = "invalid arguments";
    private static final String PARSE_ERROR = "parse error";

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException e){
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(NOT_FOUND);
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<ErrorResponse> handleMongoWriteError(MongoWriteException e){
        final ErrorResponse errorResponse = new ErrorResponse();
        if (e.getCode() == 11000) {
            errorResponse.setError(DUPLICATE_ID);
            errorResponse.setMessage(e.getError().getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        final ErrorResponse errorResponse = new ErrorResponse();
        StringBuilder sb = new StringBuilder();
        for (Object msg : Objects.requireNonNull(e.getDetailMessageArguments())) {
            sb.append(msg.toString());
            sb.append(" ");
        }
        errorResponse.setError(MODEL_VALIDATION_FAILED);
        errorResponse.setMessage(sb.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e){
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(PARSE_ERROR);
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
