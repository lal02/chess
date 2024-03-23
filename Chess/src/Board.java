import java.util.ArrayList;

/**
 * The Board class represents the chessboard.
 * The class provides the current playing position and a function to update the board. The board-Array is provided as a String.
 * The board is internally handled as a 2D Array of Piece Enums.
 * The board is initialized with the standard starting position of a classical chess game in the POV of the white player.
 * Uses the Singleton Design-Pattern to ensure only one Board Instance and usage of the correct board array.
 * Saves all played Moves in an ArrayList for Usage in other classes
 * @author lalbr
 * 
 */
public class Board {

	private final Piece blackRook = Piece.blackRook;
	private final Piece blackKnight = Piece.blackKnight;
	private final Piece blackBishop = Piece.blackBishop;
	//private final Piece blackBishop2 = Piece.blackBishop;
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
	public ArrayList<Move> playedMoves = new ArrayList<Move>();
	
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
	 * It sets the old position to null and moves the Piece to the new position.
	 * @param move The move object that is to be executed
	 * @param boardParam The board that is to be updated. Either the currently active gameboard or a copy of the board used to simulate the position after a move is made
	 */
	public void updateBoard(Move move, Piece[][] boardParam) {
		//alte position null setzen neue Position das Zeichen setzen

			int oldRow = move.getCurrentPosition().getRow();
			int oldColumn = move.getCurrentPosition().getColumn();
			boardParam[oldRow][oldColumn] = null;
			int newRow = move.getTargetPosition().getRow();
			int newColumn = move.getTargetPosition().getColumn();
			boardParam[newRow][newColumn] = move.getPiece();


	}
	
	public void removePiece(Position pos, Piece[][] boardParam) {
		boardParam[pos.getRow()][pos.getColumn()] = null;
	}

	public void placePiece(Position pos, Piece[][] board, Piece piece){
		board[pos.getRow()][pos.getColumn()] = piece;
	}

	/**
	 * used to reset the board when testing code
	 */
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
	
	public ArrayList<Move> getPlayedMoves() {
		return playedMoves;
	}
}
