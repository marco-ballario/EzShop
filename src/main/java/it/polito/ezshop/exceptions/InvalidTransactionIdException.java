package it.polito.ezshop.exceptions;

public class InvalidTransactionIdException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7924036321291808033L;
	public InvalidTransactionIdException() { super(); }
    public InvalidTransactionIdException(String msg) { super(msg); }
}
