package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import settings.Background;
import settings.Settings;
import utility.LoggingUtility;

import java.io.IOException;

public class ControllerSettings {

    @FXML
    CheckBox soundCheckBox;

    @FXML
    Button chooseThemeButton;

    @FXML
    Button brownBoardButton;
    @FXML
    Button darkBlueBoardButton;
    @FXML
    Button lightBlueBoardButton;
    @FXML
    Button darkGreenBoardButton;
    @FXML
    Button lightGreenBoardButton;
    @FXML
    Button lightRedBoardButton;
    @FXML
    Button darkRedBoardButton;
    @FXML
    Button pinkBoardButton;
    @FXML
    Button purpleBoardButton;

    @FXML
    Button returnToMenuButton;

    private Button[] imageButtons;

    @FXML
    public void initialize(){
        imageButtons = new Button[]{   brownBoardButton, darkBlueBoardButton,lightBlueBoardButton, darkGreenBoardButton,
                lightGreenBoardButton, lightRedBoardButton, pinkBoardButton, purpleBoardButton,
                darkRedBoardButton  };
        setButtonImages();
    }


    private void setButtonImages(){
        applyButtonImages(Background.BROWN,brownBoardButton);
        applyButtonImages(Background.LIGHTGREEN,lightGreenBoardButton);
        applyButtonImages(Background.LIGHTRED,lightRedBoardButton);
        applyButtonImages(Background.PURPLE,purpleBoardButton);
        applyButtonImages(Background.DARKRED,darkRedBoardButton);
        applyButtonImages(Background.LIGHTBLUE,lightBlueBoardButton);
        applyButtonImages(Background.DARKBLUE,darkBlueBoardButton);
        applyButtonImages(Background.PINK,pinkBoardButton);
        applyButtonImages(Background.DARKGREEN,darkGreenBoardButton);
        LoggingUtility.getLogger().info("Loaded all the theme images for theme choosing buttons");
    }

    private void applyButtonImages(Background background, Button button){
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResourceAsStream(background.getPath()),130,130,false,false);
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        LoggingUtility.getLogger().info("Applied" +background.getPath() +"to " +button.getId());
    }

    private void removeBorders(){
        for(Button button : imageButtons){
            button.setStyle("-fx-border-color:transparent");
        }
        LoggingUtility.getLogger().info("Removed Borders from all theme image buttons");

    }

    @FXML
    public void onChooseThemeButtonPressed(){
        for(Button button : imageButtons){
            button.setVisible(true);
        }
        LoggingUtility.getLogger().info("Selected theme button pressed, switched visibility to true for all theme buttons");
    }

    @FXML
    public void onBrownBoardButtonPressed(){
        removeBorders();
        brownBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.BROWN);
        LoggingUtility.getLogger().info("Activated green border for" + brownBoardButton.getId());
    }
    @FXML
    public void onPinkBoardButtonPressed(){
        removeBorders();
        pinkBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.PINK);
        LoggingUtility.getLogger().info("Activated green border for" + pinkBoardButton.getId());
    }
    @FXML
    public void onLightBlueBoardButtonPressed(){
        removeBorders();
        lightBlueBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.LIGHTBLUE);
        LoggingUtility.getLogger().info("Activated green border for" + lightBlueBoardButton.getId());
    }
    @FXML
    public void onLightGreenBoardButtonPressed(){
        removeBorders();
        lightGreenBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.LIGHTGREEN);
        LoggingUtility.getLogger().info("Activated green border for" + lightGreenBoardButton.getId());
    }
    @FXML
    public void onLightRedBoardButtonPressed(){
        removeBorders();
        lightRedBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.LIGHTRED);
        LoggingUtility.getLogger().info("Activated green border for" + lightRedBoardButton.getId());
    }
    @FXML
    public void onDarkBlueBoardButtonPressed(){
        removeBorders();
        darkBlueBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.DARKBLUE);
        LoggingUtility.getLogger().info("Activated green border for" + darkBlueBoardButton.getId());
    }
    @FXML
    public void onDarkGreenBoardButtonPressed(){
        removeBorders();
        darkGreenBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.DARKGREEN);
        LoggingUtility.getLogger().info("Activated green border for" + darkGreenBoardButton.getId())    ;
    }
    @FXML
    public void onDarkRedBoardButtonPressed(){
        removeBorders();
        darkRedBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.DARKRED);
        LoggingUtility.getLogger().info("Activated green border for" + darkRedBoardButton);
    }
    @FXML
    public void onPurpleBoardButtonPressed(){
        removeBorders();
        purpleBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.PURPLE);
        LoggingUtility.getLogger().info("Activated green border for" + purpleBoardButton.getId());
    }


    @FXML
    public void onReturnToMenuButtonPressed(){
        try {
            FXMain.setMainMenuScene();
            LoggingUtility.getLogger().info("Returning to Main Menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onSoundCheckBoxChanged(){
        LoggingUtility.getLogger().info("Sound toggled in CheckBox");
        if(soundCheckBox.isSelected()){
            Settings.getSettingsInstance().setSound(true);
        }
        else{
            Settings.getSettingsInstance().setSound(false);
        }
    }
}
