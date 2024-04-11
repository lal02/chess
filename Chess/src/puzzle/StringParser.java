package puzzle;

import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.Position;

/**
 * This class provides functions used to interact with the Database.
 * It provides the possibility to convert between Strings and Objects.
 */
public class StringParser {

    /**
     * Converts a given pieceString to a Piece enum
     * @param pieceString the string to be parsed to a Piece
     * @return the Piece being represented by the pieceString
     */
    public Piece getPieceFromString(String pieceString){
        return switch (pieceString) {
            case "bp" -> Piece.blackPawn;
            case "br" -> Piece.blackRook;
            case "bb" -> Piece.blackBishop;
            case "bh" -> Piece.blackKnight;
            case "bq" -> Piece.blackQueen;
            case "bk" -> Piece.blackKing;
            case "wr" -> Piece.whiteRook;
            case "wb" -> Piece.whiteBishop;
            case "wh" -> Piece.whiteKnight;
            case "wk" -> Piece.whiteKing;
            case "wp" -> Piece.whitePawn;
            case "wq" -> Piece.whiteQueen;
            default -> null;
        };
    }

    /**
     * Converts a Piece enum to a pieceString
     * @param p the Piece to be parsed to a String
     * @return the String representing the piece
     */
    public String getStringFromPiece(Piece p){
        if(p == null){
            return "nu";
        }
        else{
            return switch (p) {
                case blackPawn -> "bp";
                case blackRook -> "br";
                case blackBishop -> "bb";
                case blackKnight -> "bh";
                case blackQueen -> "bq";
                case blackKing -> "bk";
                case whiteRook -> "wr";
                case whiteBishop -> "wb";
                case whiteKnight -> "wh";
                case whiteKing -> "wk";
                case whitePawn -> "wp";
                case whiteQueen -> "wq";
            };
        }
    }

    /**
     * Converts a String of length 128 to a 2D PieceArray representing the current state of the chessboard
     * @param boardString the string that is to be converted
     * @return 2D Piece Array filled with the Pieces of the given boardString
     */
    public Piece[][] getBoardFromString(String boardString){
        if(boardString.length() != 128){
            System.out.println("error");
            return null;
        }
        Piece[][] board = new Piece[8][8];
        String[] boardPairs = boardString.split("(?<=\\G.{2})");
        int counter = 0;
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                board[i][j] = getPieceFromString(boardPairs[counter]);
                counter++;
            }
        }
        return board;
    }

    //decode: 6char move -> 2char piece 2 char currentposition 2char targetposition

    /**
     * Converts a String of length 6 to a Move Object
     * @param moveString the String that is to be converted
     * @return Move Object from a given String
     */
    public Move getMoveFromString(String moveString){
        String[] solutionPairs = moveString.split("(?<=\\G.{2})");
        return new Move(getPieceFromString(solutionPairs[0]), Position.valueOf(solutionPairs[1]),Position.valueOf(solutionPairs[2]),false);
    }

}
