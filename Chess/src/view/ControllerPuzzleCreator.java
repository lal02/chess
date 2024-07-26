package view;

import gamefoundation.Board;
import gamefoundation.Piece;
import gamefoundation.Position;
import puzzle.StringParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import puzzle.DatabaseConnection;

public class ControllerPuzzleCreator{
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
    ImageView selectBlackKing = new ImageView();
    @FXML
    ImageView selectBlackRook = new ImageView();
    @FXML
    ImageView selectBlackKnight = new ImageView();
    @FXML
    ImageView selectBlackBishop = new ImageView();
    @FXML
    ImageView selectBlackPawn = new ImageView();
    @FXML
    ImageView selectBlackQueen = new ImageView();
    @FXML
    ImageView selectWhiteKing = new ImageView();
    @FXML
    ImageView selectWhiteRook = new ImageView();
    @FXML
    ImageView selectWhiteKnight = new ImageView();
    @FXML
    ImageView selectWhiteBishop = new ImageView();
    @FXML
    ImageView selectWhitePawn = new ImageView();
    @FXML
    ImageView selectWhiteQueen = new ImageView();

    @FXML
    TextField boardTextField = new TextField();
    @FXML
    Button copyLoadBoardButton = new Button();

    @FXML
    TextField solutionTextField = new TextField();
    @FXML
    Button recordMoveButton = new Button();

    @FXML
    Button saveToDBButton = new Button();
    @FXML
    Label statusLabel = new Label();

    @FXML
    ImageView deleteImageView = new ImageView();

    @FXML
    Button resetButton = new Button();

    private ImageView[][] imageViewArray;
    private Piece[][] board = new Piece[8][8];
    private boolean recordMove = false;

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

    @FXML
    public void initialize(){
        initializeArray();
        addDragListeners();
    }

    /**
     * Handles the event when a drag is being dropped
     * If recordMove is true it creates a moveString from the source target and piece
     * If source is SideBoard it sets the image of the target and updates the board and boardString
     * If target is deleteImageView source gets set to null and board and boardString gets updated
     * @param event the drag event being handled
     */
    public void OnDragDropped(DragEvent event){
        Dragboard dragboard = event.getDragboard();
        ImageView target = (ImageView) event.getGestureTarget();
        ImageView source =(ImageView) event.getGestureSource();
        StringParser parser = new StringParser();
        if(recordMove){
            Position sourcePosition = Position.valueOf(source.getId());
            Position targetPosition = Position.valueOf(target.getId());
            Piece p = board[sourcePosition.getRow()][sourcePosition.getColumn()];
            String piece = parser.getStringFromPiece(p);
            String recordedMove = "";
            recordedMove += piece;
            recordedMove += sourcePosition;
            recordedMove += "" + targetPosition;
            solutionTextField.setText(recordedMove);
            recordMove = false;

        }
        else if(isSideboard(source)){
            target.setImage(dragboard.getImage());
            event.setDropCompleted(true);
            event.consume();
            setPiece(source,target,null);
            updateBoardTextField();
        }
        else if(target == deleteImageView){
            source.setImage(null);
            event.setDropCompleted(true);
            event.consume();
            Position p = Position.valueOf(source.getId());
            int row = p.getRow();
            int column = p.getColumn();
            board[row][column] = null;
            updateBoardTextField();
        }
        else{
            int row = Position.valueOf(source.getId()).getRow();
            int column = Position.valueOf(source.getId()).getColumn();
            Piece p = board[row][column];
            setPiece(source,target,p);
            target.setImage(dragboard.getImage());
            event.setDropCompleted(true);
            event.consume();
            updateBoardTextField();
        }


    }


    /**
     * Checks if an ImageView is part of the SideBoard
     * @param imageView the imageView to be checked
     * @return true if the imageView is part of the sideBoard
     */
    private boolean isSideboard(ImageView imageView){
        return switch (imageView.getId()) {
            case "selectBlackKing", "selectBlackPawn", "selectWhiteQueen", "selectWhitePawn", "selectWhiteKnight",
                 "selectWhiteBishop", "selectWhiteRook", "selectBlackRook", "selectBlackQueen", "selectWhiteKing",
                 "selectBlackKnight", "selectBlackBishop" -> true;
            default -> false;
        };
    }

    //TODO does it really get moved or copied?
    /**
     * Handles the event of a drag detected. If the source is part of the sideBoard it gets copied else it gets moved
     * @param imageView
     * @param event
     */
    public void onDragDetected(ImageView imageView, MouseEvent event){
        Dragboard dragboard;
        if(isSideboard(imageView)){
            dragboard = imageView.startDragAndDrop(TransferMode.COPY);
        }
        else{
            dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
        }
        ClipboardContent content = new ClipboardContent();
        content.putImage(imageView.getImage());
        dragboard.setContent(content);
        event.consume();
    }


