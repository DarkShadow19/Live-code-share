package Client;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 6006);
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        transfer.Send("Привет, напиши что-нибудь: ");
        transfer.Get();

        clientSocket.close();
        transfer.close();
    } 
}
