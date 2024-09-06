package gamefoundation;

/**
 * Enum that is used to assign Unicode Values and the associated foundation.PlayerColor-Enum to the Chess Pieces
 * @author lalbr
 *
 */
@SuppressWarnings("ALL")
public enum Piece {
	
	
	blackPawn("\u265F",PlayerColor.BLACK, "/images/pieces/black_pawn.png"),
	blackKnight("\u265E",PlayerColor.BLACK, "/images/pieces/black_knight.png"),
	blackBishop("\u265D",PlayerColor.BLACK, "/images/pieces/black_bishop.png"),
	blackRook("\u265C",PlayerColor.BLACK, "/images/pieces/black_rook.png"),
	blackQueen("\u265B",PlayerColor.BLACK, "/images/pieces/black_queen.png"),
	blackKing("\u265A",PlayerColor.BLACK, "/images/pieces/black_king.png"),
	whitePawn("\u2659",PlayerColor.WHITE, "/images/pieces/white_pawn.png"),
	whiteKnight("\u2658",PlayerColor.WHITE, "/images/pieces/white_knight.png"),
	whiteBishop("\u2657",PlayerColor.WHITE, "/images/pieces/white_bishop.png"),
	whiteRook("\u2656",PlayerColor.WHITE, "/images/pieces/white_rook.png"),
	whiteQueen("\u2655",PlayerColor.WHITE, "/images/pieces/white_queen.png"),
	whiteKing("\u2654",PlayerColor.WHITE, "/images/pieces/white_king.png"),;
	
	private final String unicode;
	private final PlayerColor color;
	private final String path;

	Piece(String unicode,PlayerColor color,String path){
		this.unicode = unicode;
		this.color = color;
		this.path = path;
	}
	
	public String getUnicode(){
		return unicode;
	}
	public PlayerColor getPieceColor() {
		return color;
	}
	public String getPath() {
		return path;
	}
}
