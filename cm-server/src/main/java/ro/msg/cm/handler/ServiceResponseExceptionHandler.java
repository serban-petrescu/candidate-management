package ro.msg.cm.handler;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.msg.cm.exception.CandidateIsAlreadyValidatedException;
import ro.msg.cm.exception.CandidateNotFoundException;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ServiceResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private final static HttpHeaders HEADERS;
    private final static String MESSAGE = "message";

    static {
        HEADERS = new HttpHeaders();
        HEADERS.setContentType(MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(value = CandidateNotFoundException.class)
    protected ResponseEntity<Object> handleCandidateNotFoundException(RuntimeException ex, WebRequest request) {
        Map<String, String> output = new HashMap<>();

        if (StringUtils.isEmpty(ex.getMessage())) {
            output.put(MESSAGE, "Candidate for the given id was not found");
        } else {
            output.put(MESSAGE, ex.getMessage());
        }

        return handleExceptionInternal(ex, new Gson().toJson(output), HEADERS, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = CandidateIsAlreadyValidatedException.class)
    protected ResponseEntity<Object> handleCandidateIsAlreadyValidatedException(RuntimeException ex, WebRequest request) {
        Map<String, String> output = new HashMap<>();
        output.put(MESSAGE, "Invalidated candidate for the given id doesn't exist");

        return handleExceptionInternal(ex, new Gson().toJson(output), HEADERS, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { PatchCandidateInvalidValueException.class, ConstraintViolationException.class })
    protected ResponseEntity<Object> handlePatchCandidateInvalidValueException(RuntimeException ex, WebRequest request) {
        Map<String, String> output = new HashMap<>();
        output.put(MESSAGE, "Sent candidate email or phone is not valid");

        return handleExceptionInternal(ex, new Gson().toJson(output), HEADERS, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = CannotCreateTransactionException.class)
    protected ResponseEntity<Object> handleCannotCreateTransactionException(RuntimeException ex, WebRequest request) {
        Map<String, String> output = new HashMap<>();
        output.put(MESSAGE, "Database is offline or unreachable");

        return handleExceptionInternal(ex, new Gson().toJson(output), HEADERS, HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(RuntimeException ex, WebRequest request) {
        Map<String, String> output = new HashMap<>();
        output.put(MESSAGE, "Entity could not be found");

        return handleExceptionInternal(ex, new Gson().toJson(output), HEADERS, HttpStatus.NOT_FOUND, request);
    }
}
