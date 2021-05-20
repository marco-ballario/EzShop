package it.polito.ezshop.exceptions;

public class InvalidCustomerCardException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3467873915427714809L;
	public InvalidCustomerCardException() { super(); }
    public InvalidCustomerCardException(String msg) { super(msg); }
}
