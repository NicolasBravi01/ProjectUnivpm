package it.univpm.app.ticketmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import it.univpm.app.ticketmaster.apiConnection.TicketmasterConnection;
import it.univpm.app.ticketmaster.view.Home;

@SpringBootApplication
public class TicketmasterApplication 
{

	public static void main(String[] args) 
	{				
		//SpringApplication.run(TicketmasterApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TicketmasterApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		
		callEvents();
		new Home();
	}
	
	

	
	/*
	 * Metodo che effettua la chiamata all'api di ticketmaster per ricevere la lista degli eventi
	 */
	static void callEvents()
	{
		TicketmasterConnection.callEvents();
	}

}
