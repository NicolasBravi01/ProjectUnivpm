package it.univpm.app.ticketmaster.exception;

import java.util.Vector;

public class ErrorHandling
{
	static Vector<Exception> errors = new Vector<Exception>();
	
		
	public static void addError(Exception e)
	{
		if(!errors.contains(e))
			errors.add(e);
	}
	
	
	public static boolean removeError(Exception e)
	{
		boolean exist = true;
		
		if(errors.contains(e))
			errors.remove(e);
		else
			exist = false;
		
		return exist;
	}
	
}
