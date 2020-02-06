/**
 * @author Lincoln  Maene
 * Exception class for the abscence of uppercase characters
 *  Returns a message
 */
public class NoUpperAlphaException extends Exception {
	
	public NoUpperAlphaException (String message)
	{
		super(message);
	}

}
