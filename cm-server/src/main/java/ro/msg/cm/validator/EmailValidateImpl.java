package ro.msg.cm.validator;

import org.springframework.util.StringUtils;

public class EmailValidateImpl implements EmailValidate {

    public boolean isValid(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }

        if (StringUtils.containsWhitespace(email)) {
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
}
