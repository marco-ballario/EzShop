package it.polito.ezshop.exceptions;

public class InvalidRoleException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7230230626212382947L;
	public InvalidRoleException() { super(); }
    public InvalidRoleException(String msg) { super(msg); }
}
