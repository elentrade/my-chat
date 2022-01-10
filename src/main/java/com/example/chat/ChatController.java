package com.example.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    public TextArea txt_area;
    @FXML
    public TextField txt_field;
    @FXML
    private Label welcomeText;
    @FXML

    private final String IP_ADRESS = "localhost";
    private final int PORT = 8189;
    private Socket socket;
    DataInputStream in;
    DataOutputStream out;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //content of this block will be done after drawing all graphics elements
        try {
            socket = new Socket(IP_ADRESS, PORT);
            //make socket at client`s side
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String str = in.readUTF();
                            System.out.println("Client get from server "+str);
                        if (str.equals("Echo: end")) {
                            break;
                        }
                        txt_area.appendText(str+"\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finally {
                            try {
                                socket.close();
                                System.out.println("Client is disconnected from server");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(ActionEvent actionEvent) {
        try {
            out.writeUTF(txt_field.getText());
            txt_field.clear();
            txt_field.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}