package gamefoundation;

import analysis.MoveValidation;
import sound.SoundManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import static gamefoundation.Board.getBoardInstance;

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
		if (m.isValid(this) && !getBoardInstance().isGameOver()) {
			Board.updateBoard(this, getBoardInstance().getBoard());
			getBoardInstance().playedMoves.add(this);
			getBoardInstance().reachedPositions.add(getBoardInstance().cloneBoard(getBoardInstance().getBoard()));

			if (m.pawnPromotes(this)) {
				Piece p = getPromotionPiece();
				System.out.println("input piece to which the pawn is to be promoted");
				if (this.getColor() != p.getPieceColor()) {
					getBoardInstance().setGameOver(true);
					System.out.println("game over, promoted to wrong color");
				} else if (p == Piece.whiteKing || p == Piece.blackKing || p == Piece.whitePawn || p == Piece.blackPawn) {
					getBoardInstance().setGameOver(true);
					System.out.println("game over, promoted to illegal piece");
				} else {
					Board.updateBoard(new Move(p, this.targetPosition, this.targetPosition, false), getBoardInstance().getBoard());
				}
			}
			if (!m.sufficientMaterialOnBoard(getBoardInstance().getBoard())) {
				System.out.println("draw due to insufficient material");
				getBoardInstance().setGameOver(true);
				getBoardInstance().setDraw(true);
			}


			PlayerColor enemyColor = null;
			if (this.getColor() == PlayerColor.WHITE) enemyColor = PlayerColor.BLACK;
			if (this.getColor() == PlayerColor.BLACK) enemyColor = PlayerColor.WHITE;
			boolean checkmated = m.isCheckMated(enemyColor, getBoardInstance().cloneBoard(getBoardInstance().getBoard()));
			PlayerColor nextMoveColor = null;
			if (this.getColor() == PlayerColor.WHITE) nextMoveColor = PlayerColor.BLACK;
			else if (this.getColor() == PlayerColor.BLACK) nextMoveColor = PlayerColor.WHITE;

			if (checkmated) {
				getBoardInstance().setGameOver(true);
				if (enemyColor == PlayerColor.WHITE) {
					getBoardInstance().setWhiteCheckmated(true);
				} else {
					getBoardInstance().setBlackCheckmated(true);
				}
			} else if (m.isStaleMated(nextMoveColor, getBoardInstance().getBoard())) {
				System.out.println("Stalemate! " + nextMoveColor + " king is stalemated! Draw!");
				getBoardInstance().setGameOver(true);
				getBoardInstance().setDraw(true);
			}
			if (getBoardInstance().threeTimeRepetition()) {
				System.out.println("Draw! Position has been reached three times");
				getBoardInstance().setGameOver(true);
				getBoardInstance().setDraw(true);
			}
			boolean enemyChecked = m.inCheck(enemyColor, getBoardInstance().getBoard());

			SoundManager sound = new SoundManager();
			try {
				if (getBoardInstance().sound) {
					if (checkmated) {
						sound.playCheckmateSound();
					} else if (getBoardInstance().isDraw()) {
						sound.playDrawSound();
					} else if (enemyChecked) {
						sound.playCheckSound();
					} else {
						sound.playMoveAudio();
					}
				}
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				throw new RuntimeException(e);
			}
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

	/**
	 * Get the piece that is to be promoted to from user text input
	 * @return the piece that the pawn promotes to
	 */
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
		return this.getPiece() + " from " + this.getCurrentPosition() + " to " + this.getTargetPosition();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Move m){
            return this.getPiece() == m.getPiece() && this.getCurrentPosition() == m.getCurrentPosition() && this.getTargetPosition() == m.getTargetPosition();
		}
		return false;
	}
}
