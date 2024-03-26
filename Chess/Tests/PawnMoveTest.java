import foundation.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnMoveTest {
    //things to test:
    // first move 2 square -> only first move;
    // no forward capture
    // en passant
    // normal pawn capture
    @BeforeEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }

    @Test
    void testFirstMove(){
        assertDoesNotThrow(()->{
            new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
            new Move(Piece.blackPawn,Position.E7,Position.E5,PlayerColor.BLACK);
            new Move(Piece.whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);
            new Move(Piece.blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
            //normal diagonal pawn capture
            //foundation.Move five = new foundation.Move(foundation.Piece.whitePawn,foundation.Position.E4,foundation.Position.F6,foundation.PlayerColor.WHITE);
        });

    }

    @Test
    void testOnlyFirstMoveTwoSquares(){
        new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.E7,Position.E5,PlayerColor.BLACK);
        assertThrows(Throwable.class,()->{
            new Move(Piece.whitePawn, Position.E3,Position.E5,PlayerColor.WHITE);
        });

        new Move(Piece.whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);

        assertThrows(Throwable.class,()->{
        new Move(Piece.blackPawn,Position.E5,Position.E3,PlayerColor.BLACK);
        });
    }

    @Test
    void testIllegalDiagonalPawnMove(){
        //new foundation.Move(foundation.Piece.whitePawn,foundation.Position.E2,foundation.Position.E3,foundation.PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()->{
           new Move(Piece.whitePawn,Position.E2,Position.F3,PlayerColor.WHITE);
        });
        assertThrows(Throwable.class,()->{
            new Move(Piece.whitePawn,Position.E2,Position.D3,PlayerColor.WHITE);
        });

        new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);

        assertThrows(RuntimeException.class,()->{
            new Move(Piece.blackPawn,Position.E7,Position.F6,PlayerColor.WHITE);
        });
        assertThrows(RuntimeException.class,()->{
            new Move(Piece.blackPawn,Position.E7,Position.D6,PlayerColor.WHITE);
        });

    }

    @Test
    void testLegalDiagonalPawnCapture(){
        assertDoesNotThrow(()->{
            new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
            new Move(Piece.blackPawn,Position.E7,Position.E5,PlayerColor.BLACK);
            new Move(Piece.whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);
            new Move(Piece.blackPawn,Position.F7,Position.F5,PlayerColor.BLACK);
            //diagonal pawn capture white to the right
            new Move(Piece.whitePawn,Position.E4,Position.F5,PlayerColor.WHITE);

            new Move(Piece.blackPawn,Position.G7,Position.G6,PlayerColor.BLACK);
            new Move(Piece.whitePawn,Position.F2,Position.F4,PlayerColor.WHITE);
            //diagonal pawn capture black to the left
            new Move(Piece.blackPawn,Position.G6,Position.F5,PlayerColor.BLACK);
            //diagonal pawn capture white to the left
            new Move(Piece.whitePawn,Position.F4,Position.E5,PlayerColor.WHITE);

            new Move(Piece.blackPawn,Position.H7,Position.H6,PlayerColor.BLACK);
            new Move(Piece.whiteQueen,Position.D1,Position.G4,PlayerColor.WHITE);
            //diagonal pawn capture black to the right
            new Move(Piece.blackPawn,Position.F5,Position.G4,PlayerColor.BLACK);
        });
    }

    @Test
    void testNoForwardCapture(){
        new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.E7,Position.E5,PlayerColor.BLACK);

        assertThrows(Throwable.class,()->{
            //forward capture move
           new Move(Piece.whitePawn,Position.E4,Position.E5,PlayerColor.WHITE);
        });
    }

    @Test
    void testEnPassant(){
        new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(Piece.whitePawn,Position.E4,Position.E5,PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.F7,Position.F5,PlayerColor.BLACK);

        //white right side en passant
        assertDoesNotThrow(()->{
            new Move(Piece.whitePawn,Position.E5,Position.F6,PlayerColor.WHITE);
        });

        new Move(Piece.blackPawn,Position.E6,Position.E5,PlayerColor.BLACK);
        new Move(Piece.whitePawn,Position.C2,Position.C4,PlayerColor.WHITE);

        new Move(Piece.blackPawn,Position.E5,Position.E4,PlayerColor.BLACK);
        new Move(Piece.whitePawn,Position.F2,Position.F4,PlayerColor.WHITE);

        //black right side en passant
        assertDoesNotThrow(()->{
            new Move(Piece.blackPawn,Position.E4,Position.F3,PlayerColor.BLACK);
        });

        new Move(Piece.whitePawn,Position.C4,Position.C5,PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.B7,Position.B5,PlayerColor.BLACK);

        //white left side en passant
        assertDoesNotThrow(()->{
            new Move(Piece.whitePawn,Position.C5,Position.B6,PlayerColor.WHITE);
        });

        new Move(Piece.blackPawn,Position.C7,Position.C5, PlayerColor.BLACK);
        new Move(Piece.whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.C5,Position.C4,PlayerColor.BLACK);
        new Move(Piece.whitePawn,Position.B2,Position.B4,PlayerColor.WHITE);

        //black left side en passant

        assertDoesNotThrow(()->{
            new Move(Piece.blackPawn,Position.C4,Position.B3,PlayerColor.BLACK);
        });

    }
}
