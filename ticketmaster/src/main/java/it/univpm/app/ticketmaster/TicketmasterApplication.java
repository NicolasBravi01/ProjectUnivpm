package it.univpm.app.ticketmaster;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import it.univpm.app.ticketmaster.controller.Controller;
import it.univpm.app.ticketmaster.view.Home;


@SpringBootApplication
public class TicketmasterApplication 
{
	public static void main(String[] args) 
	{				
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TicketmasterApplication.class);
		builder.headless(false);
		builder.run(args);
		
		Controller controller = new Controller();
		new Home(controller.getTicketmasterService());
	}
	
}
