package it.polito.ezshop.exceptions;

public class InvalidCustomerIdException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3657540632271385395L;
	public InvalidCustomerIdException() { super(); }
    public InvalidCustomerIdException(String msg) { super(msg); }
}
