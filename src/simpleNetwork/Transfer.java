package simpleNetwork;

import java.io.*;

public class Transfer implements Closeable{
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public Transfer(InputStream input, OutputStream output) {
        bufferedReader = new BufferedReader(new InputStreamReader(input));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
    }

    /**
     * @param message
     * @throws IOException
     */
    public void Send(String message) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.flush();
    }

    /**
     * @return message from server
     * @throws IOException
     */
    public String Get() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
        bufferedWriter.close();
    }
}
