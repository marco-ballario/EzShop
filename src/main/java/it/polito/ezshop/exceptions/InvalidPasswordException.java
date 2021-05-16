package it.polito.ezshop.exceptions;

public class InvalidPasswordException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1245275541034665931L;
	public InvalidPasswordException() { super(); }
    public InvalidPasswordException(String msg) { super(msg); }
}
