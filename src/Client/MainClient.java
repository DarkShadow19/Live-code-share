package Client;
import WorkWithFile.FileManager;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainClient {
    static final String EOF = "435098234037";
    private static final FileManager manager = new FileManager();
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 6006);
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        String files = transfer.Get();
        System.out.println("На свервере есть: " + files);
        Set<String> newFile = CheckFile(files);
        downloadFile(newFile, transfer);

        readManager(manager);

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

    private static Set<String> CheckFile(String files) {
        Set<String> localFiles = manager.GetKeys();
        System.out.println("На кленте храняться файлы: " + localFiles);
        String external = files.substring(1, files.length()-1);
        Set<String> externalFile = new HashSet<>(Arrays.asList(external.split(", ")));  //Список полученный с сервера

        externalFile.removeAll(localFiles);     //вычитаем из него файлы, которые есть на клиенте
        return externalFile;
    }

    private static void downloadFile(Set<String> files, Transfer transfer) throws IOException {
        transfer.Send("getFiles:");
        for (String name : files) {
            try {
                transfer.Send(name);
                manager.Add(name, transfer.Get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        transfer.Send("getFiles: end");
    }

    private static void readManager(FileManager man) {
        for (String name : man.GetKeys()) {
            System.out.println(String.join(" содержит:\n", name, man.Get(name)));
        }
    }
}
