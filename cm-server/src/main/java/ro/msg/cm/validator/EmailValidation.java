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
        if (StringUtils.isEmpty(email)) {
            return false;
        }

        if (StringUtils.containsWhitespace(email)) {
            return false;
        }

        if(email.charAt(0) == '.' || email.charAt(email.length() - 1) == '.') {
            return false;
        }

        if(hasIllegalCharacters(email)) {
            return false;
        }

        if (!email.contains("@")) {
            return false;
        }

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

    private boolean hasIllegalCharacters(String email) {
        for(char illegalChar : "(),:;<>[\\]".toCharArray()) {
            if(email.contains(illegalChar + "")) {
                return true;
            }
        }
        return false;
    }
}
