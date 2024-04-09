package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import puzzle.PuzzleGamemode;

import java.io.IOException;

public class ControllerMainMenu {

    @FXML
    //public Button newGameButton = new Button();
    public Button newGameButton;
    @FXML
    public Button puzzleButton = new Button();
    @FXML
    public Button createPuzzleButton = new Button();

    public boolean puzzle = false;
    public static PuzzleGamemode puzzleGamemode = null;


    public void test() throws IOException {

    }

    public void newGame() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxmlfiles/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot);
        String title = "Chess Game";
        FXMain.setScene(boardScene,title);
        ControllerChessboard c = boardLoader.getController();
        c.displayPieces();
        c.addDragListeners();
    }

    public void puzzleGame() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxmlfiles/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot);
        setPuzzle();
        puzzleGamemode = new PuzzleGamemode();
        String title = "Puzzle Mode";
        FXMain.setScene(boardScene,title);
        ControllerChessboard c = boardLoader.getController();
        c.displayPieces();
        c.addDragListeners();
    }


    public void puzzleCreator() throws IOException {
        FXMLLoader puzzleCreatorLoader = new FXMLLoader(getClass().getResource("/resources/fxmlfiles/puzzlecreator.fxml"));
        Parent puzzleCreatorRoot = puzzleCreatorLoader.load();
        Scene puzzleCreatorScene = new Scene(puzzleCreatorRoot);
        String title = "Puzzle Creator";
        FXMain.setScene(puzzleCreatorScene,title);

    }

    public void toggleSound(){
        System.out.println("todo implement sound");
    }






    private void setPuzzle(){
        this.puzzle = true;
    }

}
