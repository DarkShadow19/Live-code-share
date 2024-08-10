package WorkWithFile;

import java.util.HashMap;

public class FileManager {
    HashMap<String, String> files;

    public FileManager() {
        files = new HashMap<>();
    }

    public void Add(String filename, String data) {
        files.put(filename, data);
    }

    public String Get(String filename) {
        return files.get(filename);
    }

    public void Remove(String filename) {
        files.remove(filename);
    }
}
