package by.epam.dkozyrev1.ecafe.service.authentication.hash;

import by.epam.dkozyrev1.ecafe.config.AuthenticationServiceConfiguration;

import java.util.Objects;

public final class PasswordHashService {

    private PasswordHashService(){}

    public static int hash(String value){
        int hashedValue = Objects.hash(value + AuthenticationServiceConfiguration.INSTANCE.getGlobalSalt());
        for(int i = 0; i< AuthenticationServiceConfiguration.INSTANCE.getHashIterations(); i++){
            hashedValue = Objects.hash(hashedValue, value + AuthenticationServiceConfiguration.INSTANCE.getGlobalSalt());
        }
        return hashedValue;
    }

}