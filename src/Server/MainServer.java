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

        System.out.println("Сервер ожидает сообщение");
        String word = transfer.Get();
        System.out.println(word);
        transfer.Send("Ne " + word + "-" + word +  " mne tut");


        clientSocket.close();
        transfer.close();
        server.close();
    }
}
