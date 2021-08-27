package by.epam.dkozyrev1.ecafe.service.authorization;

import by.epam.dkozyrev1.ecafe.entity.Actor;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.AuthorizationServiceException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

public interface IAuthorizationService {

    @CheckedArguments(requiredArrayOfArgsLength = {2, 5})
    @ExceptionableBeingLogged
    Actor authorize(String... args) throws AuthorizationServiceException;

}