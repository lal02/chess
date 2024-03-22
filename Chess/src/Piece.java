/**
 * Enum that is used to assign Unicode Values and the associated PlayerColor-Enum to the Chess Pieces
 * @author lalbr
 *
 */
public enum Piece {
	
	
	blackPawn("\u265F",PlayerColor.BLACK),
	blackKnight("\u265E",PlayerColor.BLACK),
	blackBishop("\u265D",PlayerColor.BLACK),
	blackRook("\u265C",PlayerColor.BLACK),
	blackQueen("\u265B",PlayerColor.BLACK),
	blackKing("\u265A",PlayerColor.BLACK),
	whitePawn("\u2659",PlayerColor.WHITE),
	whiteKnight("\u2658",PlayerColor.WHITE),
	whiteBishop("\u2657",PlayerColor.WHITE),
	whiteRook("\u2656",PlayerColor.WHITE),
	whiteQueen("\u2655",PlayerColor.WHITE),
	whiteKing("\u2654",PlayerColor.WHITE);
	
	private final String unicode;
	private final PlayerColor color;
	
	Piece(String unicode,PlayerColor color){
		this.unicode = unicode;
		this.color = color;
	}
	
	public String getUnicode(){
		return unicode;
	}
	public PlayerColor getPieceColor() {
		return color;
	}
}
