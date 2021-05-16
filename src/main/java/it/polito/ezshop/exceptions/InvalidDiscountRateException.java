package it.polito.ezshop.exceptions;

public class InvalidDiscountRateException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3111447922415201415L;
	public InvalidDiscountRateException() { super(); }
    public InvalidDiscountRateException(String msg) { super(msg); }
}
