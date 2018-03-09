package ro.msg.cm.dto;

import lombok.Data;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.validator.EmailValidate;
import ro.msg.cm.validator.EmailValidateImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class CandidateDto {

    private Map<String,Object> changedAttributes = new HashMap<>();
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String educationStatus;
    private int originalStudyYear;
    private String event;
    private LocalDate dateOfAdding;
    private CandidateCheck checkCandidate;
    private int currentStudyYear;

    public void setChangedAttributes(Map<String, Object> changedAttributes) {
        this.changedAttributes = changedAttributes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        changedAttributes.put("firstName", firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        changedAttributes.put("lastName", lastName);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        changedAttributes.put("phone", phone);
    }

    public void setEmail(String email) {
        EmailValidate emailValidate = new EmailValidateImpl();

        if (!emailValidate.isValid(email)) {
            throw new PatchCandidateInvalidValueException();
        }

        this.email = email;
        changedAttributes.put("email", email);
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
        changedAttributes.put("educationStatus", educationStatus);
    }

    public void setOriginalStudyYear(int originalStudyYear) {
        this.originalStudyYear = originalStudyYear;
        changedAttributes.put("originalStudyYear", originalStudyYear);
    }

    public void setEvent(String event) {
        this.event = event;
        changedAttributes.put("event", event);
    }

    public void setDateOfAdding(LocalDate dateOfAdding) {
        this.dateOfAdding = dateOfAdding;
        changedAttributes.put("dateOfAdding", dateOfAdding);
    }

    public void setCheckCandidate(CandidateCheck checkCandidate) {
        this.checkCandidate = checkCandidate;
        changedAttributes.put("checkCandidate", checkCandidate);
    }

    public void setCurrentStudyYear(int currentStudyYear) {
        this.currentStudyYear = currentStudyYear;
        changedAttributes.put("currentStudyYear", currentStudyYear);
    }

}
