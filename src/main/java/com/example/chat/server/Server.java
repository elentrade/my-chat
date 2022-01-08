package com.example.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    final static int PORT = 8189;
    public static void main(String[] args) {


        try(ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server is connected");
            try (Socket socket = server.accept()){
                System.out.println("Client is connected");
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String str = in.readUTF();
                    if (str.equals("end")) {
                        System.out.println("Client is disconnected");
                        break;
                    }
                    System.out.println("Client: " + str);
                    out.writeUTF("Echo: " + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
