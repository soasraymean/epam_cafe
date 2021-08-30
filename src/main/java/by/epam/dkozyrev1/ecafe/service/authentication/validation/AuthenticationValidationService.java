package by.epam.dkozyrev1.ecafe.service.authentication.validation;

import by.epam.dkozyrev1.ecafe.config.AuthenticationServiceConfiguration;

public class AuthenticationValidationService {

    public static AuthenticationValidationService getInstance(){
        return AuthenticationValidationServiceInstanceHandler.AUTHENTICATION_VALIDATION_SERVICE_INSTANCE;
    }

    public boolean signInDataIsValid(String username, String password) {
        return username.matches(AuthenticationServiceConfiguration.INSTANCE.getUSERNAME_PATTERN()) &&
                password.matches(AuthenticationServiceConfiguration.INSTANCE.getPASSWORD_PATTERN());
    }

    public boolean signUpDataIsValid(String username, String password, String email, String phone) {
        return username.matches(AuthenticationServiceConfiguration.INSTANCE.getUSERNAME_PATTERN()) &&
                password.matches(AuthenticationServiceConfiguration.INSTANCE.getPASSWORD_PATTERN()) &&
                email.matches(AuthenticationServiceConfiguration.INSTANCE.getEMAIL_PATTERN()) &&
                phone.matches(AuthenticationServiceConfiguration.INSTANCE.getPHONE_PATTERN());
    }

    private static class AuthenticationValidationServiceInstanceHandler{

        private static final AuthenticationValidationService AUTHENTICATION_VALIDATION_SERVICE_INSTANCE = new AuthenticationValidationService();

    }

}