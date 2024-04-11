package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class FXMain extends Application {

    private static Stage primaryStage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMain.primaryStage = primaryStage;
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/resources/fxml/mainmenu.fxml"));
        Parent menuRoot = menuLoader.load();
        Scene menuScene = new Scene(menuRoot, 500  , 300);
        primaryStage.setScene(menuScene);

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }



    public static void setScene(Scene scene,String title){
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
    }

}
