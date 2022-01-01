package it.univpm.app.ticketmaster.scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import it.univpm.app.ticketmaster.exception.NullApiKeyException;

public class ApiKeyReader 
{
	//private static String apiKey = "";
	private final static String pathApiKey = "src/main/resources/apiKey.txt";

	/*public static String getApiKey() 
	{
		if(apiKey.isEmpty())
			apiKey = readApiKey();;
		return apiKey;
	}*/

	
	public static String readApiKey() throws NullApiKeyException
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
		finally
		{
			if(msg.isEmpty())
				throw new NullApiKeyException("ApiKey not found");
		}
				
		return msg;
	}
	
	
	

}
