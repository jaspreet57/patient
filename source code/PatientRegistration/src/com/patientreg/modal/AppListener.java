package com.patientreg.modal;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppListener
 *
 */
@WebListener
public class AppListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AppListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	public void contextInitialized(ServletContextEvent event)  { 
         ServletContext ctx = event.getServletContext();
         try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:"+ ctx.getRealPath("/secure/database/database.db");
			String imagesPath = ctx.getRealPath("/images");
			
			ctx.setAttribute("imagesPath", imagesPath);
			ctx.setAttribute("dbURL", dbURL);
			
			
			System.out.println("Database found");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
}
