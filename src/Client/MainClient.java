package Client;
import WorkWithFile.FileManager;
import WorkWithFile.Writer;
import simpleNetwork.Transfer;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainClient {
    private static final FileManager manager = new FileManager();
    private static final String clientDir = "/home/nlx/clientFolder/";
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 6006);
        Transfer transfer = new Transfer(clientSocket.getInputStream(), clientSocket.getOutputStream());

        String files = transfer.Get();
        System.out.println("На свервере есть: " + files);
        Set<String> newFile = CheckFile(files);
        downloadFile(newFile, transfer);

        readManager();

        clientSocket.close();
        transfer.close();
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
            } catch (IOException _) {
                System.out.println("Пропущен файл по ошибке в MainClient.downloadFile()");
            }
        }
        transfer.Send("getFiles: end");
        saveFile();
    }

    private static void saveFile() {
        for (String name : manager.GetKeys()) {
            try {
                Writer.write(clientDir + name, manager.Get(name));
            } catch (IOException e) {
                System.out.println("Не записан файл по ошибке IOException in Write.write() by MainClient.saveFile()");
            }
        }
    }

    private static void readManager() {
        for (String name : MainClient.manager.GetKeys()) {
            System.out.println(String.join(" содержит:\n", name, MainClient.manager.Get(name)));
        }
    }
}
