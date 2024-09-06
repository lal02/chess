import gamefoundation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckMateTest {
    /*
    Fools Mate
    backrank mate
    ladder checkmate
    double checks
    pawn checkmates
    pinned piece knight checkmate
    normal protected queen checkmate
    anastastia mate
    middle of the board mate
     */

    //always the color getting checkmated

    @BeforeEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }

    @Test
    void testBlackFoolsMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F7, Position.F6, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE));
    }

    @Test
    void testWhiteFoolsMated(){
        new Move(Piece.whitePawn, Position.G2, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.F2, Position.F3, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackQueen, Position.D8, Position.H4, PlayerColor.BLACK));
    }

    @Test
    void testBlackBackRankMated(){
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.H5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H5, Position.G6, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F6, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.G7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.G7, Position.C3, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C3, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.G6, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteRook, Position.H1, Position.H8, PlayerColor.WHITE));
    }

    @Test
    void testWhiteBackRankMated(){
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H5, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.G2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H4, Position.G3, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.G2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E4, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.G3, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackRook, Position.H8, Position.H1, PlayerColor.BLACK));
    }

    @Test
    void testBlackLadderMated(){
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F7, Position.F5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.A1, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.G5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.G7, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.A3, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F6, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H3, Position.H7, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H7, Position.G7, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteRook, Position.H1, Position.H8, PlayerColor.WHITE));
    }

    @Test
    void testWhiteLadderMated(){
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.G2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.A8, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.G5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H6, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.A6, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B3, Position.B4, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H6, Position.H2, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B4, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H2, Position.G2, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B5, Position.B6, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackRook, Position.H8, Position.H1, PlayerColor.BLACK));
    }

    @Test
    void testBlackPinnedPieceKnightMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.G4, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C3, Position.D5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.H6, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteKnight, Position.D5, Position.F6, PlayerColor.WHITE));
    }

    @Test
    void testWhitePinnedPieceKnightMated(){
        new Move(Piece.whiteKnight, Position.G1, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.C6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(Piece.whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.E5, Position.G6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.F6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.E5, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B3, Position.B4, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B4, Position.B5, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackKnight, Position.E5, Position.F3, PlayerColor.BLACK));
    }

    @Test
    void testBlackDoubleCheckMated(){
        new Move(Piece.whiteKnight, Position.B1, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D2, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D4, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.D8, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.D8, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E1, Position.C1, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteBishop, Position.D2, Position.G5, PlayerColor.WHITE));
    }

    @Test
    void testWhiteDoubleCheckMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D5, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.D1, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E1, Position.D1, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H3, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.H5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H5, Position.H6, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.A8, Position.D8, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H6, Position.G7, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackBishop, Position.D7, Position.G4, PlayerColor.BLACK));
    }

    @Test
    void testBlackPawnMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E4, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E5, Position.E6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A4, Position.A3, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whitePawn, Position.E6, Position.F7, PlayerColor.WHITE));
    }

    @Test
    void testWhitePawnMated(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E5, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E4, Position.E3, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A6, Position.B7, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackPawn, Position.E3, Position.F2, PlayerColor.BLACK));
    }


    @Test
    void testBlackBishopMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.C4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteBishop, Position.C4, Position.F7, PlayerColor.WHITE));
    }

    @Test
    void testWhiteBishopMated(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.C5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackBishop, Position.C5, Position.F2, PlayerColor.BLACK));
    }

    @Test
    void testBlackQueenMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E4, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E5, Position.E6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A4, Position.A3, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteQueen, Position.F3, Position.F7, PlayerColor.WHITE));
    }

    @Test
    void testWhiteQueenMated(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E5, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E4, Position.E3, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A6, Position.B7, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackQueen, Position.F6, Position.F2, PlayerColor.BLACK));
    }

    @Test
    void testBlackAnastasiaMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.G8, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.H4, Position.F5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F5, Position.E7, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.G8, Position.H8, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H5, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F6, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.C2, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.H8, Position.H7, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whitePawn, Position.H3, Position.G4, PlayerColor.WHITE));
    }

    @Test
    void testWhiteAnastasiaMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E1, Position.G1, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G5, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.G1, Position.H2, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F6, Position.D5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.D5, Position.F4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F4, Position.E2, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H3, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H5, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.D3,Position.H7,PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() ->new Move(Piece.blackRook,Position.H8,Position.H7,PlayerColor.BLACK));
    }

    @Test
    void testBlackMiddleOfTheBoardMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.F3, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.D6, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.C6, Position.B6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.C2, Position.C4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.B6, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.G3, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whiteQueen, Position.E5, Position.B5, PlayerColor.WHITE));
    }

    @Test
    void testWhiteMiddleOfTheBoardMated(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.F3, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.F6, Position.F4, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.G3, Position.H3, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackQueen, Position.F4, Position.H4, PlayerColor.BLACK));
    }
}
