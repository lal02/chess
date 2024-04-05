import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gamefoundation.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckTest {
    //TODO pawn promotion check

    @BeforeEach
    void initialize(){
        Board.getBoardInstance().resetGame();
    }

    @Test
    void testWhiteBishopCheck(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.B5, PlayerColor.WHITE);
        System.out.println(Board.getBoardInstance());
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK));
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);

        new Move(Piece.whiteBishop, Position.B5, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F7, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E2, Position.H5, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK));
    }

    @Test
    void testBlackBishopCheck(){
        new Move(Piece.whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.B4, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,()-> new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE));
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.B4, Position.E7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.F2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.E7, Position.H4, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,()-> new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE));
    }

    @Test
    void testWhiteRookCheck(){
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.G5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H6, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H1, Position.H8, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.G7, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H8, Position.G8, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.G7, Position.F8, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.G8, Position.G5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.G5, Position.E5, PlayerColor.WHITE);

        assertThrows(RuntimeException.class,()-> new Move(Piece.blackPawn,Position.B7,Position.B6,PlayerColor.BLACK));
    }

    @Test
    void testBlackRookCheck(){
        new Move(Piece.whitePawn, Position.G2, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H5, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H3, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H8, Position.H1, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.G2, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H1, Position.G1, PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.G2,Position.F1,PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.G1, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.G4, Position.E4, PlayerColor.BLACK);

        assertThrows(RuntimeException.class,()-> new Move(Piece.whitePawn,Position.B2,Position.B3,PlayerColor.WHITE));

    }

    @Test
    void testWhiteKnightCheck(){
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C3, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B5, Position.C7, PlayerColor.WHITE);
        //should not be allowed because of check
        assertThrows(Throwable.class,() -> new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK));
        new Move(Piece.blackQueen,Position.D8,Position.C7,PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.D4, Position.F5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H6, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F5, Position.G7, PlayerColor.WHITE);
        //should not be allowed because of check
        assertThrows(Throwable.class,() -> new Move(Piece.blackPawn, Position.H5, Position.H4, PlayerColor.BLACK));
    }
    @Test
    void testBlackKnightCheck(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.C6, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B4, Position.C2, PlayerColor.BLACK);
        //when playing h2 h3 after this assertThrows it gets a nullpointer exception??
        assertThrows(Throwable.class,() -> new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE));
        new Move(Piece.whiteQueen, Position.D1, Position.C2, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F6, Position.D5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.D5, Position.F4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.H5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F4, Position.G2, PlayerColor.BLACK);
        assertThrows(Throwable.class,() -> new Move(Piece.whitePawn, Position.D2, Position.D3, PlayerColor.WHITE));

    }

    @Test
    void testWhitePawnCheck(){
        new Move(Piece.whitePawn, Position.E2, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E4, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H6, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E5, Position.E6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H5, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E6, Position.F7, PlayerColor.WHITE);

        assertThrows(RuntimeException.class,()-> new Move(Piece.blackPawn,Position.H4,Position.H4,PlayerColor.BLACK));

        new Move(Piece.blackKing, Position.E8, Position.F7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.F7, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G4, Position.G5, PlayerColor.WHITE);

        assertThrows(RuntimeException.class,()-> new Move(Piece.blackPawn,Position.G7,Position.G6,PlayerColor.BLACK));
    }

    @Test
    void testBlackPawnCheck(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G5, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G4, Position.G3, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G3, Position.F2, PlayerColor.BLACK);

        //from the right check
        assertThrows(RuntimeException.class,()-> new Move(Piece.whitePawn,Position.B2,Position.B3,PlayerColor.WHITE));

        new Move(Piece.whiteKing, Position.E1, Position.F2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.F2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E5, Position.E4, PlayerColor.BLACK);
        //from the left check
        assertThrows(RuntimeException.class,()-> new Move(Piece.whitePawn,Position.C2,Position.C3,PlayerColor.WHITE));

    }

    @Test
    void testWhiteEnPassantCheck(){
        new Move(Piece.whitePawn, Position.D2, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D4, Position.D5, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D5, Position.E6, PlayerColor.WHITE);

        assertThrows(RuntimeException.class,() -> new Move(Piece.blackPawn,Position.H7,Position.H6,PlayerColor.BLACK));

        new Move(Piece.blackPawn, Position.F7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.C2, Position.C4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.D7, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.B7, Position.B6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H3, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.C6, Position.B7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.H5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.B6, Position.B5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.C4, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B5, Position.A6, PlayerColor.WHITE);

        assertThrows(RuntimeException.class,() -> new Move(Piece.blackRook,Position.A8,Position.A7,PlayerColor.BLACK));


    }

    @Test
    void testBlockEnPassantCheck(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E5, Position.E4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.F2, Position.F4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E4, Position.F3, PlayerColor.BLACK);

        assertThrows(RuntimeException.class,()-> new Move(Piece.whitePawn,Position.A2,Position.A3,PlayerColor.WHITE));

        new Move(Piece.whiteKing, Position.E2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G5, Position.G4, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.F3, Position.G2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G4, Position.H3, PlayerColor.BLACK);

        assertThrows(RuntimeException.class,()-> new Move(Piece.whiteRook,Position.H1,Position.H2,PlayerColor.WHITE));
    }

    @Test
    void testPawnBlockCheck(){
            new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
            new Move(Piece.blackPawn, Position.F7, Position.F6, PlayerColor.BLACK);
            new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE);
            new Move(Piece.blackPawn, Position.G7, Position.G6, PlayerColor.BLACK);
            new Move(Piece.whiteQueen, Position.H5, Position.D5, PlayerColor.WHITE);
            new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
            new Move(Piece.whiteQueen, Position.D5, Position.B5, PlayerColor.WHITE);
            new Move(Piece.blackPawn, Position.C7, Position.C6, PlayerColor.BLACK);
            new Move(Piece.whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
            new Move(Piece.blackQueen, Position.D8, Position.A5, PlayerColor.BLACK);
            new Move(Piece.whitePawn, Position.C2, Position.C3, PlayerColor.WHITE);
            new Move(Piece.blackQueen, Position.A5, Position.B5, PlayerColor.BLACK);
            new Move(Piece.whitePawn, Position.F2, Position.F3, PlayerColor.WHITE);
            new Move(Piece.blackQueen, Position.B5, Position.H5, PlayerColor.BLACK);
            new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
            new Move(Piece.blackQueen, Position.H5, Position.H4, PlayerColor.BLACK);
    }

    @Test
    void testBlockBishopChecks(){
        //forward and backwards moves to block a diagonal check given by a bishop with a bishop
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.B5, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.D7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F3, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F7, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.G4, Position.H5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.E6, Position.F7, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.H5, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F3, Position.C6, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.E6, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C6, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D6, Position.D5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.E7, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F3, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F6, Position.F5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.E7, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.B4, Position.C5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.D2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C5, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E3, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.B4, Position.E7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.F2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.D2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.E7, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E3, Position.F2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.H4, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.H3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F2, Position.G1, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.G5, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.G1, Position.F2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.H4, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H1, Position.F1, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.G5, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.F1, Position.F2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.H4, Position.E7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.E7, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.D2, PlayerColor.WHITE);
    }

    @Test
    void testBlockRookCheck(){
        new Move(Piece.whitePawn, Position.H2, Position.H4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H4, Position.G5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H6, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.G7, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H8, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H4, Position.G4, PlayerColor.BLACK);
        // rook check
        //assertDoesNotThrow(() ->new Move(Piece.whiteRook, Position.H1, Position.H8, PlayerColor.WHITE));
        new Move(Piece.whiteRook, Position.H1, Position.H8, PlayerColor.WHITE);
        //block it with bishop
        new Move(Piece.blackBishop, Position.G7, Position.F8, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.G4, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D3, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H4, Position.H3, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.E2, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.E2, PlayerColor.WHITE);
        //rook check
        new Move(Piece.blackRook, Position.H3, Position.H1, PlayerColor.BLACK);
        //block it with bishop
        new Move(Piece.whiteBishop, Position.E2, Position.F1, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.B5, Position.F5, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.B7, Position.B6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.B6, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.B6, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.A1, Position.A8, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D7, Position.D8, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H1, Position.H2, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H2, Position.H1, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.G1, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.A8, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E2, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.A8, Position.A1, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C3, Position.D1, PlayerColor.WHITE);
    }

    @Test
    void testCaptureCheckingPiece(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.G4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.G4, Position.F5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.F5, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.D6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.D2, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A4, Position.B3, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H6, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C3, Position.D5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H5, Position.H4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.D2, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.A8, Position.A1, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C3, Position.A1, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.B7, Position.B6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.B6, Position.B5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.E5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.B5, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.G2, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H4, Position.G3, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.H2, Position.G3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.C7, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.E2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H8, Position.H1, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F3, Position.H1, PlayerColor.WHITE);
    }

    @Test
    void testWhiteKnightChecks(){
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.F3, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.D4, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B5, Position.C7, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.C7, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A4, Position.A3, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C3, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A3, Position.B2, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B5, Position.C7, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.D8, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C7, Position.E6, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.D8, Position.E8, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.E6, Position.C7, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.D8, PlayerColor.BLACK);
    }
    @Test
    void testBlackKnightChecks(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F6, Position.H5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.H5, Position.F4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.F4, Position.G2, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.G2, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A6, Position.B7, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.C6, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.B2, Position.B3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B4, Position.C2, PlayerColor.BLACK);
        new Move(Piece.whiteKing, Position.E1, Position.F1, PlayerColor.WHITE);

    }

    @Test
    void testStraightPinnedWhitePieceCannotCapture(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.F6, Position.E5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A6, Position.B7, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.B4, Position.C5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.D2, Position.D4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.H7, Position.H6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C5, Position.D4, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,() -> new Move(Piece.whitePawn,Position.E3,Position.D4,PlayerColor.WHITE));
        //new Move(Piece.whitePawn,Position.E3,Position.D4,PlayerColor.WHITE);
    }

    @Test
    void testStraightPinnedBlackPieceCannotCapture(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.F3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A4, Position.A3, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.C3, Position.B5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A3, Position.B2, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B5, Position.D6, PlayerColor.WHITE);
        // piece is pinned and capturing that way is therefore illegal
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackPawn,Position.E7,Position.D6,PlayerColor.BLACK));
        //new Move(Piece.blackPawn,Position.E7,Position.D6,PlayerColor.BLACK);

    }

    @Test
    void testDiagonalPinnedBlackPieceCannotCapture(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A6, Position.A5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.C4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.A5, Position.A4, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C4, Position.E6, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,() -> new Move(Piece.blackPawn,Position.F7,Position.E6,PlayerColor.BLACK));

    }

    @Test
    void testDiagonalPinnedWhitePieceCannotCapture(){
        new Move(Piece.whitePawn, Position.A2, Position.A3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A3, Position.A4, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.B4, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A4, Position.A5, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.A5, Position.A6, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.G5, Position.E3, PlayerColor.BLACK);

        assertThrows(RuntimeException.class,()->new Move(Piece.whitePawn,Position.D2,Position.D3,PlayerColor.WHITE));




    }

}
