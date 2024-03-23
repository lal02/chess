import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingMoveTest {

    @AfterEach @BeforeEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }



    //to create result boards faster copy paste
    Piece[][] expected = new Piece[][] {
            {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
            {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
            {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,Piece.whiteKing,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
    };


    @Test
    void testNormalKingMoves(){
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKing,Position.E1,Position.E2,PlayerColor.WHITE);
        Move four = new Move(Piece.blackKing,Position.E8,Position.E7,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteKing,Position.E2,Position.F3,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKing,Position.E7,Position.F6,PlayerColor.BLACK);

        //try 2 square illegal move
        assertThrows(RuntimeException.class,()->{
            Move seven = new Move(Piece.whiteKing,Position.F3,Position.H3,PlayerColor.WHITE);

        });
        //try 2 square illegal move
        assertThrows(RuntimeException.class,()->{
            Move eight = new Move(Piece.blackKing,Position.F6,Position.H6,PlayerColor.BLACK);
        });
    }


    @Test
    void testLegalShortCastleWhite(){
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.F8,Position.C5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        //white castles
        Move seven = new Move(Piece.whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);

        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,null,null,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,null,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,null,null,Piece.blackPawn,Piece.blackKnight,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null,Piece.whitePawn,null,null,null},
                {null,null,null,null,null,Piece.whiteKnight,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteBishop,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,null,Piece.whiteRook,Piece.whiteKing,null}
        };

        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }


    @Test
    void testLegalShortCastleBlack(){
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,null,Piece.blackRook,Piece.blackKing,null},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,null,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,null,null,Piece.blackPawn,Piece.blackKnight,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null,Piece.whitePawn,null,null,null},
                {null,null,null,null,null,Piece.whiteKnight,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteBishop,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,null,Piece.whiteKing,null,Piece.whiteRook}
        };
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.F8,Position.C5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteKing,Position.E1,Position.F1,PlayerColor.WHITE);
        //black castles
        Move eight = new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK);
        //System.out.println(Board.getBoardInstance());
        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }

    @Test
    void testLegalLongCastleBlack(){
        Piece[][] expected = new Piece[][] {
                {null,null,Piece.blackKing,Piece.blackRook,null,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackQueen,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,Piece.blackKnight,null,null,null,null,null},
                {null,null,null,Piece.blackPawn,null,Piece.blackBishop,null,null},
                {null,null,null,Piece.whitePawn,null,Piece.whiteBishop,null,null},
                {null,null,Piece.whiteKnight,null,null,null,null,Piece.whitePawn},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteQueen,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,null},
                {Piece.whiteRook,null,null,null,Piece.whiteKing,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
        };
        Move one = new Move(Piece.whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.B1,Position.C3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.B8,Position.C6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        Move nine = new Move(Piece.whitePawn,Position.H2,Position.H3,PlayerColor.WHITE);
        //black castle
        Move ten = new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK);

        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }

    @Test
    void testLegalLongCastlingWhite(){
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,null,null,null,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackQueen,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,Piece.blackKnight,null,null,null,null,null},
                {null,null,null,Piece.blackPawn,null,Piece.blackBishop,null,null},
                {null,null,null,Piece.whitePawn,null,Piece.whiteBishop,null,null},
                {null,null,Piece.whiteKnight,null,null,null,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteQueen,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {null,null,Piece.whiteKing,Piece.whiteRook,null,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
        };
        Move one = new Move(Piece.whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.B1,Position.C3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.B8,Position.C6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        //white castle
        Move nine = new Move(Piece.whiteKing,Position.E1,Position.C1,PlayerColor.WHITE);


        assertArrayEquals(expected,Board.getBoardInstance().getBoard());

    }

    @Test
    void testPassThroughCheckShortCastleWhite(){
        Move one = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.D7,Position.D6,PlayerColor.BLACK);
        Move three = new Move(Piece.whitePawn,Position.G2,Position.G3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.C8,Position.E6,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.F1,Position.G2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackBishop,Position.E6,Position.C4,PlayerColor.BLACK);
        Move seven = new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);

        //board is expected to have the state of move eight => before the castling attempt was made
        //this is not implemented currently; current state just throws RuntimeException when IllegalMoveException gets thrown while move validity checking
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,null,null,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackQueen,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,null,Piece.blackPawn,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null,Piece.whitePawn,Piece.whiteKnight,Piece.whitePawn,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,null,Piece.whitePawn,Piece.whiteBishop,Piece.whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,Piece.whiteKing,null,null,Piece.whiteRook}
        };
        // try to castle through the F1 square that is threatened
        assertThrows(RuntimeException.class,()->{
            Move nine = new Move(Piece.whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);
        });

    }
    @Test
    void testPassThroughCheckShortCastleBlack(){
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackKnight,Position.G8,Position.H6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteBishop,Position.F1,Position.C4,PlayerColor.WHITE);
        Move four = new Move(Piece.blackPawn,Position.E7,Position.E5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteQueen,Position.D1,Position.E2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackBishop,Position.F8,Position.E7,PlayerColor.BLACK);
        Move seven = new Move(Piece.whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
        Move nine = new Move(Piece.whitePawn,Position.A3,Position.A4,PlayerColor.WHITE);


        //this is not implemented currently; current state just throws RuntimeException when IllegalMoveException gets thrown while move validity checking
        Piece[][] expected = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,null,null,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackBishop,null,Piece.blackPawn,Piece.blackPawn},
                {null,null,null,null,null,Piece.blackPawn,null,Piece.blackKnight},
                {null,null,null,null,Piece.blackPawn,null,null,null},
                {Piece.whitePawn,null,Piece.whiteBishop,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteQueen,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,null,Piece.whiteKing,null,Piece.whiteKnight,Piece.whiteRook}
        };
        //System.out.println(Board.getBoardInstance().toString());
        //assertArrayEquals(expected,Board.getBoardInstance().getBoard());

        // try to castle through the F8 square that is threatened
        assertThrows(RuntimeException.class,()->{

            Move ten = new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK);
        });
    }

    @Test
    void testPassThroughCheckLongCastleWhite(){
        Move one = new Move(Piece.whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        Move nine = new Move(Piece.whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        Move ten = new Move(Piece.blackPawn,Position.C7,Position.C6,PlayerColor.BLACK);
        Move eleven = new Move(Piece.whitePawn,Position.H2,Position.H3,PlayerColor.WHITE);
        Move twelve = new Move(Piece.blackBishop,Position.F5,Position.C2,PlayerColor.BLACK);

            // try to castle through the F8 square that is threatened

        assertThrows(RuntimeException.class,()->{
            Move thirteen = new Move(Piece.whiteKing,Position.E1,Position.C1,PlayerColor.BLACK);
        });

    }

    @Test
    void testPassThroughCheckLongCastleBlack(){
        Move one = new Move(Piece.whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.D7,Position.D5,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.C8,Position.F5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.C1,Position.F4,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        Move nine = new Move(Piece.whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        Move ten = new Move(Piece.blackPawn,Position.C7,Position.C6,PlayerColor.BLACK);
        Move eleven = new Move(Piece.whitePawn,Position.H2,Position.H3,PlayerColor.WHITE);
        Move twelve = new Move(Piece.blackBishop,Position.F5,Position.C2,PlayerColor.BLACK);
        Move thirteen = new Move(Piece.whiteBishop,Position.F4,Position.C7,PlayerColor.WHITE);

            // try to castle through the F8 square that is threatened

        assertThrows(RuntimeException.class,()->{
            Move fourteen = new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK);
        });

    }

    @Test
    void testWhileCheckedShortCastleWhite(){
        Move one = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackQueen,Position.D8,Position.E7,PlayerColor.BLACK);
        Move five = new Move(Piece.whitePawn,Position.D2,Position.D4,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteBishop,Position.F1,Position.D3,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.E7,Position.B4,PlayerColor.BLACK);

            // try to castle when the king is in check

        assertThrows(RuntimeException.class,()->{
            Move nine = new Move(Piece.whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);
        });
    }

    @Test
    void testWhileCheckedShortCastlingBlack(){
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
        Move three = new Move(Piece.whitePawn,Position.D2,Position.D3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move five = new Move(Piece.whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        Move six = new Move(Piece.blackBishop,Position.F8,Position.D6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whitePawn,Position.B2,Position.B3,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackKnight,Position.G8,Position.H6,PlayerColor.BLACK);
        Move nine = new Move(Piece.whiteQueen,Position.D1,Position.H5,PlayerColor.WHITE);

            // try to castle when the king is in check

        assertThrows(RuntimeException.class,()->{
            Move ten = new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK);
        });
    }
    @Test
    void testWhileCheckedLongCastlingWhite(){
        Move one = new Move(Piece.whitePawn,Position.D2,Position.D3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whitePawn,Position.B2,Position.B3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackQueen,Position.D8,Position.G5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.C1,Position.B2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackPawn,Position.A7,Position.A6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whiteQueen,Position.D1,Position.D2,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackPawn,Position.A6,Position.A5,PlayerColor.BLACK);
        Move nine = new Move(Piece.whiteKnight,Position.B1,Position.A3,PlayerColor.WHITE);
        Move ten = new Move(Piece.blackPawn,Position.A5,Position.A4,PlayerColor.BLACK);
        Move eleven = new Move(Piece.whitePawn,Position.F2,Position.F3,PlayerColor.WHITE);
        Move twelve = new Move(Piece.blackQueen,Position.G5,Position.H4,PlayerColor.BLACK);


            // try to castle when the king is in check

        assertThrows(RuntimeException.class,()->{
            Move thirteen = new Move(Piece.whiteKing,Position.E1,Position.C1,PlayerColor.WHITE);
        });
    }

    @Test
    void testWhileCheckedLongCastlingBlack(){
        Move one = new Move(Piece.whitePawn,Position.D2,Position.D3,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.D7,Position.D6,PlayerColor.BLACK);
        Move three = new Move(Piece.whitePawn,Position.E2,Position.E3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.C8,Position.E6,PlayerColor.BLACK);
        Move five = new Move(Piece.whitePawn,Position.C2,Position.C3,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.B8,Position.A6,PlayerColor.BLACK);
        Move seven = new Move(Piece.whitePawn,Position.B2,Position.B3,PlayerColor.WHITE);
        Move eight = new Move(Piece.blackQueen,Position.D8,Position.D7,PlayerColor.BLACK);
        Move nine = new Move(Piece.whitePawn,Position.A2,Position.A3,PlayerColor.WHITE);
        Move ten = new Move(Piece.blackPawn,Position.F7,Position.F6,PlayerColor.BLACK);
        Move eleven = new Move(Piece.whiteQueen,Position.D1,Position.H5,PlayerColor.WHITE);


        // try to castle when the king is in check



        assertThrows(RuntimeException.class,()->{
            Move twelve = new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK);
        });
    }


}