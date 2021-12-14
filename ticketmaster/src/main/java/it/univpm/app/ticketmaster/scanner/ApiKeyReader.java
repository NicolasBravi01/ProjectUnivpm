package it.univpm.app.ticketmaster.scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ApiKeyReader 
{
	private static String apiKey = "";
	private final static String pathApiKey = "apiKey.txt";

	public static String getApiKey() 
	{
		if(apiKey.isEmpty())
			apiKey = readApiKey();;
		return apiKey;
	}

	
	private static String readApiKey() 
	{
		String msg="";
		try 
		{
			Scanner reader = new Scanner(new BufferedReader(new FileReader(pathApiKey)));			
			msg = reader.nextLine(); 
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		if(msg.isEmpty())
			;//FILE VUOTO, GESTIRE L'ERRORE, MANCA APIKEY
		
		
		return msg;
	}
	
	
	

}
