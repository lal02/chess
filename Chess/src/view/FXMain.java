package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utility.LoggingUtility;
import settings.Settings;


import java.io.IOException;
import java.util.Objects;
import java.util.logging.*;

public class FXMain extends Application {

    private static Stage primaryStage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LoggingUtility.getLogger().info("Application Startup");

        FXMain.primaryStage = primaryStage;
        FXMain.setMainMenuScene();

        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/other/app_icon.jpg"))));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> onApplicationExit());


        Settings settings = Settings.getSettingsInstance();
        LoggingUtility.getLogger().info("Load Settings");
    }

    public static void setMainMenuScene() throws IOException {
        FXMLLoader menuLoader = new FXMLLoader(FXMain.class.getResource("/resources/fxml/mainmenu.fxml"));
        Parent menuRoot = menuLoader.load();
        Scene menuScene = new Scene(menuRoot);
        primaryStage.setTitle("main menu");
        primaryStage.setScene(menuScene);
        LoggingUtility.getLogger().info("Switching Scene to main menu");

    }

    public static void setScene(Scene scene,String title){

        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        LoggingUtility.getLogger().info("Switching Scene to " + title);
    }

    private void onApplicationExit(){
        //save settings
        Settings.getSettingsInstance().saveSettings();

        LoggingUtility.getLogger().info("Saving Settings");
        LoggingUtility.getLogger().info("Application Exit");
        Platform.exit();
    }

}
