package view;

import gamefoundation.Board;
import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.Position;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import settings.Background;
import settings.Settings;
import utility.LoggingUtility;

import java.io.IOException;
import java.util.Objects;

public class ControllerChessboard {
    @FXML
    ImageView A8 = new ImageView();
    @FXML
    ImageView B8 = new ImageView();
    @FXML
    ImageView C8 = new ImageView();
    @FXML
    ImageView D8 = new ImageView();
    @FXML
    ImageView E8 = new ImageView();
    @FXML
    ImageView F8 = new ImageView();
    @FXML
    ImageView G8 = new ImageView();
    @FXML
    ImageView H8 = new ImageView();
    @FXML
    ImageView A7 = new ImageView();
    @FXML
    ImageView B7 = new ImageView();
    @FXML
    ImageView C7 = new ImageView();
    @FXML
    ImageView D7 = new ImageView();
    @FXML
    ImageView E7 = new ImageView();
    @FXML
    ImageView F7 = new ImageView();
    @FXML
    ImageView G7 = new ImageView();
    @FXML
    ImageView H7 = new ImageView();
    @FXML
    ImageView A6 = new ImageView();
    @FXML
    ImageView B6 = new ImageView();
    @FXML
    ImageView C6 = new ImageView();
    @FXML
    ImageView D6 = new ImageView();
    @FXML
    ImageView E6 = new ImageView();
    @FXML
    ImageView F6 = new ImageView();
    @FXML
    ImageView G6 = new ImageView();
    @FXML
    ImageView H6 = new ImageView();
    @FXML
    ImageView A5 = new ImageView();
    @FXML
    ImageView B5 = new ImageView();
    @FXML
    ImageView C5 = new ImageView();
    @FXML
    ImageView D5 = new ImageView();
    @FXML
    ImageView E5 = new ImageView();
    @FXML
    ImageView F5 = new ImageView();
    @FXML
    ImageView G5 = new ImageView();
    @FXML
    ImageView H5 = new ImageView();
    @FXML
    ImageView A4 = new ImageView();
    @FXML
    ImageView B4 = new ImageView();
    @FXML
    ImageView C4 = new ImageView();
    @FXML
    ImageView D4 = new ImageView();
    @FXML
    ImageView E4 = new ImageView();
    @FXML
    ImageView F4 = new ImageView();
    @FXML
    ImageView G4 = new ImageView();
    @FXML
    ImageView H4 = new ImageView();
    @FXML
    ImageView A3 = new ImageView();
    @FXML
    ImageView B3 = new ImageView();
    @FXML
    ImageView C3 = new ImageView();
    @FXML
    ImageView D3 = new ImageView();
    @FXML
    ImageView E3 = new ImageView();
    @FXML
    ImageView F3 = new ImageView();
    @FXML
    ImageView G3 = new ImageView();
    @FXML
    ImageView H3 = new ImageView();
    @FXML
    ImageView A2 = new ImageView();
    @FXML
    ImageView B2 = new ImageView();
    @FXML
    ImageView C2 = new ImageView();
    @FXML
    ImageView D2 = new ImageView();
    @FXML
    ImageView E2 = new ImageView();
    @FXML
    ImageView F2 = new ImageView();
    @FXML
    ImageView G2 = new ImageView();
    @FXML
    ImageView H2 = new ImageView();
    @FXML
    ImageView A1 = new ImageView();
    @FXML
    ImageView B1 = new ImageView();
    @FXML
    ImageView C1 = new ImageView();
    @FXML
    ImageView D1 = new ImageView();
    @FXML
    ImageView E1 = new ImageView();
    @FXML
    ImageView F1 = new ImageView();
    @FXML
    ImageView G1 = new ImageView();
    @FXML
    ImageView H1 = new ImageView();


    @FXML
    ImageView background;

    @FXML
    DialogPane dialogPane = new DialogPane();

    @FXML
    Button returnButton;
    @FXML
    Button resetGameButton;
    @FXML
    Button pauseGameButton;
    @FXML
    Pane buttonPane;


    @FXML
    public void initialize(){

        displayBackground();
        initializeArray();
        displayPieces();
        addDragListeners();
    }

    private ImageView[][] imageViewArray;

    public void initializeArray() {
         imageViewArray = new ImageView[][]{
                {A8, B8, C8, D8, E8, F8, G8, H8},
                {A7, B7, C7, D7, E7, F7, G7, H7},
                {A6, B6, C6, D6, E6, F6, G6, H6},
                {A5, B5, C5, D5, E5, F5, G5, H5},
                {A4, B4, C4, D4, E4, F4, G4, H4},
                {A3, B3, C3, D3, E3, F3, G3, H3},
                {A2, B2, C2, D2, E2, F2, G2, H2},
                {A1, B1, C1, D1, E1, F1, G1, H1}
        };
    }

