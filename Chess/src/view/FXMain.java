package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import settings.Settings;


import java.io.IOException;
import java.util.Objects;

public class FXMain extends Application {

    private static Stage primaryStage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMain.primaryStage = primaryStage;

        FXMain.setMainMenuScene();

        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/app_icon.jpg"))));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> onApplicationExit());

        Settings settings = Settings.getSettingsInstance();
    }

    public static void setMainMenuScene() throws IOException {
        FXMLLoader menuLoader = new FXMLLoader(FXMain.class.getResource("/resources/fxml/mainmenu.fxml"));
        Parent menuRoot = menuLoader.load();
        Scene menuScene = new Scene(menuRoot);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(menuScene);
    }

    public static void setScene(Scene scene,String title){
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
    }

    private void onApplicationExit(){
        //save settings
        Settings settings = Settings.getSettingsInstance();
        settings.saveSettings();

        Platform.exit();
    }

}
