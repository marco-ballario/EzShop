package it.polito.ezshop.exceptions;

public class InvalidOrderIdException extends Exception {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6410346347865414825L;
	public InvalidOrderIdException() { super(); }
    public InvalidOrderIdException(String msg) { super(msg); }
}