    /**
     * Displays the current state of the chessboard by setting the image of the ImageView according
     */
    public void displayPieces(){
        Piece[][] board = Board.getBoardInstance().getBoard();
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j] == null){
                    imageViewArray[i][j].setImage(null);
                }
                else{
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(board[i][j].getPath())));
                    imageViewArray[i][j].setImage(image);
                }

            }
        }
        LoggingUtility.getLogger().info("Displaying Pieces");
    }

    private void displayBackground(){
        Settings settings = Settings.getSettingsInstance();
        Background backgroundEnum = settings.getBackground();

        Image image = new Image(getClass().getResourceAsStream(backgroundEnum.getPath()));
        background.setImage(image);
        LoggingUtility.getLogger().info("Displaying Background Image");

        String startColor = backgroundEnum.getColor();
        String endColor = "black";
        buttonPane.setStyle(String.format("-fx-background-color: linear-gradient(to top,%s,%s);",startColor,endColor));
        LoggingUtility.getLogger().info("Applying Linear Gradient");
    }

    /**
     * Iterate over the ImageViewArray and set the dragListeners
     */
    public void addDragListeners(){
        for(int i = 0;i<imageViewArray.length;i++){
            for(int j = 0;j<imageViewArray[i].length;j++){
                ImageView im = imageViewArray[i][j];
                im.setOnDragDetected(event -> onDragDetected(im,event));
            }
        }
        LoggingUtility.getLogger().info("Registered Drag Listeners");
    }

    /**
     * Adds the image of the ImageView to the dragBoard
     * @param imageView the source of the drag and drop input
     * @param event the mouseevent that is being called
     */
    public void onDragDetected(ImageView imageView, MouseEvent event){
        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(imageView.getImage());
        dragboard.setContent(content);
        dragboard.setDragView(null);
        event.consume();
    }


    /**
     * Checks if the tile that is being hovered over can accept the image
     * @param event the dragEvent that is being called
     */
    public void OnDragOver(DragEvent event){
        if(event.getGestureSource() != event.getGestureTarget() && event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.getDragboard().setDragView(null);
        event.consume();
    }

    /**
     * Handles the event when an image is dropped onto the chessboard. If the game is over after the move it displays the game result
     * @param event the dragEvent being called
     */
    public void OnDragDropped(DragEvent event)  {
        Dragboard dragboard = event.getDragboard();
        event.getDragboard().setDragView(null);
        boolean success = false;
        Board b = Board.getBoardInstance();
        if(dragboard.hasImage()){
            ImageView target = (ImageView) event.getGestureTarget();
            target.setImage(dragboard.getImage());

            ImageView source = (ImageView) event.getGestureSource();

            Position currentPosition = Position.valueOf(source.getId());
            Position targetPosition = Position.valueOf(target.getId());
            Piece p = b.getBoard()[currentPosition.getRow()][currentPosition.getColumn()];

            new Move(p,currentPosition,targetPosition,p.getPieceColor());

                if(b.isGameOver()){
                    ImageView gameResultImageView = (ImageView) dialogPane.getContent();
                    if(b.isWhiteCheckmated()){
                        gameResultImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/game_result/white_checkmated.png"))));
                        LoggingUtility.getLogger().info("Game Over - White Checkmated");
                    }
                    else if(b.isBlackCheckmated()){
                        gameResultImageView.setImage(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/images/game_result/black_checkmated.png")))));
                        LoggingUtility.getLogger().info("Game Over - Black Checkmated");
                    }
                    else if(b.isDraw()){
                        gameResultImageView.setImage(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/images/game_result/draw.png")))));
                        LoggingUtility.getLogger().info("Game Over - Draw");
                    }
                    System.out.println("whattt");
                    dialogPane.setVisible(true);
                    System.out.println("should be visible!");
                    dialogPane.setExpanded(true);

                    Button closeButton = (Button) dialogPane.lookupButton(ButtonType.CLOSE);
                    closeButton.setOnAction(actionEvent -> dialogPane.setVisible(false));
                }
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
        displayPieces();
        LoggingUtility.getLogger().info("Move Sequence Completed");
    }


    @FXML
    public void onReturnButtonPressed(){
        try {
            FXMain.setMainMenuScene();
            LoggingUtility.getLogger().info("Returning to Main Menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void onResetGameButtonPressed(){
        Board.getBoardInstance().resetGame();
        displayPieces();
        LoggingUtility.getLogger().info("Reset Game State");
    }
    //TODO: implement
    @FXML
    public void onPauseGameButtonPressed(){
        System.out.println("Pause game button pressed!");
    }
}
