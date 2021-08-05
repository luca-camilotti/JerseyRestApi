package luke.jersey.rest.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import luke.jersey.rest.database.dbHelper;

/* Equivalent for init() and destroy() methods in servlet lifecycle*/

@WebListener
public class ApplicationStartUpListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("---- Maven Jersey project: initialize servlet context -----");
        // add initialization code here
        try {
			dbHelper.init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver not found!");
			e.printStackTrace();
		}
        //dbHelper.connect();
        //dbHelper.query(dbHelper.queryAllDipendente);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("---- destroying servlet context -----");
        // clean up resources
        //dbHelper.close();
    }
}