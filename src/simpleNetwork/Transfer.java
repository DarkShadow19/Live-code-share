package simpleNetwork;

import java.io.*;

public class Transfer implements Closeable{
    DataInputStream inputStream;
    DataOutputStream outputStream;
    BufferedReader terminal;

    public Transfer(InputStream input, OutputStream output) {
        inputStream = new DataInputStream(input);
        outputStream =new DataOutputStream(output);
        terminal = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * @return string from terminal
     * @throws IOException
     */
    public String GetTerminal() throws IOException {
        return terminal.readLine();
    }

    /**
     * @param message
     * @throws IOException
     */
    public void Send(String message) throws IOException {
        outputStream.writeUTF(message);
    }

    /**
     * @return message from server
     * @throws IOException
     */
    public String Get() throws IOException {
        return inputStream.readUTF();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
