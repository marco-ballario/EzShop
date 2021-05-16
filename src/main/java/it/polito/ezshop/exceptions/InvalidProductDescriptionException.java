package it.polito.ezshop.exceptions;

public class InvalidProductDescriptionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5898975834328091944L;
	public InvalidProductDescriptionException() { super(); }
    public InvalidProductDescriptionException(String msg) { super(msg); }
}
