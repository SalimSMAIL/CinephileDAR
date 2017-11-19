package Jobs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class <code>UpdateDatabaseServlet</code> implements
 * <code>ServletContextListener</code> used to update the database periodicly
 * with the newest movies
 *
 * TODO: See extensions in the report
 * 
 * 
 * <p>
 * Created on : November 12, 2017
 * </p>
 * 
 * @version $Name$ -- $Revision$ -- $Date$
 */

public class UpdateDatabaseServlet implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		System.out.println("Scheduler starts");
		scheduler = Executors.newSingleThreadScheduledExecutor();

		// Mise a jour tous les 7 jours : pour la version finale
		scheduler.scheduleAtFixedRate(new getNewFilmsFromAPITask(), 0, 7, TimeUnit.DAYS);

		// Mise a jour toutes les 2 minutes : pour la version test
		// scheduler.scheduleAtFixedRate(new getNewFilmsFromAPITask(), 0, 2,
		// TimeUnit.MINUTES);
	}

	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Scheduler shutdown");
		scheduler.shutdownNow();
	}
}
