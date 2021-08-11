package by.epam.dkozyrev1.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class DAOConfiguration {

    private static DAOConfiguration INSTANCE;

    public static DAOConfiguration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DAOConfiguration();
        }
        return INSTANCE;
    }

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private int poolIncreaseStep = 2;
    private int maxPoolSize = 32;
    private int initPoolCapacity = 8;

    private DAOConfiguration() {
        loadProperties();
    }

    private void loadProperties() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dbProperties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            dbUrl=properties.getProperty("dbUrl");
            dbUser=properties.getProperty("dbUser");
            dbPassword=properties.getProperty("dbPassword");
            if(Objects.nonNull(properties.getProperty("initPoolCapacity"))){
                initPoolCapacity=Integer.parseInt(properties.getProperty("initPoolCapacity"));
            }
            if(Objects.nonNull(properties.getProperty("maxPoolSize"))){
                maxPoolSize=Integer.parseInt(properties.getProperty("maxPoolSize"));
            }
            if(Objects.nonNull(properties.getProperty("poolIncreaseStep"))){
                poolIncreaseStep=Integer.parseInt(properties.getProperty("poolIncreaseStep"));
            }
        }catch (IOException exception){

        }
    }

    @Override
    public String toString() {
        return "DAOConfiguration{" +
                "dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", poolIncreaseStep=" + poolIncreaseStep +
                ", maxPoolSize=" + maxPoolSize +
                ", initPoolCapacity=" + initPoolCapacity +
                '}';
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getInitPoolCapacity() {
        return initPoolCapacity;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public int getPoolIncreaseStep() {
        return poolIncreaseStep;
    }
}
