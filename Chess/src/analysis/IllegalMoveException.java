package analysis;

/**
 * The analysis.IllegalMoveException class is thrown when an illegal move is checked for validity
 * @author lalbr
 *
 */
public class IllegalMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalMoveException() {
		super();
	}
	
	public IllegalMoveException(String msg) {
		super(msg);
	}
}