    /**
     * Handles the event of a dragging over
     * @param event
     */
    public void OnDragOver(DragEvent event){
        if(event.getGestureSource() != event.getGestureTarget() && event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    /**
     * adds the onDragDetected Listener for all the imageViews
     */
    public void addDragListeners(){
        for(int i = 0;i<imageViewArray.length;i++){
            for(int j = 0;j<imageViewArray[i].length;j++){
                ImageView im = imageViewArray[i][j];
                im.setOnDragDetected(event -> onDragDetected(im,event));
            }
        }
        ImageView[] arr = new ImageView[]{selectBlackKing,selectBlackQueen, selectBlackKnight, selectBlackBishop,
                selectBlackPawn, selectBlackRook,selectWhiteKing, selectWhiteBishop, selectWhiteKnight, selectWhitePawn,
                selectWhiteQueen, selectWhiteRook};

        for(int i = 0; i<arr.length; i++){
            ImageView im = arr[i];
            im.setOnDragDetected(event -> onDragDetected(im,event));
        }
    }

    /**
     * Updates the boardTextField with the current state of the board represented as String
     */
    private void updateBoardTextField(){
    StringBuilder sb = new StringBuilder();
    StringParser parser = new StringParser();
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                Piece p = board[i][j];
                sb.append(parser.getStringFromPiece(p));
            }
        }
        boardTextField.setText(sb.toString());
    }


    /**
     * Loads a board state from a boardString
     */
    public void onActionLoadBoardButton(){
        String boardString = boardTextField.getText();
        StringParser parser = new StringParser();
        Piece[][] arr = parser.getBoardFromString(boardString);
        board = Board.getBoardInstance().cloneBoard(arr);
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j] == null){
                    imageViewArray[i][j].setImage(null);
                }
                else{
                    imageViewArray[i][j].setImage(new Image(getClass().getResourceAsStream(board[i][j].getPath())));
                }
            }
        }
    }

    public void onActionRecordMoveButton(){
        recordMove = true;
    }

    /**
     * Saves the recorded puzzle to the database using the boardTextField and solutionTextField Text
     */
    public void onActionSaveToDBButton(){
        DatabaseConnection db = new DatabaseConnection();
        if(db.insertPuzzle(boardTextField.getText(),solutionTextField.getText())){
            statusLabel.setText("Saved!");
        }
        else{
            statusLabel.setText("Invalid Input!");
        }
        statusLabel.setVisible(true);
    }

    /**
     * Resets the puzzleCreator
     */
    public void onActionResetButton(){
        for(int i = 0; i<imageViewArray.length;i++){
            for(int j = 0; j<imageViewArray[i].length;j++){
                imageViewArray[i][j].setImage(null);
            }
        }
        board = new Piece[8][8];
        boardTextField.setText("");
        solutionTextField.setText("");
        statusLabel.setVisible(false);
    }

    /**
     * Sets a piece on the boardArray after a drag and drop event is done
     * @param source the source of the event
     * @param target the target of the event
     * @param p the piece to be inserted on the board
     */
    private void setPiece(ImageView source,ImageView target,Piece p){
        String id = source.getId();
        int row = -1;
        int column = -1;
        String targetId = target.getId();
        row = Position.valueOf(targetId).getRow();
        column = Position.valueOf(targetId).getColumn();

        if(p!=null){
            board[row][column] = p;
            return;
        }
        switch(id){
            case "selectWhiteKing": board[row][column] = Piece.whiteKing; break;
            case "selectWhiteRook":board[row][column] = Piece.whiteRook; break;
            case "selectWhitePawn":board[row][column] = Piece.whitePawn; break;
            case "selectWhiteKnight":board[row][column] = Piece.whiteKnight; break;
            case "selectWhiteQueen":board[row][column] = Piece.whiteQueen; break;
            case "selectWhiteBishop":board[row][column] = Piece.whiteBishop; break;
            case "selectBlackKing":board[row][column] = Piece.blackKing; break;
            case "selectBlackRook":board[row][column] = Piece.blackRook; break;
            case "selectBlackPawn":board[row][column] = Piece.blackPawn; break;
            case "selectBlackKnight":board[row][column] = Piece.blackKnight; break;
            case "selectBlackQueen":board[row][column] = Piece.blackQueen; break;
            case "selectBlackBishop":board[row][column] = Piece.blackBishop; break;
            default: board[row][column] = null;
        }
    }
}
