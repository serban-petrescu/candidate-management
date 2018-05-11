package ro.msg.cm.exception;

public class PatchCandidateInvalidValueException extends RuntimeException {

    public PatchCandidateInvalidValueException(){}

    public PatchCandidateInvalidValueException(String message) {
        super(message);
    }
}
