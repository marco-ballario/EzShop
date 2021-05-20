package it.polito.ezshop.exceptions;

public class InvalidUserIdException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4175343399685864758L;
	public InvalidUserIdException() { super(); }
    public InvalidUserIdException(String msg) { super(msg); }
}
