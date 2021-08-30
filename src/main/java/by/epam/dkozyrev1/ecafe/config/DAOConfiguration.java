package by.epam.dkozyrev1.ecafe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public enum DAOConfiguration {

    INSTANCE;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private int initPoolCapacity = 8;
    private int maxPoolSize = 32;
    private int poolIncreaseStep = 2;

    DAOConfiguration(){
        loadProperties();
    }
    private void loadProperties() {
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("dbProperties.properties")){
            Properties properties = new Properties();
            properties.load(in);
            dbUrl = properties.getProperty("dbUrl");
            dbUser = properties.getProperty("dbUser");
            dbPassword = properties.getProperty("dbPassword");
            if(Objects.nonNull(properties.getProperty("initPoolCapacity")))
                initPoolCapacity = Integer.parseInt(properties.getProperty("initPoolCapacity"));
            if(Objects.nonNull(properties.getProperty("maxPoolSize")))
                maxPoolSize = Integer.parseInt(properties.getProperty("maxPoolSize"));
            if(Objects.nonNull(properties.getProperty("poolIncreaseStep")))
                poolIncreaseStep = Integer.parseInt(properties.getProperty("poolIncreaseStep"));
        } catch (IOException ignored){ }
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", initPoolCapacity=" + initPoolCapacity +
                "} ";
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
