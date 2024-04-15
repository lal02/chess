package multiplayer;

import gamefoundation.Move;
import puzzle.StringParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChessClient {
    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    public ChessClient(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost",8888);
                System.out.println("client connected to server");

                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendMove(Move move) throws IOException {
        StringParser parser = new StringParser();
        String msg = parser.getStringFromMove(move);

        out.writeUTF(msg);
//        System.out.println(msg);
    }

    public void receiveMove(Move move) throws IOException {
        StringParser parser = new StringParser();
        String msg = in.readUTF();
        System.out.println(msg);
        Move receivedMove = parser.getMoveFromString(msg);
        new Move(receivedMove.getPiece(),receivedMove.getCurrentPosition(),receivedMove.getTargetPosition(),receivedMove.getPiece().getPieceColor());
    }
}
