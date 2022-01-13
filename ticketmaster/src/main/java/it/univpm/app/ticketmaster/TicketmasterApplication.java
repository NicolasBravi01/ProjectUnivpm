package it.univpm.app.ticketmaster;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import it.univpm.app.ticketmaster.apiConnection.TicketmasterConnection;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.view.Home;


@SpringBootApplication
public class TicketmasterApplication 
{
	public static void main(String[] args) 
	{				
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TicketmasterApplication.class);
		builder.headless(false);
		builder.run(args);
		
		loadEvents();
		new Home();
	}
	
	/**
	 * Metodo che effettua la chiamata all'Api di ticketmaster per ricevere la lista degli eventi
	 */
	static void loadEvents()
	{
		EventsFilter.setEvents(TicketmasterConnection.callEvents());
	}

}
