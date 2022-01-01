package it.univpm.app.ticketmaster.exception;

import java.util.Vector;

public class ErrorHandling
{
	Vector<Exception> errors = new Vector<Exception>();
	
	public boolean addError(Exception err)
	{
		boolean exist = false;
		
		if(errors.contains(err))
			exist = true;
		else
			errors.add(err);
		
		return exist;		
	}
	
}
