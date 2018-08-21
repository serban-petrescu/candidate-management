package ro.msg.cm.validator;

import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Stream;

public class CustomNotNullValidator implements ConstraintValidator<OneNotNull, Object> {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;

    @Override
    public void initialize(OneNotNull customNotNull) {
        fields = customNotNull.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        long notNull = Stream.of(fields)
                .map(field -> PARSER.parseExpression(field).getValue(o))
                .filter(Objects::nonNull)
                .count();
        return notNull == 1 || notNull == fields.length;
    }
}
