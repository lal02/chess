package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import settings.Settings;
import utility.LoggingUtility;

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
    public Button settingsButton;

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
    @FXML
    public void newGame() throws IOException {
        loadProcess("/resources/fxml/chessboard.fxml","Singleplayer Chess");
    }

    /**
     * Switches the scene to the multiplayer
     */
    @FXML
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
    @FXML
    public void puzzleGame() throws IOException {
        loadProcess("/resources/fxml/puzzle.fxml","Puzzle Gamemode");
    }

    /**
     * Switches the scene to the puzzle creator
     */
    @FXML
    public void puzzleCreator() throws IOException {
        loadProcess("/resources/fxml/puzzlecreator.fxml","Puzzle Creator");
    }



    /**
     * Toggle sounds when moving a piece
     */
    @FXML
    public void toggleSound(){
        boolean sound = Settings.getSettingsInstance().getSound();
        Settings.getSettingsInstance().setSound(!sound);
        if(sound){
            toggleSoundButton.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/sound_off.png")))));
        }
        else{
            toggleSoundButton.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menu/sound_on.png")))));
        }
        LoggingUtility.getLogger().info("Toggled Sound and Sound-Image");
    }

    @FXML
    public void onSettingsButtonPressed() throws IOException {
        loadProcess("/resources/fxml/settings.fxml","Settings");
    }


    private void loadProcess(String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        FXMain.setScene(new Scene(root),path);
    }
}
