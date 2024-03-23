import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PawnMoveTest {
    @AfterEach
    @BeforeEach
    void resetBoard(){
        Board.getBoardInstance().resetGame();
    }
}
