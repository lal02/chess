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
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot, 800, 800);
        FXMain.setScene(boardScene);
        ControllerChessboard c = boardLoader.getController();
        c.displayPieces();
        c.addDragListeners();
    }

    public void newPuzzle() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot, 800, 800);

        setPuzzle();
        puzzleGamemode = new PuzzleGamemode();
        FXMain.setScene(boardScene);
        ControllerChessboard c = boardLoader.getController();
        c.displayPieces();
        c.addDragListeners();


    }




    private void setPuzzle(){
        this.puzzle = true;
    }

}
