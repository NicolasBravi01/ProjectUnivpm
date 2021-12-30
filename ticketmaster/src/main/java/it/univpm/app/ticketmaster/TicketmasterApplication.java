package it.univpm.app.ticketmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.app.ticketmaster.apiConnection.ticketmasterConnection;
import it.univpm.app.ticketmaster.gui.Home;

@SpringBootApplication
public class TicketmasterApplication 
{

	public static void main(String[] args) 
	{		
		SpringApplication.run(TicketmasterApplication.class, args);
		ticketmasterConnection.getJSONEvents();
		//new Home();
	}

}
