package ro.msg.cm.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidation implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail validEmail) {
        // no need of initialization
    }

    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(!hasRequiredCharactersAndNoWhitespace(email)) {
            return false;
        }

        if(hasIllegalCharacters(email)) {
            return false;
        }

        if(hasSequenceOfSpecialsCharacters(email)) {
            return false;
        }

        return hasValidStructure(email);
    }

    private boolean hasRequiredCharactersAndNoWhitespace(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }

        if (StringUtils.containsWhitespace(email)) {
            return false;
        }

        if(email.charAt(0) == '.' || email.charAt(email.length() - 1) == '.') {
            return false;
        }

        return email.contains("@");
    }

    private boolean hasIllegalCharacters(String email) {
        for(char illegalChar : "(),:;<>[\\]\"".toCharArray()) {
            if(email.contains(illegalChar + "")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSequenceOfSpecialsCharacters(String email) {
        for(char specialChar : "!#$%&'*+-/=?^_`{|}~.".toCharArray()) {
            if(email.contains(specialChar + "" + specialChar)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasValidStructure(String email) {
        String beforeAtSymbol = email.split("@")[0];
        if (beforeAtSymbol.length() < 2) {
            return false;
        }

        String afterAtSymbol = email.split("@")[1];
        if (!afterAtSymbol.contains(".")) {
            return false;
        }

        String betweenAtAndDot = afterAtSymbol.substring(0, afterAtSymbol.lastIndexOf('.'));
        if (betweenAtAndDot.length() < 2) {
            return false;
        }

        String afterDot = afterAtSymbol.substring(afterAtSymbol.lastIndexOf('.') + 1, afterAtSymbol.length());
        return afterDot.length() >= 2;
    }
}
