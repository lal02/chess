import foundation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    /**
     * Tests the playedMoves ArrayList and if the moves only get stored if the move is legal
     */
    //TODO Castling moves should count as one move
    @Test
    void testPlayedMoves(){
        Move one = new Move(Piece.whitePawn,Position.A2,Position.A4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.A7,Position.A6, PlayerColor.BLACK);
        Move three = new Move(Piece.whitePawn,Position.A4,Position.A5,PlayerColor.WHITE);
        Move four = new Move(Piece.blackPawn,Position.B7,Position.B5,PlayerColor.BLACK);
        Move five = new Move(Piece.whitePawn,Position.A5, Position.B6,PlayerColor.WHITE);
        Move six = new Move(Piece.blackPawn,Position.C7,Position.B6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        assertEquals(7, Board.getBoardInstance().getPlayedMoves().size());

        //illegal foundation.Move shouldnt get added to playedMoves
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackKing,Position.E8,Position.E7,PlayerColor.BLACK));
        assertEquals(7,Board.getBoardInstance().getPlayedMoves().size());

        //also illegal foundation.Move because a different colour should be expected. not to be added to list
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteRook, Position.A1, Position.A2, PlayerColor.WHITE));
        assertEquals(7,Board.getBoardInstance().getPlayedMoves().size());

        //legal moves
        Move ten = new Move(Piece.blackPawn , Position.B6, Position.B5, PlayerColor.BLACK);
        Move eleven = new Move(Piece.whiteRook, Position.A1, Position.A2, PlayerColor.WHITE);
        Move twelve = new Move(Piece.blackPawn , Position.B5, Position.B4, PlayerColor.BLACK);
        Move thirteen = new Move(Piece.whiteKnight, Position.A3, Position.B5, PlayerColor.WHITE);
        Move fourteen = new Move(Piece.blackPawn , Position.A6, Position.B5, PlayerColor.BLACK);
        Move fifteen = new Move(Piece.whiteRook, Position.A2, Position.A8, PlayerColor.WHITE);
        Move sixteen = new Move(Piece.blackKnight , Position.B8, Position.C6, PlayerColor.BLACK);
        Move seventeen = new Move(Piece.whiteRook, Position.A8, Position.C8, PlayerColor.WHITE);
        assertEquals(15,Board.getBoardInstance().getPlayedMoves().size());

        //illegal because would be in check
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackQueen,Position.D8,Position.B6,PlayerColor.BLACK));
        assertEquals(15,Board.getBoardInstance().getPlayedMoves().size());



    }
}