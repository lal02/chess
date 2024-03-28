import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.PlayerColor;
import gamefoundation.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckMateTest {

    @Test
    void testBlackCheckmatedByQueen(){
        new Move(Piece.whitePawn, Position.E2, Position.E3, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.G7, Position.G5, PlayerColor.BLACK);
        new Move(Piece.whitePawn, Position.E3, Position.E4, PlayerColor.WHITE);
        new Move(Piece.blackPawn, Position.F7, Position.F6, PlayerColor.BLACK);

        //assertThrows(RuntimeException.class,() -> new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE));

        new Move(Piece.whiteQueen, Position.D1, Position.H5, PlayerColor.WHITE);

        //assertThrows(RuntimeException.class,() ->  new Move(Piece.blackPawn, Position.A7, Position.A6, PlayerColor.BLACK));


        //how to test if game is over?
    }
}
