package it.polito.ezshop.exceptions;

public class InvalidUsernameException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2149180368729509913L;
	public InvalidUsernameException() { super(); }
    public InvalidUsernameException(String msg) { super(msg); }
}
