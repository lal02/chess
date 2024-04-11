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

    public static PuzzleGamemode puzzleGamemode = null;

    /**
     * Switches the scene to the chess game and starts it
     * @throws IOException
     */
    public void newGame() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxml/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot);
        String title = "Chess Game";
        FXMain.setScene(boardScene,title);
        ControllerChessboard c = boardLoader.getController();
        c.displayPieces();
        c.addDragListeners();
    }

    /**
     * Switches the scene to the puzzle gamemode and starts it
     * @throws IOException
     */
    public void puzzleGame() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxml/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot);
        ControllerChessboard c = boardLoader.getController();
        puzzleGamemode = new PuzzleGamemode();

        FXMain.setScene(boardScene,"Puzzle Mode");
        c.displayPieces();

        c.addDragListeners();
    }

    /**
     * Switches the scene to the puzzle creator
     * @throws IOException
     */
    public void puzzleCreator() throws IOException {
        FXMLLoader puzzleCreatorLoader = new FXMLLoader(getClass().getResource("/resources/fxml/puzzlecreator.fxml"));
        Parent puzzleCreatorRoot = puzzleCreatorLoader.load();
        Scene puzzleCreatorScene = new Scene(puzzleCreatorRoot);
        ControllerPuzzleCreator controller = puzzleCreatorLoader.getController();
        controller.addDragListeners();
        String title = "Puzzle Creator";
        FXMain.setScene(puzzleCreatorScene,title);
    }

    /**
     * Toggle sounds when moving a piece
     */
    public void toggleSound(){
        System.out.println("todo implement sound");
    }








}
