package puzzle;

import gamefoundation.Board;
import gamefoundation.Move;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.ControllerChessboard;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class PuzzleGamemode extends Thread{

    private Puzzle puzzle;
    DatabaseConnection db;
    private Semaphore semaphore;

    /**
     * Establishes a database connection and starts the thread and therefore the puzzle gameLoop
     */
    public PuzzleGamemode() {
        db = new DatabaseConnection();
        semaphore = new Semaphore(0);
        this.start();
    }

    /**
     *  GameLoop consists of fetching a puzzle from the database, sets the current state of the board as the puzzle board, displays the board
     */
    public void run(){
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxml/chessboard.fxml"));
        try {
            Parent boardRoot = boardLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ControllerChessboard c = boardLoader.getController(); // Obtain the controller instance
        int i = 0;
        while(true){
            String[] puzzle = db.requestPuzzle(i);
            if(puzzle == null){
                break;
            }
            Puzzle p = new Puzzle(puzzle);
            setPuzzle(p);

            c.displayPieces();
            try {

                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }


    /**
     * Signal that a puzzle was solved and a new puzzle can be fetched
     */
    public void puzzleReady() {
        semaphore.release(); // Signal that the puzzle is ready
    }


    public Move getSolution(){
        return puzzle.getSolution();
    }

    /**
     * Sets the puzzle and sets the board state as the puzzle
     * @param puzzle the Puzzle that is to be set
     */
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        Board.getBoardInstance().setBoard(puzzle.getBoard());
    }
}
