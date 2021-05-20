package it.polito.ezshop.exceptions;

public class InvalidProductCodeException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8301879563132511654L;
	public InvalidProductCodeException() { super(); }
    public InvalidProductCodeException(String msg) { super(msg); }
}
