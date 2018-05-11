package ro.msg.cm.exception;

public class CandidateIsAlreadyValidatedException extends RuntimeException {

    public CandidateIsAlreadyValidatedException() {
        super();
    }

    public CandidateIsAlreadyValidatedException(String message) {
        super(message);
    }
}
