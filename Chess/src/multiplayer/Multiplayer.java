package multiplayer;

import gamefoundation.Board;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Multiplayer extends Thread {
    ChessServer server;
    public Multiplayer(){
         server = ChessServer.getInstance();

         this.start();
    }

    @Override
    public void run(){
        try{


        Socket client1 = server.client1;
        Socket client2 = server.client2;

        while(client1 ==null || client2==null){
            Thread.sleep(100);
        }


            DataOutputStream out1;
            DataOutputStream out2;
            DataInputStream in1 = null;
            DataInputStream in2 = null;

            try{
                out1 = new DataOutputStream(client1.getOutputStream());
                out2 = new DataOutputStream(client2.getOutputStream());

                in1 = new DataInputStream(client1.getInputStream());
                in2 = new DataInputStream(client2.getInputStream());
            }
            catch(IOException e){
                e.printStackTrace();
            }

            while(!Board.getBoardInstance().isGameOver()){
                try {
                    String receivedClient1 = in1.readUTF();
                    String receivedClient2 = in2.readUTF();

                    if(!receivedClient1.isEmpty()){
                        server.forwardMove(receivedClient1,2);
                    }
                    else if(!receivedClient2.isEmpty()){
                        server.forwardMove(receivedClient2,1);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
