package com.example.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {
    final static int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;
    List <ClientHandler> clients;
    public  Server()  {
clients = new Vector<>();
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server is connected");
            while (true){
                socket = server.accept();
                System.out.println("Client is connected");
                clients.add(new ClientHandler(this,socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//finally {
//            try {
//                server.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
