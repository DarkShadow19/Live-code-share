package Server;

import WorkWithFile.FileManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class MainServer {
    public static final int PORT = 6006;
    public static LinkedList<ServerSomething> serverList = new LinkedList<>();
    public static FileManager manager = new FileManager();

    public static void main(String[] args) throws IOException {
        Initial();
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomething(socket));
                } catch (IOException e) {
                    socket.close();
                    e.printStackTrace();
                }
            }
        } finally {
            server.close();
        }

    }

    private static void Initial() throws IOException {
        manager.Add("source.txt", new String(Files.readAllBytes(Paths.get("/home/nlx/source.txt"))));
        manager.Add("code.txt", new String(Files.readAllBytes(Paths.get("/home/nlx/code.txt"))));
    }
}
