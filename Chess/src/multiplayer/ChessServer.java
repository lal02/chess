package multiplayer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {

    private ServerSocket socket;
    private static ChessServer instance;
    private int connections = 0;
    public Socket client1;
    public Socket client2;

    public static ChessServer getInstance(){
        if(instance == null){
            instance = new ChessServer(8888);
        }
        return instance;
    }

    private ChessServer(int port){
        if(!isServerRunning()){
            startServer();
        }
    }


    public static boolean isServerRunning() {
        try (ServerSocket socket = new ServerSocket(8888)) {
            // If the port is available, it means the server is not running
            return false;
        } catch (IOException e) {
            // If the port is already in use, it means the server is running
            return true;
        }
    }

    private void startServer(){
        try {
            socket = new ServerSocket(8888);
            acceptClients();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptClients(){
            while(connections<2){
                try{
                    Socket clientsocket = socket.accept();
                    if(client1==null){
                        client1 = clientsocket;
                        connections++;
                        System.out.println("connection established with client from " + client1.getInetAddress()  + " as client1" );
                    }
                    else if(client2==null && client1!= client2){
                        client2 = clientsocket;
                        connections++;
                        System.out.println("connection established with client from " + client2.getInetAddress() + " as client2");
                    }
                    else{
                        break;
                    }
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }

    }

    public void forwardMove(String msg,int clientTarget) throws IOException {
        if(clientTarget == 1){
            DataOutputStream toClient1 = new DataOutputStream(client1.getOutputStream());
            toClient1.writeUTF(msg);
            System.out.println("server " + msg + " forwarded from client 2 to client 1");
        }
        else if(clientTarget == 2){
            DataOutputStream toClient2 = new DataOutputStream(client2.getOutputStream());
            System.out.println("server " + msg + " forwarded from client 1 to client 2");
            toClient2.writeUTF(msg);
        }

    }
}
