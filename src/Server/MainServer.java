package Server;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    static final String EOFWORDCODE = "Feni to la";
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6006);
        Socket clientSocket = server.accept();
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        BufferedReader fileRead = new BufferedReader(new FileReader("/home/nlx/source.txt"));

        SendFile(transfer, fileRead);
        fileRead.close();

        System.out.println("Сервер ожидает сообщение");
        String word = transfer.Get();
        System.out.println(word);
        transfer.Send("Ne " + word + "-" + word +  " mne tut");


        clientSocket.close();
        transfer.close();
        server.close();
    }

    private static void SendFile(Transfer transfer, BufferedReader file) throws IOException {
        while(file.ready()) {
            String line = file.readLine();
            System.out.println(line);
            transfer.Send(line);
        }
        transfer.Send(EOFWORDCODE);
    }
}
