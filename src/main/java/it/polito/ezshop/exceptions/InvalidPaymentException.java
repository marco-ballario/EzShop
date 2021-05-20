package it.polito.ezshop.exceptions;

public class InvalidPaymentException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6506175209790629985L;
	public InvalidPaymentException() { super(); }
    public InvalidPaymentException(String msg) { super(msg); }
}
