package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MainServer {
    public static final int PORT = 6006;
    public static LinkedList<ServerSomething> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomething(socket));
                } catch (IOException _) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }


//        BufferedReader fileRead = new BufferedReader(new FileReader("/home/nlx/source.txt"))
    }
}
