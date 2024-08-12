package WorkWithFile;

import java.util.HashMap;
import java.util.Set;

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

    public Set<String> GetKeys() {
        return files.keySet();
    }

    public void Remove(String filename) {
        files.remove(filename);
    }
}
