package Client;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.Socket;

public class MainClient {
    static final String EOF = "Feni to la";
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 6006);
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());





        clientSocket.close();
        transfer.close();
    }

    private static void GetFileFromNetwork(Transfer transfer) throws IOException {
        String line;
        while(true) {
            line = transfer.Get();
            if (line.equalsIgnoreCase(EOF))
                break;
            System.out.println(line);
        }
    }
}
