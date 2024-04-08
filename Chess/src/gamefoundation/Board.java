package gamefoundation;

import java.sql.Array;
import java.util.ArrayList;

/**
 * The foundation.Board class represents the chessboard.
 * The class provides the current playing position and a function to update the board. The board-Array is provided as a String.
 * The board is internally handled as a 2D Array of foundation.Piece Enums.
 * The board is initialized with the standard starting position of a classical chess game in the POV of the white player.
 * Uses the Singleton Design-Pattern to ensure only one foundation.Board Instance and usage of the correct board array.
 * Saves all played Moves in an ArrayList for Usage in other classes
 * @author lalbr
 * 
 */
public class Board {

	private final Piece blackRook = Piece.blackRook;
	private final Piece blackKnight = Piece.blackKnight;
	private final Piece blackBishop = Piece.blackBishop;
	//private final foundation.Piece blackBishop2 = foundation.Piece.blackBishop;
	private final Piece blackQueen = Piece.blackQueen;
	private final Piece blackKing = Piece.blackKing;
	private final Piece blackPawn = Piece.blackPawn;
	private final Piece whiteRook = Piece.whiteRook;
	private final Piece whiteKnight = Piece.whiteKnight;
	private final Piece whiteBishop = Piece.whiteBishop;
	private final Piece whiteQueen = Piece.whiteQueen;
	private final Piece whiteKing = Piece.whiteKing;
	private final Piece whitePawn = Piece.whitePawn;
	
	private Piece[][] board;
	public ArrayList<Move> playedMoves = new ArrayList<>();
	public ArrayList<Piece[][]> reachedPositions = new ArrayList<>();
	private static Board instance = null;
	
	
	private Board() {
		board = new Piece[][] {							
								{blackRook,blackKnight,blackBishop,blackQueen,blackKing,blackBishop,blackKnight,blackRook},
								{blackPawn,blackPawn,blackPawn,blackPawn,blackPawn,blackPawn,blackPawn,blackPawn},
								{null,null,null,null,null,null,null,null},
								{null,null,null,null,null,null,null,null},
								{null,null,null,null,null,null,null,null},
								{null,null,null,null,null,null,null,null},
								{whitePawn,whitePawn,whitePawn,whitePawn,whitePawn,whitePawn,whitePawn,whitePawn},
								{whiteRook,whiteKnight,whiteBishop,whiteQueen,whiteKing,whiteBishop,whiteKnight,whiteRook}	
		};
	}
	
	/**
	 * Singleton Design-Pattern that assures only one board instance that is up-to-date all the time
	 * @return the current instance of the chess board
	 */
	public static Board getBoardInstance() {
		if(instance == null) {
			instance = new Board();
		}
		return instance;
	}
	
	
	/**
	 * Updates the Chessboard after the move is made.
	 * It sets the old position to null and moves the foundation.Piece to the new position.
	 * @param move The move object that is to be executed
	 * @param boardParam The board that is to be updated. Either the currently active gameboard or a copy of the board used to simulate the position after a move is made
	 */
	public static void updateBoard(Move move, Piece[][] boardParam) {
		//set the old position to null and the new position has the new piece value on it
			int oldRow = move.getCurrentPosition().getRow();
			int oldColumn = move.getCurrentPosition().getColumn();
			boardParam[oldRow][oldColumn] = null;
			int newRow = move.getTargetPosition().getRow();
			int newColumn = move.getTargetPosition().getColumn();
			boardParam[newRow][newColumn] = move.getPiece();
	}

	/**
	 * This is necessary because Array.clone() does not work with 2D arrays as I have painfully learned
	 * @param source the source board that is to be clones
	 * @return a deep value copy of the board
	 */
	public Piece[][] cloneBoard(Piece[][] source){
		Piece[][] returnedBoard = new Piece[source.length][source[0].length];
		for(int i = 0;i< source.length;i++){
			for(int j = 0;j<source[i].length;j++){
				returnedBoard[i][j] = source[i][j];
			}
		}

		return returnedBoard;
	}

	/**
	 * removes a piece from the board and sets the position to null
	 * @param pos
	 * @param boardParam
	 */
	public void removePiece(Position pos, Piece[][] boardParam) {
		boardParam[pos.getRow()][pos.getColumn()] = null;
	}

	/**
	 * place a piece on a board to a specific position
	 * @param pos
	 * @param board
	 * @param piece
	 */
	public void placePiece(Position pos, Piece[][] board, Piece piece){
		board[pos.getRow()][pos.getColumn()] = piece;
	}

	/**
	 * checks if a position has already been reached three times and therefore
	 * @return
	 */
	public boolean threeTimeRepetition(){
		int counter = 0;
		for(Piece[][] arr : reachedPositions){
			if(boardEquals(arr,this.getBoard())){
				counter++;
				if(counter==3){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if two Piece[][] are equal (have the same position)
	 * @param board1
	 * @param board2
	 * @return true if the position is equal
	 */
	private boolean boardEquals(Piece[][] board1, Piece[][] board2){
		for(int i = 0; i<board1.length;i++){
			for(int j = 0; j<board1[i].length;j++){
				if(board1[i][j] != board2[i][j]){
					return false;
				}
			}
		}
		return true;
	}

	//used to reset the board when testing code
	public void resetGame(){
		board = new Piece[][] {
				{blackRook,blackKnight,blackBishop,blackQueen,blackKing,blackBishop,blackKnight,blackRook},
				{blackPawn,blackPawn,blackPawn,blackPawn,blackPawn,blackPawn,blackPawn,blackPawn},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{whitePawn,whitePawn,whitePawn,whitePawn,whitePawn,whitePawn,whitePawn,whitePawn},
				{whiteRook,whiteKnight,whiteBishop,whiteQueen,whiteKing,whiteBishop,whiteKnight,whiteRook}
		};
		playedMoves.clear();
		reachedPositions.clear();
	}
	
	public String toString() {
	    StringBuilder str = new StringBuilder();
	    for(int i = 0; i < board.length; i++) {
	        for(int j = 0; j < board[i].length; j++) {
	            if(board[i][j] != null) {
	                str.append(board[i][j].getUnicode());
	            } else {
	                str.append(".");
	            }
	        }
	        str.append("\n");
	    }
	    return str.toString();
	}
	
	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public ArrayList<Move> getPlayedMoves() {
		return playedMoves;
	}
}
