package com.nu1silva.apps.rest.h2connector;

import javax.servlet.ServletContextEvent;
import java.sql.SQLException;

public class H2Listener implements javax.servlet.ServletContextListener {

    H2Connector h2 = new H2Connector();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            h2.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
