package java;

import by.epam.dkozyrev1.ecafe.service.authentication.validation.AuthenticationValidationService;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticationServiceTest {

    private static final String validUsername = "AnyUsername";
    private static final String validPassword = "S0m3Str0ngpa$$";
    private static final String validEmail = "someValidEmail@anyservice.any";
    private static final String validPhone = "80294206969";

    private static final String invalidUsername = "4ny1iv@lidUsername";
    private static final String invalidPassword = "SoMuchSimplePass";
    private static final String invalidEmail = "invalidEmail";
    private static final String invalidPhone = "invalidPhone";

    @Test
    public void AuthenticationServiceTest_Valid_Data(){
        Assert.assertTrue(AuthenticationValidationService.getInstance().signUpDataIsValid(validUsername, validPassword, validEmail, validPhone));
    }

    @Test
    public void AuthenticationServiceTest_Invalid_Data(){
        Assert.assertFalse(AuthenticationValidationService.getInstance().signUpDataIsValid(invalidUsername, invalidPassword, invalidEmail, invalidPhone));
    }

}
