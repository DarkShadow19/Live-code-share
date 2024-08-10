package Client;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 6006);
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        System.out.println("Привет, напиши текст!");
        String word = transfer.GetTerminal();
        transfer.Send(word);
        String mess = transfer.Get();
        System.out.println("message from server: " + mess);

        clientSocket.close();
        transfer.close();
    } 
}
