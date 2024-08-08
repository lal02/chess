package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import settings.Background;
import settings.Settings;

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
    }

    private void applyButtonImages(Background background, Button button){
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResourceAsStream(background.getPath()),130,130,false,false);
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
    }

    private void removeBorders(){
        for(Button button : imageButtons){
            button.setStyle("-fx-border-color:transparent");
        }
    }

    @FXML
    public void onChooseThemeButtonPressed(){
        for(Button button : imageButtons){
            button.setVisible(true);
        }
    }

    @FXML
    public void onBrownBoardButtonPressed(){
        removeBorders();
        brownBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.BROWN);
    }
    @FXML
    public void onPinkBoardButtonPressed(){
        removeBorders();
        pinkBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.PINK);
    }
    @FXML
    public void onLightBlueBoardButtonPressed(){
        removeBorders();
        lightBlueBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.LIGHTBLUE);
    }
    @FXML
    public void onLightGreenBoardButtonPressed(){
        removeBorders();
        lightGreenBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.LIGHTGREEN);
    }
    @FXML
    public void onLightRedBoardButtonPressed(){
        removeBorders();
        lightRedBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.LIGHTRED);
    }
    @FXML
    public void onDarkBlueBoardButtonPressed(){
        removeBorders();
        darkBlueBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.DARKBLUE);
    }
    @FXML
    public void onDarkGreenBoardButtonPressed(){
        removeBorders();
        darkGreenBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.DARKGREEN);
    }
    @FXML
    public void onDarkRedBoardButtonPressed(){
        removeBorders();
        darkRedBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.DARKRED);
    }
    @FXML
    public void onPurpleBoardButtonPressed(){
        removeBorders();
        purpleBoardButton.setStyle("-fx-border-color:green");
        Settings.getSettingsInstance().setBackground(Background.PURPLE);
    }


    @FXML
    public void onReturnToMenuButtonPressed(){
        try {
            FXMain.setMainMenuScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onSoundCheckBoxChanged(){
        if(soundCheckBox.isSelected()){
            Settings.getSettingsInstance().setSound(true);
        }
        else{
            Settings.getSettingsInstance().setSound(false);
        }
    }
}
