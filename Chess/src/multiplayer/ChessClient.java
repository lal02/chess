package multiplayer;

import gamefoundation.Move;
import javafx.fxml.FXMLLoader;
import puzzle.StringParser;
import view.ControllerMultiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChessClient {
    Socket socket;
    DataOutputStream out;
    DataInputStream  in;

    public ChessClient(){
        new Thread(() -> {
            try {
                FXMLLoader mpLoader = new FXMLLoader(getClass().getResource("/resources/fxml/multiplayer.fxml"));
                ControllerMultiplayer controller = mpLoader.getController();

                socket = new Socket("localhost",8888);
                System.out.println("client connected to server");

                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                while(true){
                    if(receiveMove() && controller!=null){
                        controller.displayPieces();
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendMove(Move move) throws IOException {
        StringParser parser = new StringParser();
        String msg = parser.getStringFromMove(move);
        out.writeUTF(msg);
    }

    private boolean receiveMove() throws IOException {
        StringParser parser = new StringParser();
        String msg = in.readUTF();
        System.out.println(msg);
        Move receivedMove = parser.getMoveFromString(msg);
        if(receivedMove!=null){
            new Move(receivedMove.getPiece(),receivedMove.getCurrentPosition(),receivedMove.getTargetPosition(),receivedMove.getPiece().getPieceColor());
            return true;
        }
        return false;
    }

}
