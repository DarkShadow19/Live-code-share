package Server;

import simpleNetwork.Transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ServerSomething extends Thread {
    static final String EOF = "435098234037";
    private final Transfer transfer;

    public ServerSomething(Socket socket) throws IOException {
        transfer = new Transfer(socket.getInputStream(), socket.getOutputStream());
        start();
    }

    @Override
    public void run() {
        try {
            transfer.Send(MainServer.manager.GetKeys().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            transfer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void SendFile(Transfer transfer, BufferedReader file) throws IOException {
        while(file.ready()) {
            String line = file.readLine();
            System.out.println(line);
            transfer.Send(line);
        }
        transfer.Send(EOF);
    }
}
