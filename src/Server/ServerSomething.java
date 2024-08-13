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
            String command = transfer.Get();
            if ("getFiles:".equalsIgnoreCase(command))
                sendFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            transfer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void SendFiles(BufferedReader file) throws IOException {
        while(file.ready()) {
            String line = file.readLine();
            System.out.println(line);
            transfer.Send(line);
        }
        transfer.Send(EOF);
    }

    private void sendFile() throws IOException {
        while (true) {
            String file = transfer.Get();
            if ("getFiles: end".equalsIgnoreCase(file))
                    break;
            transfer.Send(MainServer.manager.Get(file));
        }
    }
}
