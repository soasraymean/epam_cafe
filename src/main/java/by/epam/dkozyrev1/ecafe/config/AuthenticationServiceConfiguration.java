package by.epam.dkozyrev1.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class AuthenticationServiceConfiguration {
    private static AuthenticationServiceConfiguration INSTANCE;

    private final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$";
    private final String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private final String PHONE_PATTERN = "[0-9]{11}";
    private String globalSalt;
    private int hashIterations;

    public static AuthenticationServiceConfiguration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthenticationServiceConfiguration();
        }
        return INSTANCE;
    }

    private AuthenticationServiceConfiguration() {
        loadProperties();
    }

    private void loadProperties(){
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("authenticationProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            globalSalt=Objects.nonNull(properties.getProperty("globalSalt"))?
                    properties.getProperty("globalSalt"):"defaultSalt";
            hashIterations=Objects.nonNull(properties.getProperty("hashIterations"))?
                    Integer.parseInt(properties.getProperty("hashIterations")):1;
        }catch (IOException ignored){
        }
    }

    public String getUSERNAME_PATTERN() {
        return USERNAME_PATTERN;
    }

    public String getPASSWORD_PATTERN() {
        return PASSWORD_PATTERN;
    }

    public String getEMAIL_PATTERN() {
        return EMAIL_PATTERN;
    }

    public String getPHONE_PATTERN() {
        return PHONE_PATTERN;
    }

    public String getGlobalSalt() {
        return globalSalt;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    @Override
    public String toString() {
        return "AuthenticationServiceConfiguration{" +
                "USERNAME_PATTERN='" + USERNAME_PATTERN + '\'' +
                ", PASSWORD_PATTERN='" + PASSWORD_PATTERN + '\'' +
                ", EMAIL_PATTERN='" + EMAIL_PATTERN + '\'' +
                ", PHONE_PATTERN='" + PHONE_PATTERN + '\'' +
                ", globalSalt='" + globalSalt + '\'' +
                ", hashIterations=" + hashIterations +
                '}';
    }
}
