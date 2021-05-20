package it.polito.ezshop.exceptions;

public class InvalidCustomerNameException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5218781124801650117L;
	public InvalidCustomerNameException() { super(); }
    public InvalidCustomerNameException(String msg) { super(msg); }
}
