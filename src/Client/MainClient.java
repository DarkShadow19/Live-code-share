package Client;
import WorkWithFile.FileManager;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class MainClient {
    static final String EOF = "435098234037";
    private static FileManager manager = new FileManager();;
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket clientSocket = new Socket("localhost", 6006);
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        String files = transfer.Get();
        System.out.println(files);
        CheckFile(files);

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

    private static void CheckFile(String files) {
//    private static void CheckFile() {
        Set<String> localFiles = manager.GetKeys();
        System.out.println(localFiles);
        String external = files.substring(1, files.length()-1);
        Set<String> exte = new HashSet<>(Arrays.asList(external.split(", ")));

        System.out.println(external);
    }
}
