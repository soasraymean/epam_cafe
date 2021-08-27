package by.epam.dkozyrev1.ecafe.service.authorization.impl;

import by.epam.dkozyrev1.ecafe.entity.Actor;
import by.epam.dkozyrev1.ecafe.entity.Admin;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.service.authentication.impl.AuthenticationService;
import by.epam.dkozyrev1.ecafe.service.authorization.IAuthorizationService;
import by.epam.dkozyrev1.ecafe.service.exception.AuthorizationServiceException;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.IEntityServiceFactory;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import java.util.Optional;

public class AuthorizationService implements IAuthorizationService {

    IEntityServiceFactory entityServiceFactory;

    public static IAuthorizationService getInstance(){
        return AuthorizationServiceInstanceHandler.AUTHORIZATION_SERVICE_INSTANCE;
    }

    private AuthorizationService(IEntityServiceFactory entityServiceFactory){
        this.entityServiceFactory = entityServiceFactory;
    }

    @Override
    public Actor authorize(String... args) throws AuthorizationServiceException {
        try {
            Optional<? extends Actor> actor = args.length == 2 ? signIn(args[0], args[1]) :
                    signUp(args[0], args[1], args[2], args[3], args[4]);
            if(actor.isEmpty()){
                throw new AuthorizationServiceException("Authorization failed");
            }
            return actor.get();
        } catch (ServiceException ex) {
            throw new AuthorizationServiceException("Authorization failed", ex);
        }
    }

    private Optional<? extends Actor> signIn(String username, String password) throws ServiceException {
        final User user = AuthenticationService.getInstance()
                .signIn(username, password);
        return user.isPromoted() ?
                Optional.of(new Admin(user)) :
                entityServiceFactory.getClientService().find(user);
    }

    private Optional<? extends Actor> signUp(String username, String password, String email, String phone, String name) throws ServiceException {
        final User user = AuthenticationService.getInstance()
                .signUp(username, password, email, phone);
        return entityServiceFactory.getClientService().save(new Client(user, name));
    }

    private static class AuthorizationServiceInstanceHandler{

        private static final AuthorizationService AUTHORIZATION_SERVICE_INSTANCE = new AuthorizationService(EntityServiceFactory.getInstance());

    }

}