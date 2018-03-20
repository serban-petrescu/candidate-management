package ro.msg.cm.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.msg.cm.model.Candidate;

import javax.validation.Validation;
import javax.validation.Validator;

public class EmailValidationTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private Candidate candidate = new Candidate("john", "doe", "0987654321", null);

    @Before
    public void setup() {
        candidate.setEmail(null);
    }

    @Test
    public void checkValidEmailBasic() {
        candidate.setEmail("prettyandsimple@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmailWithDotInName() {
        candidate.setEmail("very.common@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmailWithMultipleDotsInName() {
        candidate.setEmail("disposable.style.email.with+symbol@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmailWithDotAndDashesInName() {
        candidate.setEmail("other.email-with-dash@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmailWithShortName() {
        candidate.setEmail("xx@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmailWithDashesInDomainAndName() {
        candidate.setEmail("example-indeed@strange-example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmailWithSpecialSymbolsInName() {
        candidate.setEmail("#!$%&'*+-/=?^_`{}|~@example.org");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithNoAtSymbol() {
        candidate.setEmail("Abc.example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithMultipleAtSymbols() {
        candidate.setEmail("A@b@c@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithIllegalSymbols() {
        candidate.setEmail("a\"b(c)d,e:f;gi[j\\k]l@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithQuotesInName() {
        candidate.setEmail("just\"not\"right@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithSpaceInName() {
        candidate.setEmail("this is\"not\\allowed@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithDotSequenceInName() {
        candidate.setEmail("john..doe@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailWithDotSequenceInDomain() {
        candidate.setEmail("john.doe@example..com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailStartingWithSpaces() {
        candidate.setEmail("    john.doe@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailEndingWithSpaces() {
        candidate.setEmail("john.doe@example.com    ");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailStartingWithDot() {
        candidate.setEmail(".john.doe@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmailEndingWithDot() {
        candidate.setEmail("john.doe@example.com.");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    private boolean hasErrors(Candidate candidate) {
        return !validator.validate(candidate).isEmpty() && validator.validate(candidate).iterator().next().getConstraintDescriptor().getAnnotation().annotationType().getName().equalsIgnoreCase(ValidEmail.class.getName());
    }
}
