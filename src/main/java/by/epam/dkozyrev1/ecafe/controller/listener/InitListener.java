package by.epam.dkozyrev1.ecafe.controller.listener;

import by.epam.dkozyrev1.ecafe.config.DependenciesLoader;
import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener("Application initialization listener")
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaticDataHandler.INSTANCE.getLOGGER().info(String.format("Loaded dependencies %s", DependenciesLoader.getInstance()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        StaticDataHandler.INSTANCE.getLOGGER().info(String.format("%s context destroyed", this));
        ConnectionPool.getInstance().shutdown();
    }
}