package view;

import gamefoundation.Board;
import gamefoundation.Move;
import gamefoundation.Piece;
import gamefoundation.Position;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.Objects;

public class Controller {
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




    public ImageView[][] imageBoard;


    public Controller(){
        imageBoard = new ImageView[][]{{A8, B8, C8, D8, E8, F8, G8, H8},
                {A7, B7, C7, D7, E7, F7, G7, H7},
                {A6, B6, C6, D6, E6, F6, G6, H6},
                {A5, B5, C5, D5, E5, F5, G5, H5},
                {A4, B4, C4, D4, E4, F4, G4, H4},
                {A3, B3, C3, D3, E3, F3, G3, H3},
                {A2, B2, C2, D2, E2, F2, G2, H2},
                {A1, B1, C1, D1, E1, F1, G1, H1}};
    }
    //this is a horrible solution
    public void displayPieces(){
        Piece[][] board = Board.getBoardInstance().getBoard();
        // Row A
        if (board[0][0] != null) {
            A8.setImage(new Image(getClass().getResourceAsStream(board[0][0].getPath())));
        } else {
            A8.setImage(null);
        }

        if (board[1][0] != null) {
            A7.setImage(new Image(getClass().getResourceAsStream(board[1][0].getPath())));
        } else {
            A7.setImage(null);
        }

        if (board[2][0] != null) {
            A6.setImage(new Image(getClass().getResourceAsStream(board[2][0].getPath())));
        } else {
            A6.setImage(null);
        }

        if (board[3][0] != null) {
            A5.setImage(new Image(getClass().getResourceAsStream(board[3][0].getPath())));
        } else {
            A5.setImage(null);
        }

        if (board[4][0] != null) {
            A4.setImage(new Image(getClass().getResourceAsStream(board[4][0].getPath())));
        } else {
            A4.setImage(null);
        }

        if (board[5][0] != null) {
            A3.setImage(new Image(getClass().getResourceAsStream(board[5][0].getPath())));
        } else {
            A3.setImage(null);
        }

        if (board[6][0] != null) {
            A2.setImage(new Image(getClass().getResourceAsStream(board[6][0].getPath())));
        } else {
            A2.setImage(null);
        }

        if (board[7][0] != null) {
            A1.setImage(new Image(getClass().getResourceAsStream(board[7][0].getPath())));
        } else {
            A1.setImage(null);
        }

// Row B
        if (board[0][1] != null) {
            B8.setImage(new Image(getClass().getResourceAsStream(board[0][1].getPath())));
        } else {
            B8.setImage(null);
        }

        if (board[1][1] != null) {
            B7.setImage(new Image(getClass().getResourceAsStream(board[1][1].getPath())));
        } else {
            B7.setImage(null);
        }

        if (board[2][1] != null) {
            B6.setImage(new Image(getClass().getResourceAsStream(board[2][1].getPath())));
        } else {
            B6.setImage(null);
        }
        // Row B
        if (board[3][1] != null) {
            B5.setImage(new Image(getClass().getResourceAsStream(board[3][1].getPath())));
        } else {
            B5.setImage(null);
        }

        if (board[4][1] != null) {
            B4.setImage(new Image(getClass().getResourceAsStream(board[4][1].getPath())));
        } else {
            B4.setImage(null);
        }

        if (board[5][1] != null) {
            B3.setImage(new Image(getClass().getResourceAsStream(board[5][1].getPath())));
        } else {
            B3.setImage(null);
        }

        if (board[6][1] != null) {
            B2.setImage(new Image(getClass().getResourceAsStream(board[6][1].getPath())));
        } else {
            B2.setImage(null);
        }

        if (board[7][1] != null) {
            B1.setImage(new Image(getClass().getResourceAsStream(board[7][1].getPath())));
        } else {
            B1.setImage(null);
        }

// Row C
        if (board[0][2] != null) {
            C8.setImage(new Image(getClass().getResourceAsStream(board[0][2].getPath())));
        } else {
            C8.setImage(null);
        }

        if (board[1][2] != null) {
            C7.setImage(new Image(getClass().getResourceAsStream(board[1][2].getPath())));
        } else {
            C7.setImage(null);
        }

        if (board[2][2] != null) {
            C6.setImage(new Image(getClass().getResourceAsStream(board[2][2].getPath())));
        } else {
            C6.setImage(null);
        }

        if (board[3][2] != null) {
            C5.setImage(new Image(getClass().getResourceAsStream(board[3][2].getPath())));
        } else {
            C5.setImage(null);
        }

        if (board[4][2] != null) {
            C4.setImage(new Image(getClass().getResourceAsStream(board[4][2].getPath())));
        } else {
            C4.setImage(null);
        }

        if (board[5][2] != null) {
            C3.setImage(new Image(getClass().getResourceAsStream(board[5][2].getPath())));
        } else {
            C3.setImage(null);
        }

        if (board[6][2] != null) {
            C2.setImage(new Image(getClass().getResourceAsStream(board[6][2].getPath())));
        } else {
            C2.setImage(null);
        }

        if (board[7][2] != null) {
            C1.setImage(new Image(getClass().getResourceAsStream(board[7][2].getPath())));
        } else {
            C1.setImage(null);
        }

// Row D
        if (board[0][3] != null) {
            D8.setImage(new Image(getClass().getResourceAsStream(board[0][3].getPath())));
        } else {
            D8.setImage(null);
        }

        if (board[1][3] != null) {
            D7.setImage(new Image(getClass().getResourceAsStream(board[1][3].getPath())));
        } else {
            D7.setImage(null);
        }

        if (board[2][3] != null) {
            D6.setImage(new Image(getClass().getResourceAsStream(board[2][3].getPath())));
        } else {
            D6.setImage(null);
        }

        if (board[3][3] != null) {
            D5.setImage(new Image(getClass().getResourceAsStream(board[3][3].getPath())));
        } else {
            D5.setImage(null);
        }

        if (board[4][3] != null) {
            D4.setImage(new Image(getClass().getResourceAsStream(board[4][3].getPath())));
        } else {
            D4.setImage(null);
        }

        if (board[5][3] != null) {
            D3.setImage(new Image(getClass().getResourceAsStream(board[5][3].getPath())));
        } else {
            D3.setImage(null);
        }

        if (board[6][3] != null) {
            D2.setImage(new Image(getClass().getResourceAsStream(board[6][3].getPath())));
        } else {
            D2.setImage(null);
        }

        // Row B
        if (board[3][1] != null) {
            B5.setImage(new Image(getClass().getResourceAsStream(board[3][1].getPath())));
        } else {
            B5.setImage(null);
        }

        if (board[4][1] != null) {
            B4.setImage(new Image(getClass().getResourceAsStream(board[4][1].getPath())));
        } else {
            B4.setImage(null);
        }

        if (board[5][1] != null) {
            B3.setImage(new Image(getClass().getResourceAsStream(board[5][1].getPath())));
        } else {
            B3.setImage(null);
        }

        if (board[6][1] != null) {
            B2.setImage(new Image(getClass().getResourceAsStream(board[6][1].getPath())));
        } else {
            B2.setImage(null);
        }

        if (board[7][1] != null) {
            B1.setImage(new Image(getClass().getResourceAsStream(board[7][1].getPath())));
        } else {
            B1.setImage(null);
        }

// Row C
        if (board[0][2] != null) {
            C8.setImage(new Image(getClass().getResourceAsStream(board[0][2].getPath())));
        } else {
            C8.setImage(null);
        }

        if (board[1][2] != null) {
            C7.setImage(new Image(getClass().getResourceAsStream(board[1][2].getPath())));
        } else {
            C7.setImage(null);
        }

        if (board[2][2] != null) {
            C6.setImage(new Image(getClass().getResourceAsStream(board[2][2].getPath())));
        } else {
            C6.setImage(null);
        }

        if (board[3][2] != null) {
            C5.setImage(new Image(getClass().getResourceAsStream(board[3][2].getPath())));
        } else {
            C5.setImage(null);
        }

        if (board[4][2] != null) {
            C4.setImage(new Image(getClass().getResourceAsStream(board[4][2].getPath())));
        } else {
            C4.setImage(null);
        }

        if (board[5][2] != null) {
            C3.setImage(new Image(getClass().getResourceAsStream(board[5][2].getPath())));
        } else {
            C3.setImage(null);
        }

        if (board[6][2] != null) {
            C2.setImage(new Image(getClass().getResourceAsStream(board[6][2].getPath())));
        } else {
            C2.setImage(null);
        }

        if (board[7][2] != null) {
            C1.setImage(new Image(getClass().getResourceAsStream(board[7][2].getPath())));
        } else {
            C1.setImage(null);
        }

// Row D
        if (board[0][3] != null) {
            D8.setImage(new Image(getClass().getResourceAsStream(board[0][3].getPath())));
        } else {
            D8.setImage(null);
        }

        if (board[1][3] != null) {
            D7.setImage(new Image(getClass().getResourceAsStream(board[1][3].getPath())));
        } else {
            D7.setImage(null);
        }

        if (board[2][3] != null) {
            D6.setImage(new Image(getClass().getResourceAsStream(board[2][3].getPath())));
        } else {
            D6.setImage(null);
        }

        if (board[3][3] != null) {
            D5.setImage(new Image(getClass().getResourceAsStream(board[3][3].getPath())));
        } else {
            D5.setImage(null);
        }

        if (board[4][3] != null) {
            D4.setImage(new Image(getClass().getResourceAsStream(board[4][3].getPath())));
        } else {
            D4.setImage(null);
        }

        if (board[5][3] != null) {
            D3.setImage(new Image(getClass().getResourceAsStream(board[5][3].getPath())));
        } else {
            D3.setImage(null);
        }

        if (board[6][3] != null) {
            D2.setImage(new Image(getClass().getResourceAsStream(board[6][3].getPath())));
        } else {
            D2.setImage(null);
        }

        if (board[7][3] != null) {
            D1.setImage(new Image(getClass().getResourceAsStream(board[7][3].getPath())));
        } else {
            D1.setImage(null);
        }

// Row E
        if (board[0][4] != null) {
            E8.setImage(new Image(getClass().getResourceAsStream(board[0][4].getPath())));
        } else {
            E8.setImage(null);
        }

        if (board[1][4] != null) {
            E7.setImage(new Image(getClass().getResourceAsStream(board[1][4].getPath())));
        } else {
            E7.setImage(null);
        }

        if (board[2][4] != null) {
            E6.setImage(new Image(getClass().getResourceAsStream(board[2][4].getPath())));
        } else {
            E6.setImage(null);
        }

        if (board[3][4] != null) {
            E5.setImage(new Image(getClass().getResourceAsStream(board[3][4].getPath())));
        } else {
            E5.setImage(null);
        }

        if (board[4][4] != null) {
            E4.setImage(new Image(getClass().getResourceAsStream(board[4][4].getPath())));
        } else {
            E4.setImage(null);
        }

        if (board[5][4] != null) {
            E3.setImage(new Image(getClass().getResourceAsStream(board[5][4].getPath())));
        } else {
            E3.setImage(null);
        }

        if (board[6][4] != null) {
            E2.setImage(new Image(getClass().getResourceAsStream(board[6][4].getPath())));
        } else {
            E2.setImage(null);
        }

        if (board[7][4] != null) {
            E1.setImage(new Image(getClass().getResourceAsStream(board[7][4].getPath())));
        } else {
            E1.setImage(null);
        }

// Row F
        if (board[0][5] != null) {
            F8.setImage(new Image(getClass().getResourceAsStream(board[0][5].getPath())));
        } else {
            F8.setImage(null);
        }

        if (board[1][5] != null) {
            F7.setImage(new Image(getClass().getResourceAsStream(board[1][5].getPath())));
        } else {
            F7.setImage(null);
        }

        if (board[2][5] != null) {
            F6.setImage(new Image(getClass().getResourceAsStream(board[2][5].getPath())));
        } else {
            F6.setImage(null);
        }

        if (board[3][5] != null) {
            F5.setImage(new Image(getClass().getResourceAsStream(board[3][5].getPath())));
        } else {
            F5.setImage(null);
        }

        if (board[4][5] != null) {
            F4.setImage(new Image(getClass().getResourceAsStream(board[4][5].getPath())));
        } else {
            F4.setImage(null);
        }

        if (board[5][5] != null) {
            F3.setImage(new Image(getClass().getResourceAsStream(board[5][5].getPath())));
        } else {
            F3.setImage(null);
        }

        if (board[6][5] != null) {
            F2.setImage(new Image(getClass().getResourceAsStream(board[6][5].getPath())));
        } else {
            F2.setImage(null);
        }

        if (board[7][5] != null) {
            F1.setImage(new Image(getClass().getResourceAsStream(board[7][5].getPath())));
        } else {
            F1.setImage(null);
        }

// Row G
        if (board[0][6] != null) {
            G8.setImage(new Image(getClass().getResourceAsStream(board[0][6].getPath())));
        } else {
            G8.setImage(null);
        }

        if (board[1][6] != null) {
            G7.setImage(new Image(getClass().getResourceAsStream(board[1][6].getPath())));
        } else {
            G7.setImage(null);
        }

        if (board[2][6] != null) {
            G6.setImage(new Image(getClass().getResourceAsStream(board[2][6].getPath())));
        } else {
            G6.setImage(null);
        }

        if (board[3][6] != null) {
            G5.setImage(new Image(getClass().getResourceAsStream(board[3][6].getPath())));
        } else {
            G5.setImage(null);
        }

        if (board[4][6] != null) {
            G4.setImage(new Image(getClass().getResourceAsStream(board[4][6].getPath())));
        } else {
            G4.setImage(null);
        }

        if (board[5][6] != null) {
            G3.setImage(new Image(getClass().getResourceAsStream(board[5][6].getPath())));
        } else {
            G3.setImage(null);
        }

        if (board[6][6] != null) {
            G2.setImage(new Image(getClass().getResourceAsStream(board[6][6].getPath())));
        } else {
            G2.setImage(null);
        }

        if (board[7][6] != null) {
            G1.setImage(new Image(getClass().getResourceAsStream(board[7][6].getPath())));
        } else {
            G1.setImage(null);
        }

// Row H
        if (board[0][7] != null) {
            H8.setImage(new Image(getClass().getResourceAsStream(board[0][7].getPath())));
        } else {
            H8.setImage(null);
        }

        if (board[1][7] != null) {
            H7.setImage(new Image(getClass().getResourceAsStream(board[1][7].getPath())));
        } else {
            H7.setImage(null);
        }

        if (board[2][7] != null) {
            H6.setImage(new Image(getClass().getResourceAsStream(board[2][7].getPath())));
        } else {
            H6.setImage(null);
        }

        if (board[3][7] != null) {
            H5.setImage(new Image(getClass().getResourceAsStream(board[3][7].getPath())));
        } else {
            H5.setImage(null);
        }

        if (board[4][7] != null) {
            H4.setImage(new Image(getClass().getResourceAsStream(board[4][7].getPath())));
        } else {
            H4.setImage(null);
        }

        if (board[5][7] != null) {
            H3.setImage(new Image(getClass().getResourceAsStream(board[5][7].getPath())));
        } else {
            H3.setImage(null);
        }

        if (board[6][7] != null) {
            H2.setImage(new Image(getClass().getResourceAsStream(board[6][7].getPath())));
        } else {
            H2.setImage(null);
        }

        if (board[7][7] != null) {
            H1.setImage(new Image(getClass().getResourceAsStream(board[7][7].getPath())));
        } else {
            H1.setImage(null);
        }



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


    /**
     * Iterate over board array and set images of the ImageViews accordingly
     */
    public void setImages(){
        Piece[][] board = Board.getBoardInstance().getBoard();
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j<board[i].length; j++){
                if(board[i][j] == null){
                }
                else if(board[i][j] != null){
                    Image image = new Image(getClass().getResourceAsStream(board[i][j].getPath()));
                    imageBoard[i][j].setImage(image);
                }
                int finalI = i;
                int finalJ = j;
                imageBoard[i][j].setOnDragDetected(event -> onDragDetected(imageBoard[finalI][finalJ],event));
            }
        }
    }

