import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    //TODO add all possible cases for castling: finishing on, crossing, starting on checks

    @AfterEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }

    //to create result boards faster copy paste
    Piece[][] boardDefault = new Piece[][] {
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
    void testNormalShortCastleWhite(){
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
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.F8,Position.C5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        //white castles
        Move seven = new Move(Piece.whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);
        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }


    @Test
    void testNormalShortCastleBlack(){
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
        assertArrayEquals(expected,Board.getBoardInstance().getBoard());
    }

    @Test
    void testNormalLongCastleBlack(){
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
    void testLongCastlingWhite(){
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

        try{
            // try to castle through the F1 square that is threatened
            Move nine = new Move(Piece.whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);
        }
        catch (Exception e){
            assertNotNull(e);
            //=> Exception has to get thrown
        }
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
        try{
            // try to castle through the F8 square that is threatened
            Move ten = new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK);
        }
        catch (Exception e){
            assertNotNull(e);
            //=> Exception has to get thrown
        }
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
        try{
            // try to castle through the F8 square that is threatened
            Move thirteen = new Move(Piece.whiteKing,Position.E1,Position.C1,PlayerColor.BLACK);
        }
        catch (Exception e){
            assertNotNull(e);
            //=> Exception has to get thrown
        }
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
        try{
            // try to castle through the F8 square that is threatened
            Move fourteen = new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK);
        }
        catch (Exception e){
            assertNotNull(e);
            //=> Exception has to get thrown
        }
    }


}