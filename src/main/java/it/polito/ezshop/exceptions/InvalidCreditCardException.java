package it.polito.ezshop.exceptions;

public class InvalidCreditCardException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4297338548597482742L;
	public InvalidCreditCardException() { super(); }
    public InvalidCreditCardException(String msg) { super(msg); }
}
