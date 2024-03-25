import java.util.ArrayList;

/**
 * The Move class represents a Move played in the game.
 * It encapsulates information about the piece being moved, the current and targeted Position and which Color is currently moving.
 * It also provides the function to check if a move is valid and can be legally played in the game.
 * 
 * @author lalbr
 *
 */

public class Move {


	public Piece piece;
	public Position currentPosition;
	public Position targetPosition;
	public PlayerColor color;

	public Move(Piece piece, Position currentPosition, Position targetPosition, PlayerColor color) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;
		this.color = color;

		MoveValidation m = new MoveValidation();
        try {
            m.isValid(this);
			Board.getBoardInstance().updateBoard(this, Board.getBoardInstance().getBoard());
			Board.getBoardInstance().playedMoves.add(this);
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
    }
	// constructor to prevent validity checking for this move if it is only to be simulated for check searching. also prevents unwanted add to playedmove list
	public Move(Piece piece, Position currentPosition,Position targetPosition,boolean checkValidity) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;
		this.color = color;
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


	
}