//    public void setDragListeners(){
//        A8.setOnDragDetected(event -> onDragDetected(A8,event));
//        for(int i = 0; i<imageBoard.length; i++){
//            for(int j = 0; j<imageBoard[i].length; j++){
//                int finalI = i;
//                int finalJ = j;
//                imageBoard[i][j].setOnDragDetected(event -> onDragDetected(imageBoard[finalI][finalJ],event));
//            }
//        }
//    }


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

    public void OnDragDropped(DragEvent event) throws InterruptedException {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        if(dragboard.hasImage()){
            ImageView target = (ImageView) event.getGestureTarget();
            target.setImage(dragboard.getImage());

            ImageView source = (ImageView) event.getGestureSource();


            Position currentPosition = getPositionFromImageView(source);
            Position targetPosition = getPositionFromImageView(target);
            Piece p = Board.getBoardInstance().getBoard()[currentPosition.getRow()][currentPosition.getColumn()];

            if(FXMain.puzzleGamemode != null){
                Move triedMove = new Move(p,currentPosition,targetPosition,false);
                if(triedMove.equals(FXMain.getInstance().puzzleGamemode.getSolution())){
                    FXMain.puzzleGamemode.puzzleReady();
                    System.out.println("correct move!");
                    displayPieces();
                }
            }
            else{
                new Move(p,currentPosition,targetPosition,p.getPieceColor());
            }

            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
        displayPieces();
    }

    public Position getPositionFromImageView(ImageView imageView){

        return switch (imageView.getId()) {
            case "A8" -> Position.A8;
            case "B8" -> Position.B8;
            case "C8" -> Position.C8;
            case "D8" -> Position.D8;
            case "E8" -> Position.E8;
            case "F8" -> Position.F8;
            case "G8" -> Position.G8;
            case "H8" -> Position.H8;
            case "A7" -> Position.A7;
            case "B7" -> Position.B7;
            case "C7" -> Position.C7;
            case "D7" -> Position.D7;
            case "E7" -> Position.E7;
            case "F7" -> Position.F7;
            case "G7" -> Position.G7;
            case "H7" -> Position.H7;
            case "A6" -> Position.A6;
            case "B6" -> Position.B6;
            case "C6" -> Position.C6;
            case "D6" -> Position.D6;
            case "E6" -> Position.E6;
            case "F6" -> Position.F6;
            case "G6" -> Position.G6;
            case "A5" -> Position.A5;
            case "B5" -> Position.B5;
            case "C5" -> Position.C5;
            case "D5" -> Position.D5;
            case "E5" -> Position.E5;
            case "F5" -> Position.F5;
            case "G5" -> Position.G5;
            case "H5" -> Position.H5;
            case "A4" -> Position.A4;
            case "B4" -> Position.B4;
            case "C4" -> Position.C4;
            case "D4" -> Position.D4;
            case "E4" -> Position.E4;
            case "F4" -> Position.F4;
            case "G4" -> Position.G4;
            case "H4" -> Position.H4;
            case "A3" -> Position.A3;
            case "B3" -> Position.B3;
            case "C3" -> Position.C3;
            case "D3" -> Position.D3;
            case "E3" -> Position.E3;
            case "F3" -> Position.F3;
            case "G3" -> Position.G3;
            case "H3" -> Position.H3;
            case "A2" -> Position.A2;
            case "B2" -> Position.B2;
            case "C2" -> Position.C2;
            case "D2" -> Position.D2;
            case "E2" -> Position.E2;
            case "F2" -> Position.F2;
            case "G2" -> Position.G2;
            case "H2" -> Position.H2;
            case "A1" -> Position.A1;
            case "B1" -> Position.B1;
            case "C1" -> Position.C1;
            case "D1" -> Position.D1;
            case "E1" -> Position.E1;
            case "F1" -> Position.F1;
            case "G1" -> Position.G1;
            case "H1" -> Position.H1;
            default -> null;
        };

    }




}
