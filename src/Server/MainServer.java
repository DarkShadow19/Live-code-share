package Server;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6006);
        Socket clientSocket = server.accept();
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        String word = transfer.Get();
        transfer.Send("Привет, это Сервер! Подтверждаю, вы наисали : " + word + "\n");


        clientSocket.close();
        transfer.close();
    }
}
