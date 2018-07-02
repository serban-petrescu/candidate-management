package ro.msg.cm.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that a class must have one of the given fields not null
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CustomNotNullValidator.class)
public @interface OneNotNull {

    /**
     * List all the fields that at least one of them must not be null
     */
    String[] value();

    String message() default "At least of this {value} must not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
