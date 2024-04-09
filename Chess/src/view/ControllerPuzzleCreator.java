package view;

import gamefoundation.Board;
import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.Position;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

public class ControllerPuzzleCreator {

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



    public void OnDragDropped(DragEvent event) throws InterruptedException {
//        Dragboard dragboard = event.getDragboard();
//        boolean success = false;
//
//        if(dragboard.hasImage()){
//            ImageView target = (ImageView) event.getGestureTarget();
//            target.setImage(dragboard.getImage());
//
//            ImageView source = (ImageView) event.getGestureSource();
//
//
//            //Position currentPosition = getPositionFromImageView(source);
//            //Position targetPosition = getPositionFromImageView(target);
//            //Piece p = Board.getBoardInstance().getBoard()[currentPosition.getRow()][currentPosition.getColumn()];
//
//            if(ControllerMainMenu.puzzleGamemode != null){
//               // Move triedMove = new Move(p,currentPosition,targetPosition,false);
////                if(nullequals(ControllerMainMenu.puzzleGamemode.getSolution())){
////                    ControllerMainMenu.puzzleGamemode.puzzleReady();
////                    System.out.println("correct move!");
////                    //Board.getBoardInstance().updateBoard(triedMove,Board.getBoardInstance().getBoard());
////                    //displayPieces();
////                }
////                else{
////                    System.out.println("incorrect move");
////                }
//            }
//            else{
//               // new Move(p,currentPosition,targetPosition,p.getPieceColor());
//            }
//
//            success = true;
//        }
//        event.setDropCompleted(success);
//        event.consume();
//        //displayPieces();
        System.out.println("hier kann nichts hingedragged werden");
    }

    public void onDragDetected(ImageView imageView, MouseEvent event){
        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(imageView.getImage());
        dragboard.setContent(content);
        event.consume();
    }



    public void OnDragOver(DragEvent event){
        if(event.getGestureSource() != event.getGestureTarget() && event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }


    public void addDragListeners(){
        A8.setOnDragDetected(event -> onDragDetected(A8, event));
        B8.setOnDragDetected(event -> onDragDetected(B8, event));
        C8.setOnDragDetected(event -> onDragDetected(C8, event));
        D8.setOnDragDetected(event -> onDragDetected(D8, event));
        E8.setOnDragDetected(event -> onDragDetected(E8, event));
        F8.setOnDragDetected(event -> onDragDetected(F8, event));
        G8.setOnDragDetected(event -> onDragDetected(G8, event));
        H8.setOnDragDetected(event -> onDragDetected(H8, event));

        A7.setOnDragDetected(event -> onDragDetected(A7, event));
        B7.setOnDragDetected(event -> onDragDetected(B7, event));
        C7.setOnDragDetected(event -> onDragDetected(C7, event));
        D7.setOnDragDetected(event -> onDragDetected(D7, event));
        E7.setOnDragDetected(event -> onDragDetected(E7, event));
        F7.setOnDragDetected(event -> onDragDetected(F7, event));
        G7.setOnDragDetected(event -> onDragDetected(G7, event));
        H7.setOnDragDetected(event -> onDragDetected(H7, event));

        A6.setOnDragDetected(event -> onDragDetected(A6, event));
        B6.setOnDragDetected(event -> onDragDetected(B6, event));
        C6.setOnDragDetected(event -> onDragDetected(C6, event));
        D6.setOnDragDetected(event -> onDragDetected(D6, event));
        E6.setOnDragDetected(event -> onDragDetected(E6, event));
        F6.setOnDragDetected(event -> onDragDetected(F6, event));
        G6.setOnDragDetected(event -> onDragDetected(G6, event));
        H6.setOnDragDetected(event -> onDragDetected(H6, event));

        A5.setOnDragDetected(event -> onDragDetected(A5, event));
        B5.setOnDragDetected(event -> onDragDetected(B5, event));
        C5.setOnDragDetected(event -> onDragDetected(C5, event));
        D5.setOnDragDetected(event -> onDragDetected(D5, event));
        E5.setOnDragDetected(event -> onDragDetected(E5, event));
        F5.setOnDragDetected(event -> onDragDetected(F5, event));
        G5.setOnDragDetected(event -> onDragDetected(G5, event));
        H5.setOnDragDetected(event -> onDragDetected(H5, event));

        A4.setOnDragDetected(event -> onDragDetected(A4, event));
        B4.setOnDragDetected(event -> onDragDetected(B4, event));
        C4.setOnDragDetected(event -> onDragDetected(C4, event));
        D4.setOnDragDetected(event -> onDragDetected(D4, event));
        E4.setOnDragDetected(event -> onDragDetected(E4, event));
        F4.setOnDragDetected(event -> onDragDetected(F4, event));
        G4.setOnDragDetected(event -> onDragDetected(G4, event));
        H4.setOnDragDetected(event -> onDragDetected(H4, event));

        A3.setOnDragDetected(event -> onDragDetected(A3, event));
        B3.setOnDragDetected(event -> onDragDetected(B3, event));
        C3.setOnDragDetected(event -> onDragDetected(C3, event));
        D3.setOnDragDetected(event -> onDragDetected(D3, event));
        E3.setOnDragDetected(event -> onDragDetected(E3, event));
        F3.setOnDragDetected(event -> onDragDetected(F3, event));
        G3.setOnDragDetected(event -> onDragDetected(G3, event));
        H3.setOnDragDetected(event -> onDragDetected(H3, event));

        A2.setOnDragDetected(event -> onDragDetected(A2, event));
        B2.setOnDragDetected(event -> onDragDetected(B2, event));
        C2.setOnDragDetected(event -> onDragDetected(C2, event));
        D2.setOnDragDetected(event -> onDragDetected(D2, event));
        E2.setOnDragDetected(event -> onDragDetected(E2, event));
        F2.setOnDragDetected(event -> onDragDetected(F2, event));
        G2.setOnDragDetected(event -> onDragDetected(G2, event));
        H2.setOnDragDetected(event -> onDragDetected(H2, event));

        A1.setOnDragDetected(event -> onDragDetected(A1, event));
        B1.setOnDragDetected(event -> onDragDetected(B1, event));
        C1.setOnDragDetected(event -> onDragDetected(C1, event));
        D1.setOnDragDetected(event -> onDragDetected(D1, event));
        E1.setOnDragDetected(event -> onDragDetected(E1, event));
        F1.setOnDragDetected(event -> onDragDetected(F1, event));
        G1.setOnDragDetected(event -> onDragDetected(G1, event));
        H1.setOnDragDetected(event -> onDragDetected(H1, event));
    }



}
