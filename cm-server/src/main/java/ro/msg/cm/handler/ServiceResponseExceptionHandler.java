package ro.msg.cm.handler;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.msg.cm.exception.CandidateIsAlreadyValidatedException;
import ro.msg.cm.exception.CandidateNotFoundException;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.pojo.ErrorMessage;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ServiceResponseExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CandidateNotFoundException.class)
    public ErrorMessage handleCandidateNotFoundException(RuntimeException ex) {
        return new ErrorMessage(StringUtils.isEmpty(ex.getMessage()) ? "Candidate for the given id was not found" : ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CandidateIsAlreadyValidatedException.class)
    protected ErrorMessage handleCandidateIsAlreadyValidatedException(RuntimeException ex) {
        return new ErrorMessage(StringUtils.isEmpty(ex.getMessage()) ? "Candidate does not exist or is already validated" : ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ PatchCandidateInvalidValueException.class, ConstraintViolationException.class })
    protected ErrorMessage handlePatchCandidateInvalidValueException() {
        return new ErrorMessage("Sent candidate email or phone is not valid");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(CannotCreateTransactionException.class)
    protected ErrorMessage handleCannotCreateTransactionException() {
        return new ErrorMessage("Database is offline or unreachable");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    protected ErrorMessage handleEntityNotFoundException() {
        return new ErrorMessage("Entity could not be found");
    }
}
