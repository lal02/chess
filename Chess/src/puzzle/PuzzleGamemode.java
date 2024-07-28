package puzzle;

public class PuzzleGamemode extends Thread{

    private static DatabaseConnection db;

    public PuzzleGamemode() {
        db = new DatabaseConnection();
    }

    /**
     * Retrieves the Puzzle objected stored in the database with the according index
     * @param index the index of the puzzle to be retrieved from the database
     * @return Puzzle Object
     */
    public Puzzle fetchPuzzle(int index){
        String[] puzzleString = db.retrievePuzzle(index);
        return new Puzzle(puzzleString);
    }
}
