import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.PlayerColor;
import gamefoundation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckMateTest {

    /*
    pinned pieces -> diagonal and horizontal
    double checks
    pawn checkmates
    knight checkmates
    pinned piece knight checkmate
    ladder checkmate
    normal protected queen checkmate
    anastastia mate
    middle of the board mate

    => everything for both colors
     */


    @Test
    void testFoolsMate(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F7, Position.F6, PlayerColor.BLACK);

        //assertThrows(RuntimeException.class,() -> new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE));



        assertThrows(RuntimeException.class,() -> {
            new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE);
        });

        //assertThrows(RuntimeException.class,() ->  new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK));


        //how to test if game is over?
    }

    @Test
    void testRookBackRankCheckmate(){

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
}
