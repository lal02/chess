package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import settings.Settings;

import java.io.IOException;
import java.util.Objects;

public class ControllerMainMenu {

    @FXML
    public Button newGameButton;
    @FXML
    public Button puzzleButton = new Button();
    @FXML
    public Button createPuzzleButton = new Button();
    @FXML
    public Button toggleSoundButton = new Button();
    @FXML
    public Button multiplayerButton = new Button();

    @FXML
    void initialize(){
        if(Settings.getSettingsInstance().getSound()){
            toggleSoundButton.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/sound_on.png")))));
        }
        else{
            toggleSoundButton.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/sound_off.png")))));
        }

    }

    /**
     * Switches the scene to the chess game and starts it
     */
    public void newGame() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxml/chessboard.fxml"));
        Parent boardRoot = boardLoader.load();
        FXMain.setScene(new Scene(boardRoot),"Singleplayer Chess");
    }

    /**
     * Switches the scene to the multiplayer
     */
    public void multiplayer() throws IOException {
//        FXMLLoader mpLoader = new FXMLLoader(getClass().getResource("/resources/fxml/multiplayer.fxml"));
//        Parent mpRoot = mpLoader.load();
//        Scene scene = new Scene(mpRoot);
//        ChessServer s = ChessServer.getInstance();
//        FXMain.setScene(scene,"Multiplayer Chess");
        System.out.println("WIP");
    }

    /**
     * Switches the scene to the puzzle gamemode and starts it
     */
    public void puzzleGame() throws IOException {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/resources/fxml/puzzle.fxml"));
        Parent boardRoot = boardLoader.load();
        Scene boardScene = new Scene(boardRoot);
        FXMain.setScene(boardScene,"Puzzle Gamemode");
    }

    /**
     * Switches the scene to the puzzle creator
     */
    public void puzzleCreator() throws IOException {
        FXMLLoader puzzleCreatorLoader = new FXMLLoader(getClass().getResource("/resources/fxml/puzzlecreator.fxml"));
        Parent puzzleCreatorRoot = puzzleCreatorLoader.load();
        Scene puzzleCreatorScene = new Scene(puzzleCreatorRoot);
        FXMain.setScene(puzzleCreatorScene,"Puzzle Creator");
    }



    /**
     * Toggle sounds when moving a piece
     */
    public void toggleSound(){
        boolean sound = Settings.getSettingsInstance().getSound();
        Settings.getSettingsInstance().setSound(!sound);
        if(sound){
            toggleSoundButton.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/sound_off.png")))));
        }
        else{
            toggleSoundButton.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/sound_on.png")))));
        }
    }
}
