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

    public boolean puzzle = false;
    public static PuzzleGamemode puzzleGamemode = null;
    private static FXMain instance = null;

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
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        if(input.equals("puzzle")){
            setPuzzle();
            puzzleGamemode = new PuzzleGamemode();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMain.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Chess");

        Controller c = loader.getController();
        c.displayPieces();
        c.addDragListeners();

        primaryStage.show();
    }



    private void setPuzzle(){
        this.puzzle = true;
    }

}
