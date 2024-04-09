package puzzle;

import gamefoundation.Board;
import gamefoundation.Move;
import view.ControllerChessboard;

import java.util.concurrent.Semaphore;

public class PuzzleGamemode extends Thread{

    private Puzzle puzzle;
    DatabaseConnection db;
    private Semaphore semaphore;

    public PuzzleGamemode() {
        db = new DatabaseConnection();
        semaphore = new Semaphore(0);
        this.start();
    }


    public void run(){
        int i = 0;
        while(true){

            String[] puzzle = db.requestPuzzle(i);
            if(puzzle == null){
                break;
            }
            Puzzle p = new Puzzle(puzzle);
            setPuzzle(p);
            ControllerChessboard c = new ControllerChessboard();
            c.displayPieces();
            try {

                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }



    public void puzzleReady() {
        semaphore.release(); // Signal that the puzzle is ready
    }

    public Move getSolution(){
        return puzzle.getSolution();
    }
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        Board.getBoardInstance().setBoard(puzzle.getBoard());
    }
}
