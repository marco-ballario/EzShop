package it.polito.ezshop.exceptions;

public class InvalidQuantityException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3067992322206974288L;
	public InvalidQuantityException() { super(); }
    public InvalidQuantityException(String msg) { super(msg); }
}
