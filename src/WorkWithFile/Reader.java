package WorkWithFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {
    /**
     * @param filename
     * @return
     * @throws IOException
     */
    public static String Read(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}
