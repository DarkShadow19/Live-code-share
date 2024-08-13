package WorkWithFile;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {
    /**
     * @param filename
     * @param data
     * @throws IOException
     */
    public static void write(String filename, String data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filename);
        byte[] buffer = data.getBytes();

        outputStream.write(buffer);
        outputStream.close();
    }
}
