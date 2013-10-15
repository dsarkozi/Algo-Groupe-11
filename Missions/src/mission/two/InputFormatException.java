package mission.two;

/**
 * @author David Sarkozi
 * 
 */
public class InputFormatException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435711012133337683L;

	/**
	 * 
	 */
	public InputFormatException()
	{
		super("Wrong input file format.");
	}

	/**
	 * @param message
	 */
	public InputFormatException(String message)
	{
		super(message);
	}

}
