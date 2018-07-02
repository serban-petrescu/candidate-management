package ro.msg.cm.exception;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException() {
        super();
    }

    public CandidateNotFoundException(String message) {
        super(message);
    }
}
