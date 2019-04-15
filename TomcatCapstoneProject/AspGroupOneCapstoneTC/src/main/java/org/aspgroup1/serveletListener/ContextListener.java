package org.aspgroup1.serveletListener;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.aspgroup1.HibernateUtilities.HibernateUtil;

@WebListener
public class ContextListener implements ServletContextListener {
  
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
        HibernateUtil.closeSessionFactory();
    }

}
