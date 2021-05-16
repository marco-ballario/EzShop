package it.polito.ezshop.exceptions;

public class InvalidProductIdException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1453874303007273828L;
	public InvalidProductIdException() { super(); }
    public InvalidProductIdException(String msg) { super(msg); }
}
