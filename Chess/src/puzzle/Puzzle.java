package puzzle;

import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.Position;

import java.io.Serializable;

public class Puzzle implements Serializable {

    private Piece[][] board = null;
    private Move solution = null;

    public Puzzle(String[] puzzle){
        convertQueryResultToPuzzle(puzzle);
    }


    //decode: 128char board -> each piece 2 chars, null = nu
    //decode: 6char move -> 2char piece 2 char currentposition 2char targetposition
    public void convertQueryResultToPuzzle(String[] input){
        String boardString = input[0];
        String solutionString = input[1];

        String[] boardPairs = boardString.split("(?<=\\G.{2})");

        Piece[][] board = new Piece[8][8];
        int l = 0;
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                board[i][j] = getPieceFromString(boardPairs[l]);
                l++;
            }
        }
        this.board = board;

        String[] solutionPairs = solutionString.split("(?<=\\G.{2})");
        int m = 0;
        this.solution = new Move(getPieceFromString(solutionPairs[0]),getPositionFromString(solutionPairs[1]),getPositionFromString(solutionPairs[2]),false);

    }

    private Piece getPieceFromString(String s){
        switch(s){
            case "bp": return Piece.blackPawn;
            case "br": return Piece.blackRook;
            case "bb": return Piece.blackBishop;
            case "bh": return Piece.blackKnight;
            case "bq": return Piece.blackQueen;
            case "bk": return Piece.blackKing;
            case "wr": return Piece.whiteRook;
            case "wb": return Piece.whiteBishop;
            case "wh": return Piece.whiteKnight;
            case "wk": return Piece.whiteKing;
            case "wp": return Piece.whitePawn;
            default: return null;
        }
    }

    private Position getPositionFromString(String s){

        return  Position.valueOf(s);
    }

    public Piece[][] getBoard(){
        return board;
    }
    public Move getSolution(){
        return solution;
    }

}
