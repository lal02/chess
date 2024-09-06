package puzzle;

import gamefoundation.*;
import utility.LoggingUtility;

public class Puzzle {

    private Piece[][] board = null;
    private Move solution = null;

    /**
     * Takes a String array of length 2 and calls the convertQueryResultToPuzzle function
     * @param puzzle String array that contains the boardString and the solutionString
     */
    public Puzzle(String[] puzzle){
        convertQueryResultToPuzzle(puzzle);
    }


    /**
     * Converts boardString and solutionString to Piece[][]and Move object
     * @param input A string array of length 2 with the boardString at index 0 and the solutionMoveString at index 1
     */
    public void convertQueryResultToPuzzle(String[] input){
        String boardString = input[0];
        String solutionString = input[1];
        StringParser parser = new StringParser();
        this.board = parser.getBoardFromString(boardString);
        this.solution = parser.getMoveFromString(solutionString);
        LoggingUtility.getLogger().info("Converting String to Puzzle");
    }

    public Piece[][] getBoard(){
        return board;
    }
    public Move getSolution(){
        return solution;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Puzzle){
            Puzzle p = (Puzzle) obj;
            return Board.getBoardInstance().boardEquals(this.board, p.board) && this.solution.equals(p.solution);
        }
        return false;
    }
}
