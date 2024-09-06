package puzzle;

import utility.LoggingUtility;

public class PuzzleGamemode extends Thread{

    private static DatabaseConnection db;
    private final int puzzleCount;

    public PuzzleGamemode() {
        db = new DatabaseConnection();
        puzzleCount = db.getLatestPuzzle();
    }

    /**
     * Retrieves the Puzzle objected stored in the database with the according index
     * @param index the index of the puzzle to be retrieved from the database
     * @return Puzzle Object
     */
    public Puzzle fetchPuzzle(int index){
        String[] puzzleString = db.retrievePuzzle(index);
        LoggingUtility.getLogger().info("Fetching puzzle with index: " + index);
        return new Puzzle(puzzleString);
    }

    public int getPuzzleCount() {
        return puzzleCount;
    }
}
