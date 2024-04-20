package multiplayer;

import gamefoundation.Board;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Multiplayer extends Thread {


    public static void main(String[] args) {
        new Multiplayer();
    }

    ChessServer server;

    public Multiplayer() {
        server = ChessServer.getInstance();
        this.start();
    }

    @Override
    public void run() {
        try {
            Socket client1 = server.client1;
            Socket client2 = server.client2;

            while (client1 == null || client2 == null) {
//            Thread.sleep(100);
            }


            new Thread(() -> {
                try {
                    DataInputStream inp1 = new DataInputStream(client1.getInputStream());
                    while (!Board.getBoardInstance().isGameOver()) {
                        String receivedClient1 = inp1.readUTF();
                        System.out.println("Received message from client 1: " + receivedClient1);
                        server.forwardMove(receivedClient1, 2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    DataInputStream inp2 = new DataInputStream(client2.getInputStream());
                    while (!Board.getBoardInstance().isGameOver()) {
                        String receivedClient2 = inp2.readUTF();
                        System.out.println("Received message from client 2: " + receivedClient2);
                        server.forwardMove(receivedClient2, 1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
