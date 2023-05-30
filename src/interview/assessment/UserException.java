package interview.assessment;

public class UserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ErrorMessage;

	public UserException(String errorMessage) {
		super();
		this.ErrorMessage = errorMessage;
	}

       
	
}
