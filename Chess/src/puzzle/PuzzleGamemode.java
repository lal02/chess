package puzzle;

import gamefoundation.Board;
import gamefoundation.Move;

public class PuzzleGamemode {

    private Puzzle puzzle;

    public PuzzleGamemode() {
        DatabaseConnection db = new DatabaseConnection();
        String[] puzzle = db.requestPuzzle(0);
        Puzzle p = new Puzzle(puzzle);
        setPuzzle(p);
    }

    public Move getSolution(){
        return puzzle.getSolution();
    }
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        Board.getBoardInstance().setBoard(puzzle.getBoard());
    }
}
