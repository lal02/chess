import gamefoundation.Board;
import gamefoundation.Move;
import gamefoundation.Piece.*;
import gamefoundation.PlayerColor;
import gamefoundation.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static gamefoundation.Piece.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StaleMateTest {

    @BeforeEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }

     //TODO this test is illegal :D it does not have sufficient material on the board
    void testBlackStaleMated(){
        new Move(whitePawn, Position.D2, Position.D4, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(whitePawn, Position.D4, Position.D5, PlayerColor.WHITE);
        new Move(blackPawn, Position.H7, Position.H5, PlayerColor.BLACK);
        new Move(whitePawn, Position.D5, Position.E6, PlayerColor.WHITE);
        new Move(blackPawn, Position.D7, Position.E6, PlayerColor.BLACK);
        new Move(whiteQueen, Position.D1, Position.D8, PlayerColor.WHITE);
        new Move(blackKing, Position.E8, Position.D8, PlayerColor.BLACK);
        new Move(whitePawn, Position.H2, Position.H3, PlayerColor.WHITE);
        new Move(blackBishop, Position.F8, Position.B4, PlayerColor.BLACK);
        new Move(whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(blackBishop, Position.B4, Position.D2, PlayerColor.BLACK);
        new Move(whiteKnight, Position.B1, Position.D2, PlayerColor.WHITE);
        new Move(blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(whitePawn, Position.G2, Position.G4, PlayerColor.WHITE);
        new Move(blackPawn, Position.H5, Position.G4, PlayerColor.BLACK);
        new Move(whitePawn, Position.H3, Position.G4, PlayerColor.WHITE);
        new Move(blackRook, Position.H8, Position.H1, PlayerColor.BLACK);
        new Move(whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(blackBishop, Position.D7, Position.B5, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.C1, PlayerColor.WHITE);
        new Move(blackRook, Position.H1, Position.F1, PlayerColor.BLACK);
        new Move(whiteRook, Position.D1, Position.F1, PlayerColor.WHITE);
        new Move(blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(blackBishop, Position.B5, Position.F1, PlayerColor.BLACK);
        new Move(whiteKnight, Position.D2, Position.F1, PlayerColor.WHITE);
        new Move(blackKnight, Position.F6, Position.E4, PlayerColor.BLACK);
        new Move(whitePawn, Position.G4, Position.G5, PlayerColor.WHITE);
        new Move(blackKnight, Position.E4, Position.F2, PlayerColor.BLACK);
        new Move(whitePawn, Position.G5, Position.G6, PlayerColor.WHITE);
        new Move(blackPawn, Position.F7, Position.G6, PlayerColor.BLACK);
        new Move(whitePawn, Position.A2, Position.A4, PlayerColor.WHITE);
        new Move(blackPawn, Position.G6, Position.G5, PlayerColor.BLACK);
        new Move(whiteKnight, Position.F3, Position.G5, PlayerColor.WHITE);
        new Move(blackPawn, Position.B7, Position.B5, PlayerColor.BLACK);
        new Move(whiteKnight, Position.G5, Position.E6, PlayerColor.WHITE);
        new Move(blackKing, Position.D8, Position.D7, PlayerColor.BLACK);
        new Move(whiteKnight, Position.E6, Position.G7, PlayerColor.WHITE);
        new Move(blackPawn, Position.B5, Position.A4, PlayerColor.BLACK);
        new Move(whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(blackPawn, Position.A4, Position.B3, PlayerColor.BLACK);
        new Move(whitePawn, Position.C2, Position.B3, PlayerColor.WHITE);
        new Move(blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(whiteKnight, Position.G7, Position.E8, PlayerColor.WHITE);
        new Move(blackRook, Position.A8, Position.E8, PlayerColor.BLACK);
        new Move(whiteKing, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(blackRook, Position.E8, Position.E1, PlayerColor.BLACK);
        new Move(whiteKing, Position.D2, Position.E1, PlayerColor.WHITE);
        new Move(blackPawn, Position.A7, Position.A5, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.F2, PlayerColor.WHITE);
        new Move(blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(whitePawn, Position.B3, Position.A4, PlayerColor.WHITE);
        new Move(blackKing, Position.D7, Position.C8, PlayerColor.BLACK);
        new Move(whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(blackKnight, Position.C6, Position.A5, PlayerColor.BLACK);
        new Move(whiteKnight, Position.F1, Position.D2, PlayerColor.WHITE);
        new Move(blackPawn, Position.C7, Position.C5, PlayerColor.BLACK);
        new Move(whiteKing, Position.F2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.C5, Position.C4, PlayerColor.BLACK);
        new Move(whiteKnight, Position.D2, Position.C4, PlayerColor.WHITE);
        new Move(blackKing, Position.C8, Position.C7, PlayerColor.BLACK);
        new Move(whiteKnight, Position.C4, Position.A5, PlayerColor.WHITE);
        new Move(blackKing, Position.C7, Position.B6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E3, Position.D4, PlayerColor.WHITE);
        new Move(blackKing, Position.B6, Position.A7, PlayerColor.BLACK);
        new Move(whiteKing, Position.D4, Position.C5, PlayerColor.WHITE);
        new Move(blackKing, Position.A7, Position.A8, PlayerColor.BLACK);
        new Move(whiteKnight, Position.A5, Position.C6, PlayerColor.WHITE);
        new Move(blackKing, Position.A8, Position.B7, PlayerColor.BLACK);
        new Move(whiteKing, Position.C5, Position.D6, PlayerColor.WHITE);
        new Move(blackKing, Position.B7, Position.A8, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(whiteKing, Position.D6, Position.C7, PlayerColor.WHITE));
    }
}
