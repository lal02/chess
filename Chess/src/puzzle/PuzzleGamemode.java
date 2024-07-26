package puzzle;

public class PuzzleGamemode extends Thread{

    private static DatabaseConnection db;

    public PuzzleGamemode() {
        db = new DatabaseConnection();
    }

    public Puzzle fetchPuzzle(int index){
        String[] puzzleString = db.requestPuzzle(index);
        return new Puzzle(puzzleString);
    }
}
