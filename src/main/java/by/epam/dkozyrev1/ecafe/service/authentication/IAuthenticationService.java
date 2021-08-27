package by.epam.dkozyrev1.ecafe.service.authentication;

import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.UserAuthenticationServiceException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

public interface IAuthenticationService {

    @CheckedArguments
    @ExceptionableBeingLogged
    User signIn(String username, String password) throws UserAuthenticationServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged
    User signUp(String username, String password, String email, String phone) throws UserAuthenticationServiceException;


}