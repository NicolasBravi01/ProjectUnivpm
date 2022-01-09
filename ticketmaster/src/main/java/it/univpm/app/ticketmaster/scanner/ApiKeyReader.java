package it.univpm.app.ticketmaster.scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import it.univpm.app.ticketmaster.exception.ApiConnectionException;

public class ApiKeyReader 
{
	
	private final static String pathApiKey = "src/main/resources/apiKey.txt";

	
	public static String readApiKey() throws ApiConnectionException
	{
		String msg="";
		try 
		{
			Scanner reader = new Scanner(new BufferedReader(new FileReader(pathApiKey)));			
			msg = reader.nextLine();
			
			if(msg.isEmpty())
				throw new ApiConnectionException("ApiKey not found");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("ApiKey not found");
			throw new ApiConnectionException("ApiKey not found");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			throw new ApiConnectionException("Found error searching ApiKey");
		}
				
		return msg;
	}
	
	
	

}
