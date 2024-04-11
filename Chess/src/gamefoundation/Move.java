package gamefoundation;

import analysis.IllegalMoveException;
import analysis.MoveValidation;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

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
				Board.getBoardInstance().reachedPositions.add(Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard()));

				if(m.pawnPromotes(this)){
					Piece p = getPromotionPiece();
					System.out.println("input piece to which the pawn is to be promoted");
					if(this.getColor() != p.getPieceColor()){
						throw new RuntimeException("promoted to wrong color");
					}
					else if(p == Piece.whiteKing ||p == Piece.blackKnight ||p == Piece.whitePawn || p == Piece.blackPawn){
						throw new RuntimeException("promoted to illegal piece");
					}
					else{
						Board.updateBoard(new Move(p,this.targetPosition,this.targetPosition,false),Board.getBoardInstance().getBoard());
					}
				}
				if(!m.sufficientMaterialOnBoard(Board.getBoardInstance().getBoard())){
					System.out.println("draw due to insufficient material");
					throw new RuntimeException("insufficient material on the board");
				}

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
				if(Board.getBoardInstance().threeTimeRepetition()){
					System.out.println("Draw! Position has been reached three times");
					throw new RuntimeException("Three Time Repetition");
				}
			}
        } catch (IllegalMoveException e) {
			System.out.println(e.getMessage());
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


	private Piece getPromotionPiece() {
		CompletableFuture<Piece> future = new CompletableFuture<>();

		new Thread(() -> {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			sc.close();
			Piece piece = Piece.valueOf(input);
			future.complete(piece);
		}).start();

		try {
			return future.get(); // Wait for user input
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Move){
			Move m = (Move)obj;
			if(this.getPiece() == m.getPiece() && this.getCurrentPosition() == m.getCurrentPosition() && this.getTargetPosition() == m.getTargetPosition()){
				return true;
			}
		}
		return false;
	}
}
