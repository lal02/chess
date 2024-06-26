import gamefoundation.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static gamefoundation.Piece.*;
import static org.junit.jupiter.api.Assertions.*;

class KingMoveTest {

    @AfterEach @BeforeEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }



    //starting board
    Piece[][] expected = new Piece[][] {
            {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
            {blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn},
            {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen, whiteKing,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
    };


    @Test
    void testNormalKingMoves(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(whiteKing, Position.E2, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackKing,Position.E7,Position.F6,PlayerColor.BLACK);

        //try 2 square illegal move
        assertThrows(RuntimeException.class,()-> new Move(whiteKing,Position.F3,Position.H3,PlayerColor.WHITE));
        //try 2 square illegal move
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackKing,Position.F6,Position.H6,PlayerColor.BLACK));
    }


    @Test
    void testLegalShortCastleWhite(){
        new Move(whitePawn, Position.E2,Position.E4,PlayerColor.WHITE);
        new Move(blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.F8,Position.C5,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        //white castles
        new Move(whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);

        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,null,null,Piece.blackRook},
                {blackPawn, blackPawn, blackPawn, blackPawn,null, blackPawn, blackPawn, blackPawn},
                {null,null,null,null, blackPawn,Piece.blackKnight,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null, whitePawn,null,null,null},
                {null,null,null,null,null,Piece.whiteKnight,null,null},
                {whitePawn, whitePawn, whitePawn, whitePawn,Piece.whiteBishop, whitePawn, whitePawn, whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,null,Piece.whiteRook, whiteKing,null}
        };

        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }


    @Test
    void testLegalShortCastleBlack(){
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,null,Piece.blackRook,Piece.blackKing,null},
                {blackPawn, blackPawn, blackPawn, blackPawn,null, blackPawn, blackPawn, blackPawn},
                {null,null,null,null, blackPawn,Piece.blackKnight,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null, whitePawn,null,null,null},
                {null,null,null,null,null,Piece.whiteKnight,null,null},
                {whitePawn, whitePawn, whitePawn, whitePawn,Piece.whiteBishop, whitePawn, whitePawn, whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,null, whiteKing,null, Piece.whiteRook}
        };
        new Move(whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        new Move(blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.F8,Position.C5, PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        new Move(whiteKing,Position.E1,Position.F1,PlayerColor.WHITE);
        //black castles
        new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK);
        //System.out.println(foundation.Board.getBoardInstance());
        assertArrayEquals(expected, Board.getBoardInstance().getBoard());
    }

    @Test
    void testLegalLongCastleBlack(){
        Piece[][] expected = new Piece[][] {
                {null,null,Piece.blackKing,Piece.blackRook,null,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {blackPawn, blackPawn, blackPawn,Piece.blackQueen, blackPawn, blackPawn, blackPawn, blackPawn},
                {null,null,Piece.blackKnight,null,null,null,null,null},
                {null,null,null, blackPawn,null,Piece.blackBishop,null,null},
                {null,null,null, whitePawn,null,Piece.whiteBishop,null,null},
                {null,null,Piece.whiteKnight,null,null,null,null, whitePawn},
                {whitePawn, whitePawn, whitePawn,Piece.whiteQueen, whitePawn, whitePawn, whitePawn,null},
                {Piece.whiteRook,null,null,null, whiteKing,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
        };
        new Move(whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        new Move(blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.B1,Position.C3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.B8,Position.C6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        new Move(whitePawn,Position.H2,Position.H3,PlayerColor.WHITE);
        //black castle
        new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK);

        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }

    @Test
    void testLegalLongCastlingWhite(){
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,null,null,null,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {blackPawn, blackPawn, blackPawn,Piece.blackQueen, blackPawn, blackPawn, blackPawn, blackPawn},
                {null,null,Piece.blackKnight,null,null,null,null,null},
                {null,null,null, blackPawn,null,Piece.blackBishop,null,null},
                {null,null,null, whitePawn,null,Piece.whiteBishop,null,null},
                {null,null,Piece.whiteKnight,null,null,null,null,null},
                {whitePawn, whitePawn, whitePawn,Piece.whiteQueen, whitePawn, whitePawn, whitePawn, whitePawn},
                {null,null, whiteKing,Piece.whiteRook,null,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
        };
        new Move(whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        new Move(blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.B1,Position.C3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.B8,Position.C6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        //white castle
        new Move(whiteKing,Position.E1,Position.C1,PlayerColor.WHITE);


        assertArrayEquals(expected,Board.getBoardInstance().getBoard());

    }

    @Test
    void testPassThroughCheckShortCastleWhite(){
        new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        new Move(blackPawn,Position.D7,Position.D6,PlayerColor.BLACK);
        new Move(whitePawn,Position.G2,Position.G3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.C8,Position.E6,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.F1,Position.G2,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.E6,Position.C4,PlayerColor.BLACK);
        new Move(whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        //board is expected to have the state of move eight => before the castling attempt was made
        //this is not implemented currently; current state just throws RuntimeException when analysis.IllegalMoveException gets thrown while move validity checking
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,null,null,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {blackPawn, blackPawn, blackPawn,Piece.blackQueen, blackPawn, blackPawn, blackPawn, blackPawn},
                {null,null,null, blackPawn,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null, whitePawn,Piece.whiteKnight, whitePawn,null},
                {whitePawn, whitePawn, whitePawn, whitePawn,null, whitePawn,Piece.whiteBishop, whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen, whiteKing,null,null,Piece.whiteRook}
        };
        // try to castle through the F1 square that is threatened
        assertThrows(RuntimeException.class,()-> new Move(whiteKing,Position.E1,Position.G1,PlayerColor.WHITE));

    }
    @Test
    void testPassThroughCheckShortCastleBlack(){
        new Move(whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.G8,Position.H6,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.F1,Position.C4,PlayerColor.WHITE);
        new Move(blackPawn,Position.E7,Position.E5,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.E2,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.F8,Position.E7,PlayerColor.BLACK);
        new Move(whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);
        new Move(blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
        new Move(whitePawn,Position.A3,Position.A4,PlayerColor.WHITE);


        //this is not implemented currently; current state just throws RuntimeException when analysis.IllegalMoveException gets thrown while move validity checking
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,null,null,Piece.blackRook},
                {blackPawn, blackPawn, blackPawn, blackPawn,Piece.blackBishop,null, blackPawn, blackPawn},
                {null,null,null,null,null, blackPawn,null,Piece.blackKnight},
                {null,null,null,null, blackPawn,null,null,null},
                {whitePawn,null,Piece.whiteBishop,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null, whitePawn, whitePawn, whitePawn,Piece.whiteQueen, whitePawn, whitePawn, whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,null, whiteKing,null,Piece.whiteKnight,Piece.whiteRook}
        };
        //System.out.println(foundation.Board.getBoardInstance().toString());
        //assertArrayEquals(expected,foundation.Board.getBoardInstance().getBoard());
        // try to castle through the F8 square that is threatened
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK));
    }

    @Test
    void testPassThroughCheckLongCastleWhite(){
        new Move(whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        new Move(blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        new Move(whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        new Move(blackPawn,Position.C7,Position.C6,PlayerColor.BLACK);
        new Move(whitePawn,Position.H2,Position.H3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.F5,Position.C2,PlayerColor.BLACK);
        // try to castle through the F8 square that is threatened
        assertThrows(RuntimeException.class,()-> new Move(whiteKing,Position.E1,Position.C1,PlayerColor.BLACK));
    }

    @Test
    void testPassThroughCheckLongCastleBlack(){
        new Move(whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        new Move(blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        new Move(whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        new Move(blackPawn,Position.C7,Position.C6,PlayerColor.BLACK);
        new Move(whitePawn,Position.H2,Position.H3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.F5,Position.C2,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.F4,Position.C7,PlayerColor.WHITE);
        // try to castle through the F8 square that is threatened
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK));
    }

    @Test
    void testWhileCheckedShortCastleWhite(){
        new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        new Move(blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.E7,PlayerColor.BLACK);
        new Move(whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.F1,Position.D3,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.E7,Position.B4,PlayerColor.BLACK);
        // try to castle when the king is in check
        assertThrows(RuntimeException.class,()-> new Move(whiteKing,Position.E1,Position.G1,PlayerColor.WHITE));
    }

    @Test
    void testWhileCheckedShortCastlingBlack(){
        new Move(whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        new Move(blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
        new Move(whitePawn,Position.D2,Position.D3,PlayerColor.WHITE);
        new Move(blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.F8,Position.D6,PlayerColor.BLACK);
        new Move(whitePawn,Position.B2,Position.B3,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.G8,Position.H6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.H5,PlayerColor.WHITE);
        // try to castle when the king is in check
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK));
    }
    @Test
    void testWhileCheckedLongCastlingWhite(){
        new Move(whitePawn,Position.D2,Position.D3,PlayerColor.WHITE);
        new Move(blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        new Move(whitePawn,Position.B2,Position.B3,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.G5,PlayerColor.BLACK);
        new Move(Piece.whiteBishop,Position.C1,Position.B2,PlayerColor.WHITE);
        new Move(blackPawn,Position.A7,Position.A6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        new Move(blackPawn,Position.A6,Position.A5,PlayerColor.BLACK);
        new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        new Move(blackPawn,Position.A5,Position.A4,PlayerColor.BLACK);
        new Move(whitePawn,Position.F2,Position.F3,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.G5,Position.H4,PlayerColor.BLACK);
        // try to castle when the king is in check
        assertThrows(RuntimeException.class,()-> new Move(whiteKing,Position.E1,Position.C1,PlayerColor.WHITE));
    }

    @Test
    void testWhileCheckedLongCastlingBlack(){
        new Move(whitePawn,Position.D2,Position.D3,PlayerColor.WHITE);
        new Move(blackPawn,Position.D7,Position.D6,PlayerColor.BLACK);
        new Move(whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        new Move(Piece.blackBishop,Position.C8,Position.E6,PlayerColor.BLACK);
        new Move(whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        new Move(whitePawn,Position.B2,Position.B3,PlayerColor.WHITE);
        new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        new Move(whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);
        new Move(blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
        new Move(Piece.whiteQueen,Position.D1,Position.H5,PlayerColor.WHITE);
        // try to castle when the king is in check
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK));
    }

    @Test
    void testWhiteShortCastlingWhenKingHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(whiteKing, Position.E2, Position.E1, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E7, Position.E8, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,()->new Move(whiteKing, Position.E1, Position.G1, PlayerColor.WHITE));
    }

    @Test
    void testBlackShortCastlingWhenKingHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(whiteKing, Position.E2, Position.E1, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E7, Position.E8, PlayerColor.BLACK);
        new Move(whiteKing,Position.E1,Position.E2,PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()->new Move(Piece.blackKing, Position.E8, Position.G8, PlayerColor.BLACK));
    }

    @Test
    void testWhiteShortCastlingWhenRookHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H1, Position.G1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H8, Position.G8, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.G1, Position.H1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.G8, Position.H8, PlayerColor.BLACK);

        assertThrows(RuntimeException.class,()-> new Move(whiteKing,Position.E1,Position.G1,PlayerColor.WHITE));

    }

    @Test
    void testBlackShortCastlingWhenRookHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.F1, Position.D3, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.F8, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.G1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.G8, Position.F6, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.H1, Position.G1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.H8, Position.G8, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.G1, Position.H1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.G8, Position.H8, PlayerColor.BLACK);
        new Move(whiteKing,Position.E1,Position.E2,PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()-> new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK));
    }

    @Test
    void testWhiteLongCastlingWhenKingHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(whiteKing, Position.E2, Position.E1, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E7, Position.E8, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,()->new Move(whiteKing, Position.E1, Position.C1, PlayerColor.WHITE));
    }

    @Test
    void testBlackLongCastlingWhenKingHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(whiteKing, Position.E2, Position.E1, PlayerColor.WHITE);
        new Move(Piece.blackKing, Position.E7, Position.E8, PlayerColor.BLACK);
        new Move(whiteKing,Position.E1,Position.E2,PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()->new Move(Piece.blackKing, Position.E8, Position.C8, PlayerColor.BLACK));
    }

    @Test
    void testWhiteLongCastlingWhenRookHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.A1, Position.B1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.A8, Position.B8, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.B1, Position.A1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.B8, Position.A8, PlayerColor.BLACK);
        assertThrows(RuntimeException.class,()-> new Move(Piece.whiteKnight,Position.E1,Position.C1,PlayerColor.WHITE));
    }
    @Test
    void testBlackLongCastlingWhenRookHasMoved(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(Piece.whiteQueen, Position.D1, Position.F3, PlayerColor.WHITE);
        new Move(Piece.blackQueen, Position.D8, Position.F6, PlayerColor.BLACK);
        new Move(whitePawn, Position.D2, Position.D3, PlayerColor.WHITE);
        new Move(blackPawn, Position.D7, Position.D6, PlayerColor.BLACK);
        new Move(Piece.whiteBishop, Position.C1, Position.D2, PlayerColor.WHITE);
        new Move(Piece.blackBishop, Position.C8, Position.D7, PlayerColor.BLACK);
        new Move(Piece.whiteKnight, Position.B1, Position.C3, PlayerColor.WHITE);
        new Move(Piece.blackKnight, Position.B8, Position.C6, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.A1, Position.B1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.A8, Position.B8, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.B1, Position.A1, PlayerColor.WHITE);
        new Move(Piece.blackRook, Position.B8, Position.A8, PlayerColor.BLACK);
        new Move(Piece.whiteRook, Position.A1, Position.B1, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()->new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK));
    }

    @Test
    void testKingsCannotTouch(){
        new Move(whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(blackPawn, Position.E7, Position.E6, PlayerColor.BLACK);
        new Move(whiteKing, Position.E1, Position.E2, PlayerColor.WHITE);
        new Move(blackKing, Position.E8, Position.E7, PlayerColor.BLACK);
        new Move(whiteKing, Position.E2, Position.F3, PlayerColor.WHITE);
        new Move(blackKing, Position.E7, Position.F6, PlayerColor.BLACK);
        new Move(whiteKing, Position.F3, Position.F4, PlayerColor.WHITE);
        assertThrows(RuntimeException.class,()->new Move(blackKing, Position.F6, Position.F5, PlayerColor.BLACK));
    }
}