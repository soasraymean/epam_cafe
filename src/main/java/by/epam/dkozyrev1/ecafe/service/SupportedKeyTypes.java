package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.User;

import java.lang.reflect.Type;

public enum SupportedKeyTypes {

    INTEGER,
    STRING,
    USER,
    CLIENT,
    UNSUPPORTED_KEY_TYPE;

    public static SupportedKeyTypes of(Type type){
        return type.equals(Integer.class) ? INTEGER :
                type.equals(String.class) ? STRING :
                        type.equals(User.class) ? USER :
                                type.equals(Client.class) ? CLIENT : UNSUPPORTED_KEY_TYPE;
    }

}