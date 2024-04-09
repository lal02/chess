package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import puzzle.PuzzleGamemode;


import java.io.IOException;
import java.util.Scanner;

public class FXMain extends Application {

    private static FXMain instance = null;
    private static Stage primaryStage;
    public FXMain(){

    }

    public static FXMain getInstance(){
        if(instance == null){
            instance = new FXMain();
        }
        return instance;
    }

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMain.primaryStage = primaryStage;
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/resources/mainmenu.fxml"));
        Parent menuRoot = menuLoader.load();
        Scene menuScene = new Scene(menuRoot, 500  , 300);
        primaryStage.setScene(menuScene);


        primaryStage.setResizable(false);
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }





    public static void setScene(Scene scene){
        primaryStage.setScene(scene);
    }

}
