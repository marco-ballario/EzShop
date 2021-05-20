package it.polito.ezshop.exceptions;

public class UnauthorizedException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5042697762447859516L;
	public UnauthorizedException() { super(); }
    public UnauthorizedException(String msg) { super(msg); }
}
