package gamefoundation;

import analysis.IllegalMoveException;
import analysis.MoveValidation;

/**
 * The foundation.Move class represents a foundation.Move played in the game.
 * It encapsulates information about the piece being moved, the current and targeted foundation.Position and which Color is currently moving.
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
	public Position checkedFrom;

	public Move(Piece piece, Position currentPosition, Position targetPosition, PlayerColor color) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;
		this.color = color;

		MoveValidation m = new MoveValidation();
        try {

			if(m.isValid(this)){
				Board.getBoardInstance().updateBoard(this, Board.getBoardInstance().getBoard());
				Board.getBoardInstance().playedMoves.add(this);
				Piece king = null;


				if(this.getColor() == PlayerColor.WHITE) king = Piece.blackKing;
				if(this.getColor() == PlayerColor.BLACK) king = Piece.whiteKing;

				if(m.isCheckMated(king,Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard())) == true){
					System.out.println("CHECKMATE! " + king + " is checkmated! game over");
					throw new RuntimeException();
				}
				PlayerColor nextMoveColor = null;
				if(this.getColor() == PlayerColor.WHITE){
					nextMoveColor = PlayerColor.BLACK;
				}
				else if(this.getColor() == PlayerColor.BLACK){
					nextMoveColor = PlayerColor.WHITE;
				}

				if(m.isStaleMated(nextMoveColor,Board.getBoardInstance().getBoard())){
					System.out.println("Stalemate! " + nextMoveColor + " king is stalemated! Draw!");
				}
				else{
					System.out.println("Kein Stalemate ;)");
				}
			}

        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
    }
	// constructor to prevent validity checking for this move if it is only to be simulated for check searching. also prevents unwanted add to playedmove list
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


	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("Move: " + this.piece + " from " + this.getCurrentPosition() + " to " + this.getTargetPosition());

		return sb.toString();
	}
	
}
