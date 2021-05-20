package it.polito.ezshop.exceptions;

public class InvalidPricePerUnitException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1475859457829354774L;
	public InvalidPricePerUnitException() { super(); }
    public InvalidPricePerUnitException(String msg) { super(msg); }
}
