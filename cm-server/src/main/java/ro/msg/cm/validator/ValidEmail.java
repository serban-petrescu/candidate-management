package ro.msg.cm.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Checks if the String is an email address
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Constraint(validatedBy = EmailValidation.class)
public @interface ValidEmail {
    String message() default "String has to be a valid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
