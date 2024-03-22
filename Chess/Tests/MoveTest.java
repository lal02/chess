import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    //TODO add all possible cases for castling: finishing on, crossing, starting on checks
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
    void testShortCastlingWhite(){

        Piece[][] whiteCastle = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,Piece.blackKing,null,null,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,null,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,null,null,Piece.blackPawn,Piece.blackKnight,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null,Piece.whitePawn,null,null,null},
                {null,null,null,null,null,Piece.whiteKnight,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteBishop,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,null,Piece.whiteRook,Piece.whiteKing,null}
        };



        //legal moves
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.F8,Position.C5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        //white has Castled
        Move seven = new Move(Piece.whiteKing,Position.E1,Position.G1,PlayerColor.WHITE);
        assertEquals(whiteCastle.toString(),Board.getBoardInstance().getBoard().toString());

    }


    @Test
    void testShortCastlingBlack(){



        Piece[][] blackCastle = new Piece[][] {
                {Piece.blackRook,Piece.blackKnight,Piece.blackBishop,Piece.blackQueen,null,Piece.blackRook,Piece.blackKing,null},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,null,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,null,null,Piece.blackPawn,Piece.blackKnight,null,null},
                {null,null,Piece.blackBishop,null,null,null,null,null},
                {null,null,null,null,Piece.whitePawn,null,null,null},
                {null,null,null,null,null,Piece.whiteKnight,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteBishop,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {Piece.whiteRook,Piece.whiteKnight,Piece.whiteBishop,Piece.whiteQueen,null,Piece.whiteRook,Piece.whiteKing,null}
        };


        //legal moves
        Move one = new Move(Piece.whitePawn,Position.E2,Position.E4,PlayerColor.WHITE);
        Move two = new Move(Piece.blackPawn,Position.E7,Position.E6,PlayerColor.BLACK);
        Move three = new Move(Piece.whiteKnight,Position.G1,Position.F3,PlayerColor.WHITE);
        Move four = new Move(Piece.blackBishop,Position.F8,Position.E5,PlayerColor.BLACK);
        Move five = new Move(Piece.whiteBishop,Position.F1,Position.E2,PlayerColor.WHITE);
        Move six = new Move(Piece.blackKnight,Position.G8,Position.F6,PlayerColor.BLACK);
        //white has Castled
        Move seven = new Move(Piece.whiteKing,Position.E1,Position.F1,PlayerColor.WHITE);

        //black has castled
        Move eight = new Move(Piece.blackKing,Position.E8,Position.G8,PlayerColor.BLACK);
        assertEquals(blackCastle,Board.getBoardInstance().getBoard());
    }

    @Test
    void testLongCastling(){
        Piece[][] whiteCastle = new Piece[][] {
                {Piece.blackRook,null,null,null,Piece.blackKing,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackQueen,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,Piece.blackKnight,null,null,null,null,null},
                {null,null,null,Piece.blackPawn,null,Piece.blackBishop,null,null},
                {null,null,null,Piece.whitePawn,null,Piece.whiteBishop,null,null},
                {null,null,Piece.whiteKnight,null,null,null,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteQueen,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {null,null,Piece.whiteKing,Piece.whiteRook,null,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
        };

        Piece[][] blackCastle = new Piece[][] {
                {null,null,Piece.blackKing,Piece.blackRook,null,Piece.blackBishop,Piece.blackKnight,Piece.blackRook},
                {Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackQueen,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn,Piece.blackPawn},
                {null,null,Piece.blackKnight,null,null,null,null,null},
                {null,null,null,Piece.blackPawn,null,Piece.blackBishop,null,null},
                {null,null,null,Piece.whitePawn,null,Piece.whiteBishop,null,null},
                {null,null,Piece.whiteKnight,null,null,null,null,null},
                {Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whiteQueen,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn,Piece.whitePawn},
                {null,null,Piece.whiteKing,Piece.whiteRook,null,Piece.whiteBishop,Piece.whiteKnight,Piece.whiteRook}
        };

        //legal moves
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
        assertEquals(whiteCastle,Board.getBoardInstance().getBoard());
        //black castle
        Move ten = new Move(Piece.blackKing,Position.E8,Position.C8,PlayerColor.BLACK);
        assertEquals(blackCastle,Board.getBoardInstance().getBoard());
    }


}