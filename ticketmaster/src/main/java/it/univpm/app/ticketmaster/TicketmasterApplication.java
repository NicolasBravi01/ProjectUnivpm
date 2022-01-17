package it.univpm.app.ticketmaster;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import it.univpm.app.ticketmaster.controller.Controller;
import it.univpm.app.ticketmaster.view.Home;

/**
 * Classe principale del progetto
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
@SpringBootApplication
public class TicketmasterApplication 
{
	/**
	 * Metodo main che avvia l'applicazione Springboot e l'interfaccia grafica
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{				
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TicketmasterApplication.class);
		builder.headless(false);
		builder.run(args);
		
		Controller controller = new Controller();
		new Home(controller.getTicketmasterService());
	}
	
}
