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
    public void checkValidEmail1() {
        candidate.setEmail("prettyandsimple@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmail2() {
        candidate.setEmail("very.common@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmail3() {
        candidate.setEmail("disposable.style.email.with+symbol@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmail4() {
        candidate.setEmail("other.email-with-dash@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmail5() {
        candidate.setEmail("xx@example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmail6() {
        candidate.setEmail("example-indeed@strange-example.com");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkValidEmail7() {
        candidate.setEmail("#!$%&'*+-/=?^_`{}|~@example.org");
        Assert.assertEquals(false, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail1() {
        candidate.setEmail("Abc.example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail2() {
        candidate.setEmail("A@b@c@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail3() {
        candidate.setEmail("a\"b(c)d,e:f;gi[j\\k]l@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail4() {
        candidate.setEmail("just\"not\"right@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail5() {
        candidate.setEmail("this is\"not\\allowed@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail6() {
        candidate.setEmail("john..doe@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail7() {
        candidate.setEmail("john.doe@example..com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail8() {
        candidate.setEmail("    john.doe@example.com");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    @Test
    public void checkInvalidEmail9() {
        candidate.setEmail("john.doe@example.com    ");
        Assert.assertEquals(true, hasErrors(candidate));
    }

    private boolean hasErrors(Candidate candidate) {
        return !validator.validate(candidate).isEmpty() && validator.validate(candidate).iterator().next().getConstraintDescriptor().getAnnotation().annotationType().getName().equalsIgnoreCase(ValidEmail.class.getName());
    }
}
