package gamefoundation;

import analysis.IllegalMoveException;
import analysis.MoveValidation;

/**
 * The foundation.Move class represents a Move played in the game.
 * It encapsulates information about the piece being moved, the current and targeted foundation.Position and which Color is currently moving.
 * It has two constructors. One tests if the move is valid and if the enemy is in a game-ending position after the move. The other is used to simulate moves without going through the validation process and enemy position testing.
 **/
public class Move {

	private final Piece piece;
	private final Position currentPosition;
	private final Position targetPosition;
	private PlayerColor color;

	/**
	 * Default constructor that does the following checks upon creation:
	 * Test if move is valid / legal by the rules of chess
	 * Test if enemy is Checkmated after this move
	 * Test if enemy is Stalemated after this move
	 * @param piece The piece that is moved
	 * @param currentPosition The starting position of the piece
	 * @param targetPosition The targeted Position of the piece
	 * @param color The color that is making the move
	 */
	public Move(Piece piece, Position currentPosition, Position targetPosition, PlayerColor color) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;
		this.color = color;
		MoveValidation m = new MoveValidation();
        try {
			if(m.isValid(this)){
				Board.updateBoard(this, Board.getBoardInstance().getBoard());
				Board.getBoardInstance().playedMoves.add(this);
				PlayerColor enemyColor = null;
				if(this.getColor() == PlayerColor.WHITE) enemyColor = PlayerColor.BLACK;
				if(this.getColor() == PlayerColor.BLACK) enemyColor = PlayerColor.WHITE;
				boolean checkmated = m.isCheckMated(enemyColor,Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard()));
				PlayerColor nextMoveColor = null;
				if(this.getColor() == PlayerColor.WHITE)nextMoveColor = PlayerColor.BLACK;
				else if(this.getColor() == PlayerColor.BLACK)nextMoveColor = PlayerColor.WHITE;

				if(checkmated){
					System.out.println("CHECKMATE! " + enemyColor + " is checkmated! game over");
					throw new RuntimeException();
				} else if(m.isStaleMated(nextMoveColor,Board.getBoardInstance().getBoard())){
					System.out.println("Stalemate! " + nextMoveColor + " king is stalemated! Draw!");
					throw new RuntimeException("stalemate");
				}
			}
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
    }
	/**
	 * Constructor that is used to reuse functions that have Move as a parameter. Good to simulate moves that do not have to be checked for validity and added to the playedMoves list.
	 * @param piece the moved piece
	 * @param currentPosition the starting position
	 * @param targetPosition the target position
	 * @param checkValidity True or false does not matter. this always prevents validity checking
	 */
	public Move(Piece piece, Position currentPosition, Position targetPosition, boolean checkValidity) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;
	}

	public Piece getPiece() {
		return piece;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public Position getTargetPosition() {
		return targetPosition;
	}

	public PlayerColor getColor() {
		return color;
	}

	@Override
	public String toString(){
		return "Move: " + this.getPiece() + " from " + this.getCurrentPosition() + " to " + this.getTargetPosition();
	}
}